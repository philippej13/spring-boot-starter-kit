package com.company.appli.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AMCreateAccountResponse {
    @JsonProperty("domaine")
    private String domaine;
    @JsonProperty("email")
    private String email;
    @JsonProperty("created")
    private String created;
    @JsonProperty("id")
    private String id;
}
