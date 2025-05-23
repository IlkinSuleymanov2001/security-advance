package com.example.demo.service.impl.inter;

import com.example.demo.dao.entity.UserEntity;
import com.example.demo.model.criteria.PageCriteria;
import com.example.demo.model.criteria.UserCriteria;
import com.example.demo.model.request.user.CreateUserRequest;
import com.example.demo.model.request.user.GetUserIdentifierRequest;
import com.example.demo.model.request.user.UpdateUserRequest;
import com.example.demo.model.response.UserResponse;
import com.example.demo.model.response.page.PageableResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest createUserRequest);

    PageableResponse<UserResponse> getAllUsers(PageCriteria pageCriteria, UserCriteria userCriteria);

    UserResponse getUser(GetUserIdentifierRequest userIdentity);

    UserEntity findUser(GetUserIdentifierRequest userIdentity);

    List<UserResponse> getUsersByName(String name);

    void deleteAllDeActiveUsers();

    UserResponse updateUser(UpdateUserRequest updateUserRequest);
}
