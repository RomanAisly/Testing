package com.example.testing.data

import com.example.testing.data.local.UsersEntity
import com.example.testing.data.remote.dto.Data
import com.example.testing.domain.model.Users

fun Data.toUsersEntity(): UsersEntity {
    return UsersEntity(
        avatar = avatar,
        email = email,
        first_name = first_name,
        last_name = last_name,
        id = id
    )
}

fun UsersEntity.toUsers(): Users {
    return Users(
        avatar = avatar,
        email = email,
        first_name = first_name,
        last_name = last_name,
        id = id
    )
}