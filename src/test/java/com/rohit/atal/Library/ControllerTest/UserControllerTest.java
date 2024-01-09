package com.rohit.atal.Library.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rohit.atal.Library.Model.User;
import com.rohit.atal.Library.controller.UserController;
import com.rohit.atal.Library.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    User user1 = new User(21,"Rohit");
    User user2 = new User(22,"Devendra");

    @Before
    public void SetUP(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsersTest() throws Exception {
        List<User> userList = new ArrayList<>(Arrays.asList(user1,user2));

        Mockito.when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].username", is("Devendra")));
    }

    @Test
    public void getUserByIdTest() throws Exception {
        Mockito.when(userService.getUserById(user1.getUserid())).thenReturn(Optional.ofNullable(user1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/21")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.username",is("Rohit")));
    }

    @Test
    public void createUserTest() throws Exception {
        User user = new User(25,"Mannu");

        Mockito.when(userService.createUser(user)).thenReturn(user);

        String content = objectWriter.writeValueAsString(user);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username", is("Mannu")));
    }

    @Test
    public void updateUserTest() throws Exception{
        User updateduser = new User(21, "Suraj");

        Mockito.when(userService.updateUser(21, updateduser)).thenReturn(updateduser);

        String updatedcontent = objectWriter.writeValueAsString(updateduser);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/users/21")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedcontent);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.username",is("Suraj")));
    }

    @Test
    public void deleteUserTest() throws Exception{
        Mockito.when(userService.deleteUser(22L)).thenReturn(user2);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/22")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
