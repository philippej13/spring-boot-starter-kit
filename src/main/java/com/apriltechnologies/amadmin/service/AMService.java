package com.apriltechnologies.amadmin.service;

import com.apriltechnologies.amadmin.model.AMCreateAccountResponseDTO;
import com.apriltechnologies.amadmin.model.AccountDTO;

public interface AMService {
    String createAccount(String domaine, String email, String name, String firstName);
    boolean deleteAccount(String domaine,String email);
    boolean existsAccount(String domaine,String email);
    AccountDTO getAccount(String domaine,String email);
    AccountDTO updateAccount(String domaine,String email, String name, String firstName);
}
