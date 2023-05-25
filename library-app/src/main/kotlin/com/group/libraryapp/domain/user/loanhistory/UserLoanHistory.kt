package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import jakarta.persistence.*

@Entity
class UserLoanHistory(

    @ManyToOne
    val user : User,

    val bookName : String,

    @Enumerated(EnumType.STRING)
    var status : UserLoanStatus = UserLoanStatus.LOANED,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    fun doReturn() {
        this.status = UserLoanStatus.RETURNED
    }
}