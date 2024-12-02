package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 4 , message = "name must be more than 4 letters")
    @Pattern(regexp = "[A-Za-z]+" , message = "name should only contain characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Email
    @Column(columnDefinition = "varchar(30) unique")
    private String email;

    @NotEmpty(message = "password should not be null")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;


    @NotNull(message = "age should not be null")
    @Positive
    @Min(value = 21,message = "age must be more than 21")
    @Column(columnDefinition = "int not null check(age>=21)")
    private int age ;


}
