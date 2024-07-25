package com.example.day3crudexercise.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankManagement {
    private String id;
    private String username;
    private double balance;
}
