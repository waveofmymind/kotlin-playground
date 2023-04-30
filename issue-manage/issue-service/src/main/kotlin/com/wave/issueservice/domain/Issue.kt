package com.wave.issueservice.domain

import com.fasterxml.jackson.databind.BeanDescription
import com.wave.issueservice.domain.enums.IssuePriority
import com.wave.issueservice.domain.enums.IssueStatus
import com.wave.issueservice.domain.enums.IssueType
import jakarta.annotation.Priority
import jakarta.persistence.*

@Entity
@Table
class Issue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var userId: Long,

    @OneToMany(fetch = FetchType.EAGER)
    val comments: MutableList<Comment> = mutableListOf(),

    @Column
    var summary: String,

    @Column
    var description: String,

    @Column
    @Enumerated(EnumType.STRING)
    var type: IssueType,

    @Column
    @Enumerated(EnumType.STRING)
    var priority: IssuePriority,

    @Column
    @Enumerated(EnumType.STRING)
    var status: IssueStatus,


    ) : BaseEntity()