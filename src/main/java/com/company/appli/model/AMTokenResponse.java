package com.company.appli.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AMTokenResponse {
    String access_token;
    String expires_at;
    String token_type;
}
