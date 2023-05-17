package com.library.model.users

interface UserCredential {
    fun credential(): ROLES
}

class User(
    val name:String,
    val surname:String,
    val roles: ROLES
): UserCredential{
    override fun credential(): ROLES {
        return roles
    }
}

enum class ROLES{
    ADMIN,
    MEMBER
}