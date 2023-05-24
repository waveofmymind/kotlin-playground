package com.example.order.domain.partner

import jakarta.persistence.*

@Entity
@Table(name = "partners")
class Partner(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var partnerToken: String,
    var partnerName: String,
    var businessNo: String,
    var email: String,

    @Enumerated(EnumType.STRING)
    var status: Status = Status.ENABLE
) {

    fun disable() {
        this.status = Status.DISABLE
    }

    fun enable() {
        this.status = Status.ENABLE
    }
}