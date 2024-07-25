package com.example.day3crudexercise.Controller;

import com.example.day3crudexercise.API.ApiResponse;
import com.example.day3crudexercise.Model.BankManagement;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bank")
public class BankManagementController {
    List<BankManagement> bankManagements = new ArrayList<>();

    @GetMapping("/get")
    public List <BankManagement> getBankManagement(){
        return bankManagements;
    }

    @PostMapping("/add")
    public ApiResponse addBankManagement(@RequestBody BankManagement bankManagement){
        for(BankManagement customer: bankManagements){
            if(customer.getId().equalsIgnoreCase(bankManagement.getId())){
                return new ApiResponse("This id is already in use");
            }
        }
        bankManagements.add(bankManagement);
        return new ApiResponse("Successfully added " + bankManagement.getUsername() + " to the bank");
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateBankManagement(@PathVariable String id, @RequestBody BankManagement bankManagement){
        for(BankManagement customer: bankManagements){
            if(customer.getId().equalsIgnoreCase(bankManagement.getId())){
                customer.setUsername(bankManagement.getUsername());
                customer.setBalance(bankManagement.getBalance());
                return new ApiResponse("Successfully updated");
            }
        }
        return new ApiResponse("Account with ID: " + bankManagement.getId() + " not found");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteBankManagement(@PathVariable String id){
        for(BankManagement customer : bankManagements){
            if(customer.getId().equalsIgnoreCase(id)){
                bankManagements.remove(customer);
                return new ApiResponse("Successfully deleted from the bank");
            }
        }
        return new ApiResponse("Account with ID: (" + id + ") not found");
    }


    @PutMapping("/deposit/{id}")
    public ApiResponse Deposit(@PathVariable String id, @RequestParam double amount) {
        if(amount <= 0){
            return new ApiResponse("Amount must be greater than 0");
        }

        for(BankManagement customer : bankManagements){
            if(customer.getId().equalsIgnoreCase(id)){
                customer.setBalance(customer.getBalance() + amount);
                return new ApiResponse("Successfully deposited [ +" + amount + " ] to " + customer.getUsername() + "'s account.");
            }
        }
        return new ApiResponse("Account with ID: (" + id + ") not found");
    }

    @PutMapping("/withdraw/{id}")
    public ApiResponse Withdraw(@PathVariable String id, @RequestParam double amount) {
        if(amount <= 0){
            return new ApiResponse("Amount must be greater than 0");
        }
        for(BankManagement customer : bankManagements){
            if(customer.getId().equalsIgnoreCase(id)){
                if(customer.getBalance() < amount){
                    return new ApiResponse("Insufficient balance for withdrawal.");
                }else {
                    customer.setBalance(customer.getBalance() - amount);
                    return new ApiResponse("Successfully withdraw [ -" + amount + " ] from " + customer.getUsername() + "'s account.");
                }
            }
        }
        return new ApiResponse("Account with ID: (" + id + ") not found");
    }
}


