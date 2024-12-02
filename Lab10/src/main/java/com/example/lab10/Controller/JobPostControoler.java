package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/JobPost")
@RequiredArgsConstructor
public class JobPostControoler {
    private final JobPostService jobPostService;


    @GetMapping("/get")
    public ResponseEntity getJobPost(){
        return ResponseEntity.status(HttpStatus.OK).body(jobPostService.getJobPost());
    }
    @PostMapping("/add")
    public ResponseEntity addJobPost(@Valid @RequestBody JobPost jobPost , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(message));
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("jobPost added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id ,@Valid @RequestBody JobPost jobPost , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(message));
        }
        if(jobPostService.updateJobPost(id,jobPost)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("jobPost updated"));

        }else  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id){

        if(jobPostService.deleteJobPost(id)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("jobPost deleted"));
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("id not found"));
    }

}
