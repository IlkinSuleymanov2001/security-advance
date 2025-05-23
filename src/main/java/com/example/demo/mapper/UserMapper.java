//package com.example.demo.mapper;
//
//import com.example.demo.dao.entity.UserEntity;
//import com.example.demo.model.request.user.CreateUserRequest;
//import com.example.demo.model.response.UserResponse;
//import com.example.demo.model.response.page.PageableResponse;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Mapper(componentModel = "spring")
//public abstract class UserMapper {
//
//     @Autowired
//     protected  PasswordEncoder passwordEncoder;
//
//
//    @Mapping(target = "password", expression = "java(passwordEncoder.encode(createUserRequest.getPassword()))")
//    public abstract UserEntity toUser(CreateUserRequest createUserRequest);
//
//    public abstract UserEntity toUser(UserResponse userResponse);
//
//    public abstract UserResponse toUserResponse(UserEntity userEntity);
//
//    public abstract UserEntity updateUser(CreateUserRequest createUserRequest, @MappingTarget UserEntity userEntity);
//
//
//    public PageableResponse<UserResponse> toPageableUserResponse(Page<UserEntity> userEntityPage) {
//
//        PageableResponse<UserResponse> pageableResponse = new PageableResponse();
//
//        pageableResponse.setTotalElements(userEntityPage.getTotalElements());
//        pageableResponse.setData(userEntityPage.stream().map(this::toUserResponse).toList());
//        pageableResponse.setHasNextPage(userEntityPage.hasNext());
//        pageableResponse.setLastPageNumber(userEntityPage.getNumber());
//
//        return pageableResponse;
//    }
//
//}
