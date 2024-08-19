package com.amzur.service

import com.amzur.dto.Request.UserRequest
import com.amzur.dto.Response.UserResponse
import com.amzur.entity.UserEntity
import com.amzur.handlers.UserNotFound
import com.amzur.constants.ApplicationConstants
import jakarta.inject.Singleton


@Singleton
class UserService {


    private Map<Integer, UserEntity> users = [:]
    private int nextUserId = 1


    def addUser(UserRequest userRequest){
        int userId=nextUserId++
        UserEntity userEntity=new UserEntity(
                userRequest.name,
                userRequest.address,
                userRequest.phoneNumber,
                userRequest.password,
                userRequest.email)
        users[userId]=userEntity
        return userId
    }
    def removeUser(int userId){
        if (users.get(userId)) {   // Check if the userId exists
            users.remove(userId)           // Remove the user
            return true                    // Return true if user was removed
        }
        throw new UserNotFound(ApplicationConstants.USER_NOT_FOUND)
    }

    def getAllUsers(){
        return users
    }
    def updateUser(int userId, UserResponse updatedUser) {
        if (users.containsKey(userId)) {
            users[userId] = updatedUser
            return true
        }
        throw new UserNotFound(ApplicationConstants.USER_NOT_FOUND)
    }

    UserResponse userLogin(String email, String password) {
        UserEntity userEntity = users.values().find { it.email == email && it.password == password }
        if (userEntity) {
            return convertEntityToResponse(userEntity)
        }
        throw new UserNotFound(ApplicationConstants.USER_NOT_FOUND)    }

    UserResponse convertEntityToResponse(UserEntity userEntity) {
        def userResponse = new UserResponse(
                userEntity.name,
                userEntity.email,
                userEntity.phoneNumber,
                userEntity.address

        )
        return userResponse
    }

    def getEmail(int userId){
        if(users.containsKey(userId)){
            return users[userId].email
        }else{
            throw new UserNotFound("User with ID $userId not found")
        }
    }


}
