package com.lab4.demo.user;

import com.lab4.demo.BaseControllerTest;
import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.book.model.dto.BookDTO;
import com.lab4.demo.user.dto.UserDTO;
import com.lab4.demo.user.dto.UserListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.lab4.demo.TestCreationFactory.*;
import static com.lab4.demo.UrlMapping.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService userService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        controller = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allUsers() throws Exception {
        List<UserListDTO> userListDTOs = TestCreationFactory.listOf(UserListDTO.class);
        when(userService.allUsersForList()).thenReturn(userListDTOs);

        ResultActions result = mockMvc.perform(get(USERS));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(userListDTOs));
    }

    @Test
    void getUser() throws Exception {
        long id = randomLong();
        UserDTO reqUser = UserDTO.builder()
                .id(id)
                .email("blablablabla@yahoo.com")
                .password("SetSdf24#411!")
                .username("blablablabla")
                .build();
        when(userService.get(id)).thenReturn(reqUser);

        ResultActions result = performGetWithPathVariable(USERS + ENTITY, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqUser));
    }

    @Test
    void delete() throws Exception {
        long id = randomLong();
        doNothing().when(userService).delete(id);

        ResultActions result = performDeleteWithPathVariable(USERS + ENTITY, id);
        result.andExpect(status().isOk());
        verify(userService, times(1)).delete(id);
    }

    @Test
    void deleteAll() throws Exception {
        doNothing().when(userService).deleteAll();

        ResultActions result = performDelete(USERS);
        result.andExpect(status().isOk());
        verify(userService, times(1)).deleteAll();
    }

    //TODO: vezi video sfarsit pentru UNPARSABLE ERROR
    @Test
    void create() throws Exception {
//        UserDTO reqUser = UserDTO.builder().username("cool_username")
//                .password("!31243rqaQWEQ")
//                .email("blabla_2421@gmail.com")
//                .role("ADMIN")
//                .build();
//
//        when(userService.create(reqUser)).thenReturn(reqUser);
//
//        ResultActions result = performPostWithRequestBody(USERS, reqUser);
//        result.andExpect(status().isOk())
//                .andExpect(jsonContentToBe(reqUser));
        UserDTO user = UserDTO.builder()
                .id(randomLong())
                .email(randomEmail())
                .username(randomString())
                .password(randomString())
                .build();
        when(userService.create(user)).thenReturn(user);
        ResultActions result = performPostWithRequestBody(USERS,user);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(user));
    }
    @Test
    void changePassword() throws Exception {
        long id = randomLong();
        String newPassword = "newPass@31!";
        UserDTO reqUser = UserDTO.builder()
                .username("username_cool")
                .password(newPassword)
                .email("blabla_2421@gmail.com")
                .build();

        when(userService.changePassword(id, newPassword)).thenReturn(reqUser);

        ResultActions result = performPatchWithRequestBodyAndPathVariable(USERS+ENTITY, newPassword, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqUser));
    }
    //TODO: vezi video sfarsit pentru UNPARSABLE ERROR
    @Test
    void edit() throws Exception {
        long id = randomLong();
        UserDTO reqUser = UserDTO.builder()
                .username("username_cool")
                .password("!31243rqaQWEQ")
                .email("blabla_2421@gmail.com")
                .role("EMPLOYEE")
                .build();

        when(userService.edit(id, reqUser)).thenReturn(reqUser);

        ResultActions result = performPutWithRequestBodyAndPathVariable(USERS + ENTITY, reqUser, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqUser));
    }

}