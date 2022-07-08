package com.ua.jwtappdemo.entities

import javax.persistence.*

@Entity
@Table(name = "users")
class User(

    var username: String,

    var firstName: String,

    var lastName: String,

    var email: String,

    var password: Array<Char>,

    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = arrayOf(JoinColumn(name = "user_id")))
    @Enumerated(EnumType.STRING)
    private var roles: Set<Role>,

    id: Long,
    dateCreated: Long,
    dateUpdated: Long,
    status: Status

) : BaseEntity(id, dateCreated, dateUpdated, status) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (username != other.username) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (email != other.email) return false
        if (!password.contentEquals(other.password)) return false
        if (roles != other.roles) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.contentHashCode()
        result = 31 * result + roles.hashCode()
        return result
    }
}