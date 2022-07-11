package com.ua.jwtappdemo.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long,

    @Column(name = "created_at")
    @CreatedDate
    open var dateCreated: Long? = null,

    @Column(name = "last_modified_at")
    @LastModifiedDate
    open var dateUpdated: Long? = null,

    @Enumerated(EnumType.STRING)
    open var status: Status?
)