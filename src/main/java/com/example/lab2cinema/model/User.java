package com.example.lab2cinema.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private int id;
    private String name;
    private String secondName;
    private String email;
    private String password;
    private List<Ticket> boughtTickets;
}
