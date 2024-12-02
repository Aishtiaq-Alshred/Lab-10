package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.User;
import com.example.lab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/User")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(message));
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user added"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id ,@Valid@RequestBody User user , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(message));
        }
        if(userService.updateUser(id,user)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user updated"));
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){

        if(userService.deleteUser(id)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user deleted"));
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("id not found"));

    }

}
