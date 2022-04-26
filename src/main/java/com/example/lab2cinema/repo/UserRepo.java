package com.example.lab2cinema.repo;

import com.example.lab2cinema.model.User;

import java.util.List;

public interface UserRepo {
    List<User> getUsers();
    User getUserById(int id);


}
