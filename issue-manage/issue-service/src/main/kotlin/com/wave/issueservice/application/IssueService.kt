package com.wave.issueservice.application

import com.wave.issueservice.domain.Issue
import com.wave.issueservice.domain.IssueRepository
import com.wave.issueservice.domain.enums.IssueStatus
import com.wave.issueservice.exception.NotFoundException
import com.wave.issueservice.model.IssueRequest
import com.wave.issueservice.model.IssueResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueService(
    private val issueRepository: IssueRepository
) {

    @Transactional
    fun create(userId: Long, request: IssueRequest): IssueResponse {

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

    @Transactional(readOnly = true)
    fun getAll(status: IssueStatus) =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)
            ?.map { IssueResponse(it) }

    @Transactional(readOnly = true)

    fun get(id: Long): IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다.")
        return IssueResponse(issue)
    }

    @Transactional
    fun edit(userId: Long, id: Long, request: IssueRequest): IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다.")

        return with(issue) {
            summary = request.summary
            description = request.description
            this.userId = userId
            status = request.status
            type = request.type
            priority = request.priority

            IssueResponse(issueRepository.save(this))
        }


    }
    @Transactional
    fun delete(id: Long) {
        issueRepository.deleteById(id)
    }

}
