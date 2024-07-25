package com.example.tasklistapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskList {
    private String id;
    private String title;
    private String description;
    private String status;
}
