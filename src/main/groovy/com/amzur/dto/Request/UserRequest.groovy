package com.amzur.dto.Request

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Introspected
@Serdeable
class UserRequest {
    String name
    String email
    String password
    String phoneNumber
    String address
    UserRequest(String name,String email,String password,String address)
    {
        this.name=name
        this.email=email
        this.password=password
        this.address=address
    }
}
