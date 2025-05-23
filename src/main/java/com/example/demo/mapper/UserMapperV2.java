package com.example.demo.mapper;

import com.example.demo.dao.entity.UserEntity;
import com.example.demo.model.request.user.CreateUserRequest;
import com.example.demo.model.response.UserResponse;
import com.example.demo.model.response.page.PageableResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring") ///, uses = PasswordEncoderMapper.class
public interface UserMapperV2 {

    //    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    UserEntity toUser(CreateUserRequest createUserRequest);


//    @AfterMapping
//    default void encodePassword(@MappingTarget UserEntity user, CreateUserRequest request, @Context PasswordEncoder passwordEncoder) {
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//    }

    UserEntity toUser(UserResponse userResponse);

    UserResponse toUserResponse(UserEntity userEntity);

    UserEntity updateUser(CreateUserRequest createUserRequest, @MappingTarget UserEntity userEntity);

    default PageableResponse<UserResponse> toPageableUserResponse(Page<UserEntity> userEntityPage) {

        PageableResponse<UserResponse> pageableResponse = new PageableResponse();

        pageableResponse.setTotalElements(userEntityPage.getTotalElements());
        pageableResponse.setData(userEntityPage.stream().map(this::toUserResponse).toList());
        pageableResponse.setHasNextPage(userEntityPage.hasNext());
        pageableResponse.setLastPageNumber(userEntityPage.getNumber());

        return pageableResponse;
    }
}

