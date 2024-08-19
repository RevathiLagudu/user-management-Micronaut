package com.amzur.controller

import com.amzur.dto.Request.UserRequest
import com.amzur.dto.Response.UserResponse
import com.amzur.service.UserService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject


@Controller('/users')
class UserController {

    @Inject
    UserService userService

//    UserController(UserService userService) {
//        this.userService = userService
//    }

   @Post
    def addUser(@Body UserRequest userRequest) {
       return userService.addUser(userRequest)
   }
   @Delete("/{userId}")
    def removeUser(@PathVariable  int userId){
        if(userService.removeUser(userId)){
            return "successfully Removed user"
        }else{
            return "User not Found"
        }
    }
    @Get
    def getAllUsers(){
        return userService.getAllUsers()
    }

    @Put("/{userId}")
    def updateUser(@PathVariable int userId, @Body UserResponse userResponse) {
        if(userService.updateUser(userId, userResponse  )){
            return "Successfully updated"

        } else{
            return  "User not Found"
        }
    }

    @Post("/login")
    def userLogin(@Body UserRequest userRequest){
        return  userService.userLogin(userRequest.getEmail(),userRequest.getPassword());
    }
    @Get("/{userId}")
    def getEmail(@PathVariable int userId){
        return userService.getEmail(userId)
    }

}
