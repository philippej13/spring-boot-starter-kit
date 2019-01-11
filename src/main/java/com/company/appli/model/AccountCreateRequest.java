package com.company.appli.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCreateRequest {
    private String domaine;
    private String email;
    private String name;
    private String firstName;
}

