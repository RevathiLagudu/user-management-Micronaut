package com.amzur.dto.Response

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Introspected
@Serdeable
class UserResponse {
    String name
    String email
    String phoneNumber
    String address

    UserResponse(String name, String email, String phoneNumber, String address) {
        this.name = name
        this.email = email

        this.phoneNumber = phoneNumber
        this.address = address
    }
}