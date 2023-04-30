package com.wave.issueservice.application

import com.wave.issueservice.domain.Issue
import com.wave.issueservice.domain.IssueRepository
import com.wave.issueservice.model.IssueRequest
import com.wave.issueservice.model.IssueResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueService(
    private val issueRepository: IssueRepository
) {

    @Transactional
    fun create(userId : Long, request : IssueRequest) : IssueResponse {

        val issue = Issue(
            summary = request.summary,
            userId = userId,
            type = request.type,
            priority = request.priority,
            description = request.description,
            status = request.status,

            )

        return IssueResponse(issueRepository.save(issue))
    }
}