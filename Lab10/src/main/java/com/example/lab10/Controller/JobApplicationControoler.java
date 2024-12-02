package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobApplication")
@RequiredArgsConstructor
public class JobApplicationControoler {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getJobApplication(){
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.getJopApplication());
    }

    @PostMapping("/add")
    public ResponseEntity ApplyForJob(@Valid @RequestBody JobApplication jopApplication , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(message));
        }
        if(jobApplicationService.ApplyForJob(jopApplication)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("job apply"));
        }return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity WithdrawApplication(@PathVariable Integer id){

        if(jobApplicationService.WithdrawJobApplication(id)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("withdraw application"));
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("id not found"));
    }
}
