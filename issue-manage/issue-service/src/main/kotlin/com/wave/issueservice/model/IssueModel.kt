package com.wave.issueservice.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.wave.issueservice.domain.Issue
import com.wave.issueservice.domain.enums.IssuePriority
import com.wave.issueservice.domain.enums.IssueStatus
import com.wave.issueservice.domain.enums.IssueType
import java.time.LocalDateTime

data class IssueRequest(
    val summary: String,
    val description: String,
    val status: IssueStatus,
    val type: IssueType,
    val priority: IssuePriority,
)

data class IssueResponse(
    val id: Long,
    val userId: Long,
    val summary: String,
    val description: String,
    val status: IssueStatus,
    val type: IssueType,
    val priority: IssuePriority,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime?,
) {

    companion object {
        operator fun invoke(issue: Issue) =
            with(issue) {
                IssueResponse(
                    id = id!!,
                    summary = summary,
                    description = description,
                    status = status,
                    type = type,
                    priority = priority,
                    userId = userId,
                    createdAt = createdAt,
                    updatedAt = updatedAt,

                    )
            }
    }
}