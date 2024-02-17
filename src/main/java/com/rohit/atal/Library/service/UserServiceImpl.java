package com.rohit.atal.Library.service;

import com.rohit.atal.Library.Model.Book;
import com.rohit.atal.Library.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    List<User> Userlist = new ArrayList<>();

    public List<User> getUserlist() {
        return Userlist;
    }

    public void setUserlist(List<User> userlist) {
        Userlist = userlist;
    }

    //    public UserServiceImpl(){
//        Userlist.add(new User(13,"rohit"));
//    }
    @Override
    public List<User> getAllUsers() {
        return Userlist;
    }

    @Override
    public Optional<User> getUserById(int Userid) {
        Optional<User> user = Optional.empty();
        for (User obj : Userlist) {
            if (obj.getUserid() == Userid) {
                user = Optional.of(obj);
                break;
            }
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        Userlist.add(user);
        return user;
    }

    @Override
    public User updateUser(int Userid, User updatedUser) {
        for (User obj : Userlist) {
            if (obj.getUserid() == Userid) {
                obj.setUsername(updatedUser.getUsername());
                return obj;
            }
        }
        return new User();
    }

    @Override
    public User deleteUser(Long Userid) {
        //Userlist.removeIf(obj -> obj.getUserid() == Userid);
        for (User obj : Userlist) {
            if (obj.getUserid() == Userid) {
                Userlist.remove(obj);
                return obj;
            }
        }
        return new User();
    }
}
