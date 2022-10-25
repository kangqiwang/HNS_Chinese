package com.kang.medicalwebapplication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Document(collection = "customer")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Customer {
    @Id
    private String id;
    @NotBlank(message = "Please provide a first name.")
    private String first_name;
    @NotBlank(message = "Please provide a last name.")
    private String last_name;
    @NotBlank(message = "Please provide a email.")
    private String email;
    private String companyName;
}
