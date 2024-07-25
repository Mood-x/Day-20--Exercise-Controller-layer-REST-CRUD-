package com.example.tasklistapp.Controller;

import com.example.tasklistapp.API.ApiResponse;
import com.example.tasklistapp.Model.TaskList;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskListController {
    List<TaskList> tasks = new ArrayList<>();

    @GetMapping("/get")
    public List <TaskList> getAllTasks(){
        return tasks;
    }

    @PostMapping("/add")
    public ApiResponse addBankManagement(@RequestBody TaskList task){
        tasks.add(task);
        return new ApiResponse("Successfully added " + task.getTitle() + " to the task list");
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateBankManagement(@PathVariable int index, @RequestBody TaskList task){
        tasks.set(index, task);
        return new ApiResponse("Successfully updated " + task.getId());
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteBankManagement(@PathVariable int index){
        tasks.remove(index);
        return new ApiResponse("Successfully deleted from the task list");
    }


    @PutMapping("/change/{index}")
    public ApiResponse changeStatus(@PathVariable int index, @RequestBody String status) {
            TaskList task = tasks.get(index);
            task.setStatus(status);
            return new ApiResponse("Successfully changed status of task with ID " + task.getId());
    }

    @PostMapping("/search")
        public ApiResponse searchTasks(@RequestBody String title){
            for(TaskList task : tasks){
                if(task.getTitle().equals(title)){
                    return new ApiResponse("It's Found ->" + title);
                }
            }
            return new ApiResponse("Task not found");
    }
}
