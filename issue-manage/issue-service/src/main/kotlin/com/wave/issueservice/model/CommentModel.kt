package com.wave.issueservice.model

import com.wave.issueservice.domain.Comment

data class CommentRequest(
    val body: String
)

data class CommentResponse(
    val id: Long,
    val issueId: Long,
    val userId: Long,
    val username: String? = null,
    val body: String,
)

fun Comment.toResponse() = CommentResponse(
    id = id!!,
    issueId = issue.id!!,
    userId = userId,
    username = username,
    body = body
)
