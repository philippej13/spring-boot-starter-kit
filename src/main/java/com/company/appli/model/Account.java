package com.company.appli.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @BsonId
    private String id;
    private String domaine;
    private String email;
    private String nom;
    private String prenom;
}
