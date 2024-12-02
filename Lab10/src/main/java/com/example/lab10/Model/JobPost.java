package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title should not be empty")
    @Size(min = 4 , message = "title must be more than 4")
    @Column(columnDefinition = "varchar(50) not null")
    private String title;

    @NotEmpty(message = "description should not be empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String description;

    @NotEmpty(message = "location should not be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String location;

    @NotNull(message = "salary should not be null")
    @Positive(message = "salary must be positive")
    @Column(columnDefinition = "int  not null")
    private Integer salary;

    @Column(columnDefinition = "not null")
    private Date postingdate;


}
