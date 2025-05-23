//package com.example.demo.controller
//
//import com.example.demo.Randoms
//import com.example.demo.exception.GlobalExceptionHandler
//import com.example.demo.model.response.UserResponse
//import com.example.demo.service.inter.UserService
//import org.springframework.http.HttpStatus
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.setup.MockMvcBuilders
//import spock.lang.Specification
//
//class UserControllerTest extends  Specification {
//
//    MockMvc mockMvc
//    UserService userService
////    UserController userController;
//
//
//    void  setup(){
//        userService=Mock()
////        userController = new UserController(userService)
//        mockMvc = MockMvcBuilders.standaloneSetup(userController)
//                .setControllerAdvice(new GlobalExceptionHandler())
//                .build()
//    }
//
//    def "TestGetSearchUser"(){
//        given:
//        def name= Randoms.random.nextObject(String)
//        def url = "/v1/user/search/$name"
//        def user=Randoms.random.nextObject(UserResponse)
//
//
//        when:
//        def result = mockMvc.
//                perform(MockMvcRequestBuilders.get(new URI(url))
//                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
//                .andReturn()
//        then:
//        1*userService.getUsersByName(name) >> [user]
//        result.response.status== HttpStatus.OK.value()
//
//
//
//
//
//
//    }
//
//
//
//}
