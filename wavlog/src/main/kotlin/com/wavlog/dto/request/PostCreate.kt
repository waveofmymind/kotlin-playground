package com.wavlog.dto.request

import jakarta.validation.constraints.NotBlank

data class PostCreate (

    @NotBlank(message = "타이틀을 입력해주세요.")
    val title: String,
    @NotBlank(message = "콘텐츠를 입력해주세요.")
    val content: String
)