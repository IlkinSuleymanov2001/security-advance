package com.example.demo.service.impl;

import com.example.demo.dao.entity.UserEntity;
import com.example.demo.dao.entity.securtiy.APiDomainEntity;
import com.example.demo.dao.entity.securtiy.PermissionEntity;
import com.example.demo.dao.entity.securtiy.RoleEntity;
import com.example.demo.dao.entity.securtiy.SecurityUserEntity;
import com.example.demo.dao.repository.UserRepository;
import com.example.demo.dao.repository.security.DomainRepository;
import com.example.demo.dao.repository.security.RoleRepository;
import com.example.demo.dao.repository.security.SPermissionRepository;
import com.example.demo.dao.repository.security.UserSecurityRepository;
import com.example.demo.mapper.UserMapperV2;
import com.example.demo.model.criteria.PageCriteria;
import com.example.demo.model.criteria.UserCriteria;
import com.example.demo.model.enums.Domains;
import com.example.demo.model.request.user.CreateUserRequest;
import com.example.demo.model.request.user.GetUserIdentifierRequest;
import com.example.demo.model.request.user.UpdateUserRequest;
import com.example.demo.model.response.UserResponse;
import com.example.demo.model.response.page.PageableResponse;
import com.example.demo.service.impl.inter.UserService;
import com.example.demo.service.specification.UserSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.exception.util.ExceptionUtil.EXCEPTION;
import static com.example.demo.model.enums.ErrorInfo.USER_NOT_FOUND;
import static com.example.demo.service.impl.security.TokenService.getUserEntityId;
import static lombok.AccessLevel.PRIVATE;


@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {

    UserRepository userRepository;
    UserSecurityRepository userSecurityRepository;
    PasswordEncoder passwordEncoder;
    SPermissionRepository permissionRepository;
    DomainRepository domainRepository;
    RoleRepository roleRepository;
    UserMapperV2 userMapper;

    @PostConstruct
    @Transactional
    public void init() {

        RoleEntity user = RoleEntity.builder()
                .name("USER")
                .build();
        RoleEntity ADMIN = RoleEntity.builder()
                .name("ADMIN")
                .build();

        SecurityUserEntity i = SecurityUserEntity.
                builder()
                .email("ilkin@gmail.com").
                role(user).
                password(passwordEncoder.encode("123"))
                .build();

        SecurityUserEntity ii = SecurityUserEntity.
                builder()
                .email("ilkin-admin@gmail.com").
                role(ADMIN).
                password(passwordEncoder.encode("123a"))
                .build();

        List<APiDomainEntity> domains = Arrays.stream(Domains.values())
                .map(domain -> APiDomainEntity.builder()
                        .name(domain.getDomain())
                        .build())
                .collect(Collectors.toList());

        PermissionEntity s = PermissionEntity.
                builder()
                .delete(true)
                .write(true)
                .read(true)
                .domain(domains.get(6))
                .role(ADMIN)
                .build();

        PermissionEntity ss = PermissionEntity.
                builder()
                .read(true)
                .domain(domains.get(6))
                .role(user)
                .build();

        PermissionEntity sss = PermissionEntity.
                builder()
                .read(true)
                .write(true)
                .domain(domains.get(4))
                .role(user)
                .build();

        domainRepository.saveAll(domains);
        roleRepository.saveAll(List.of(user, ADMIN));
        userSecurityRepository.saveAll(List.of(i, ii));
        permissionRepository.saveAll(List.of(s, ss, sss));


    }

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity = userMapper.toUser(createUserRequest);

        userRepository.save(userEntity);
        return userMapper.toUserResponse(userEntity);
    }

    @Override
    public PageableResponse<UserResponse> getAllUsers(PageCriteria pageCriteria, UserCriteria userCriteria) {

        PageRequest pageable = PageRequest.of(pageCriteria.getPage(),
                pageCriteria.getCount(),
                Sort.by("id").descending());

        Page<UserEntity> filteredUsers = userRepository.findAll(new UserSpecification(userCriteria), pageable);


        return userMapper.toPageableUserResponse(filteredUsers);
    }

    @Override
    public UserResponse getUser(GetUserIdentifierRequest userIdentity) {

        UserEntity user = findUser(userIdentity);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserEntity findUser(GetUserIdentifierRequest userIdentity) {
        UserEntity userEntity = null;
//        if (userIdentity.getUserId() != null) {
//            userEntity = fetchUserById(userIdentity.getUserId());
//
//        } else if (userIdentity.getUserEmail() != null) {
//            userEntity = userRepository.findUserByEmail(userIdentity.getUserEmail())
//                    .orElseThrow(() -> EXCEPTION.notFoundThrowable(USER_NOT_FOUND));
//        } else {
//            EXCEPTION.notFound(USER_ID_AND_EMAIL_ERROR);
//        }
        return userEntity;
    }


    @Override
    public List<UserResponse> getUsersByName(String name) {
        return userRepository.findAllByName(name).
                stream().
                map(userMapper::toUserResponse).
                toList();
    }

    @Override
    public void deleteAllDeActiveUsers() {
        List<UserEntity> allByActiveAccountIsFalse = userRepository.findAllByActiveAccountIsFalse();
        userRepository.deleteAll(allByActiveAccountIsFalse);
    }


    //    @Transactional
    @Override
    public UserResponse updateUser(UpdateUserRequest updateUserRequest) {

        log.error("user id from token: {}", getUserEntityId());
        UserEntity userEntity = fetchUserById(updateUserRequest.getUserId());
        userEntity.setActiveAccount(updateUserRequest.getStatus());
        userRepository.save(userEntity);

        return userMapper.toUserResponse(userEntity);
    }


    private UserEntity fetchUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> EXCEPTION.notFoundThrowable(USER_NOT_FOUND));
    }


}