package com.apriltechnologies.amadmin.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {
    private String id;
    private String email;
    private String nom;
    private String prenom;
}
