package com.apriltechnologies.amadmin.controller;

import com.apriltechnologies.amadmin.model.AMCreateAccountResponseDTO;
import com.apriltechnologies.amadmin.model.AccountCreateRequestDTO;
import com.apriltechnologies.amadmin.model.AccountDTO;
import com.apriltechnologies.amadmin.service.AMService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api/security/admin")
public class AccountController {

    @Autowired
    AMService amService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts")
    public ResponseEntity<AccountDTO> getAccount() {
        AccountDTO account =  new AccountDTO("1", "email", "Nom", "Prénom");

        return ResponseEntity.ok(account);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/accounts")
    public ResponseEntity<Object> createAccount(@RequestBody AccountCreateRequestDTO accountCreateRequestDTO, HttpServletRequest request) {
        System.out.println(accountCreateRequestDTO);
        //Creation du compte
        String id = amService.createAccount(accountCreateRequestDTO.getDomaine(),
                accountCreateRequestDTO.getEmail(),
                accountCreateRequestDTO.getName(),
                accountCreateRequestDTO.getFirstName());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        //return ResponseEntity.ok(new AccountCreateResponseDTO(true));
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/accounts2")
    @ApiOperation(tags = "Gestion des comptes", value = "Création du compte")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Compte créé"),
            @ApiResponse(code = 500, message = "Erreur interne"),
            @ApiResponse(code = 401, message = "Non authentifié"),
            @ApiResponse(code = 403, message = "Non authorisé")})
    public ResponseEntity<Object> createAccount2(@RequestBody AccountCreateRequestDTO accountCreateRequestDTO, HttpServletRequest request) {
        System.out.println(accountCreateRequestDTO);
        //Creation du compte
        AccountDTO account =  new AccountDTO("15345", "email", "Nom", "Prénom");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(account.getId()).toUri();
        //return ResponseEntity.ok(new AccountCreateResponseDTO(true));
        return ResponseEntity.created(location).build();
    }
}
