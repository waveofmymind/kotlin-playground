package com.example.tosspayments

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*

@Controller
@RequestMapping(value = ["/"])
class PaymentController {

    @GetMapping(value = ["success"])
    @Throws(Exception::class)
    fun paymentResult(
        model: Model,
        @RequestParam(value = "orderId") orderId: String,
        @RequestParam(value = "amount") amount: Int,
        @RequestParam(value = "paymentKey") paymentKey: String
    ): String {

        val secretKey = "test_sk_YPBal2vxj81DdBkGM6Gr5RQgOAND:"

        val encoder = Base64.getEncoder()
        val encodedBytes = encoder.encode(secretKey.toByteArray(charset("UTF-8")))
        val authorizations = "Basic " + String(encodedBytes, 0, encodedBytes.size)

        val url = URL("https://api.tosspayments.com/v1/payments/$paymentKey")

        val connection = url.openConnection() as HttpURLConnection
        connection.setRequestProperty("Authorization", authorizations)
        connection.setRequestProperty("Content-Type", "application/json")
        connection.requestMethod = "POST"
        connection.doOutput = true

        val obj = JSONObject()
        obj.put("orderId", orderId)
        obj.put("amount", amount)

        val outputStream: OutputStream = connection.outputStream
        outputStream.write(obj.toString().toByteArray(charset("UTF-8")))

        val code = connection.responseCode
        val isSuccess = code == 200

        model.addAttribute("isSuccess", isSuccess)

        val responseStream = if (isSuccess) connection.inputStream else connection.errorStream

        val reader = InputStreamReader(responseStream, StandardCharsets.UTF_8)
        val parser = JSONParser()
        val jsonObject = parser.parse(reader) as JSONObject

        responseStream.close()

        model.addAttribute("responseStr", jsonObject.toJSONString())
        println(jsonObject.toJSONString())

        model.addAttribute("method", jsonObject["method"] as String?)
        model.addAttribute("orderName", jsonObject["orderName"] as String?)

        val method = jsonObject["method"] as String?
        if (method != null) {
            when (method) {
                "카드" -> model.addAttribute("cardNumber", (jsonObject["card"] as JSONObject)["number"] as String?)
                "가상계좌" -> model.addAttribute(
                    "accountNumber",
                    (jsonObject["virtualAccount"] as JSONObject)["accountNumber"] as String?
                )

                "계좌이체" -> model.addAttribute("bank", (jsonObject["transfer"] as JSONObject)["bank"] as String?)
                "휴대폰" -> model.addAttribute(
                    "customerMobilePhone",
                    (jsonObject["mobilePhone"] as JSONObject)["customerMobilePhone"] as String?
                )
            }
        } else {
            model.addAttribute("code", jsonObject["code"] as String?)
            model.addAttribute("message", jsonObject["message"] as String?)
        }

        return "success"
    }

    @GetMapping(value = ["fail"])
    @Throws(Exception::class)
    fun paymentResult(
        model: Model,
        @RequestParam(value = "message") message: String,
        @RequestParam(value = "code") code: Int
    ): String {
        model.addAttribute("code", code)
        model.addAttribute("message", message)
        return "fail"
    }
}