//package com.example.demo.controller;
//
//import com.example.demo.exception.response.ResponseModel;
//import com.example.demo.model.criteria.PageCriteria;
//import com.example.demo.model.criteria.UserCriteria;
//import com.example.demo.model.request.user.CreateUserRequest;
//import com.example.demo.model.request.user.UpdateUserRequest;
//import com.example.demo.model.response.UserResponse;
//import com.example.demo.model.response.page.PageableResponse;
//import com.example.demo.service.inter.UserService;
//import jakarta.validation.Valid;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.util.List;
//
//import static org.springframework.http.HttpStatus.CREATED;
//import static org.springframework.http.HttpStatus.OK;
//
//@RestController
//@RequestMapping("/v1/user")
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@RequiredArgsConstructor
//@Slf4j
//public class UserController {
//
//    UserService userService;
//
//
//    @GetMapping()
//    public String test() {
//         return "service online ";
//    }
//
//    @GetMapping("/all")
//    public ResponseModel<PageableResponse<UserResponse>> getAllUsers(PageCriteria pageCriteria, UserCriteria userCriteria) {
//        var allUsers = userService.getAllUsers(pageCriteria, userCriteria);
//        return ResponseModel.
//                <PageableResponse<UserResponse>>builder()
//                .data(allUsers)
//                .build();
//    }
//
//    @GetMapping("/search/{name}")
//    @ResponseStatus(OK)
//    public ResponseModel<List<UserResponse>> searchAllUserByName(@PathVariable(value = "name") String value) {
//        List<UserResponse> allUsers = userService.getUsersByName(value);
//        log.info("user list : {}",allUsers.toString());
//        return ResponseModel.
//                <List<UserResponse>>builder()
//                .data(allUsers)
//                .build();
//    }
//
//    @PostMapping("/create")
//    @ResponseStatus(CREATED)
//    public ResponseEntity<ResponseModel<UserResponse>> createUser(@RequestBody CreateUserRequest createUserRequest) {
//        UserResponse user = userService.createUser(createUserRequest);
//        log.info("created user: {}", user);
//        ResponseModel<UserResponse> success = ResponseModel.
//                <UserResponse>builder()
//                .data(user)
//                .message("success")
//                .build();
//        return ResponseEntity.created( URI.create("/users/" + user.getId())).body(success);
//
//    }
//    @PutMapping("/update")
//    @ResponseStatus(OK)
//    public ResponseModel<UserResponse> updateUser( @RequestBody UpdateUserRequest updateUserRequest) {
//        UserResponse user = userService.updateUser(updateUserRequest);
//        log.info("updated user: {}", user);
//        return ResponseModel.
//                <UserResponse>builder()
//                .data(user)
//                .build();
//    }
//}
