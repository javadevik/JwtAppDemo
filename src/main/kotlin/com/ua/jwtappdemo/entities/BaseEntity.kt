package com.ua.jwtappdemo.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import javax.persistence.*

@MappedSuperclass
open class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long,

    @CreatedDate
    open var dateCreated: Long,

    @LastModifiedDate
    open var dateUpdated: Long,

    @Enumerated(EnumType.STRING)
    open var status: Status
)