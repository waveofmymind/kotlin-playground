package com.wave.issueservice.domain

import com.wave.issueservice.domain.enums.IssueStatus
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long> {
    fun findAllByStatusOrderByCreatedAtDesc(status: IssueStatus): List<Issue>?
}