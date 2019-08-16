package com.company.appli.service;


import java.util.HashMap;

public interface AccountService {
    String createAccount(String domaine, String username, String idpId, String email, String firstName, String lastName, HashMap<String, Object> additionalInformation);
    boolean deleteAccount(String domaine, String email);
    Boolean findByEmail(String domaine, String email);
    Boolean findByUserName(String domaine, String userName);
}
