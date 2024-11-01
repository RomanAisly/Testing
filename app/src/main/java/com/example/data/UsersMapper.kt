package com.example.data

import com.example.testing.network.dto.Data

fun Data.toUsersEntity():UsersEntity{
    return UsersEntity(
        avatar = avatar,
        firstName = first_name,
        lastName = last_name
    )
}