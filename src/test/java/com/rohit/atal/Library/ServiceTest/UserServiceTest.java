package com.rohit.atal.Library.ServiceTest;

import com.rohit.atal.Library.Model.User;
import com.rohit.atal.Library.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {
    @Autowired
    private UserServiceImpl userServiceimpl;

    List<User> Userlist = new ArrayList<>();

    public UserServiceTest(){
        this.userServiceimpl = new UserServiceImpl();

        Userlist.add(new User(21,"rohit"));
        Userlist.add(new User(22,"devendra"));

        this.userServiceimpl.setUserlist(Userlist);
    }
    @Test
    public void getAllUsersTest(){
        List<User> Expected = Userlist;
        List<User> Actual = userServiceimpl.getAllUsers();

        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    public void getUserByIdTest(){
        Optional<User> Expected = Optional.ofNullable(Userlist.get(0));
        Optional<User> Actual = userServiceimpl.getUserById(21);

        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    public void createUserTest(){
        User user = new User(23,"Suresh");
        User Expected = user;
        User Actual = userServiceimpl.createUser(user);
        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    public void updateUserTest(){
        User user = new User(21,"Kajal");
        User Expected = user;
        User Actual = userServiceimpl.updateUser(21,user);
        Assertions.assertEquals(Expected,Actual);
    }

    @Test
    public void deleteUserTest(){
        User Expected = Userlist.get(0);
        User Actual = userServiceimpl.deleteUser(21L);

        Assertions.assertEquals(Expected,Actual);
    }
}
