package com.company.appli.controller;

import com.company.appli.model.AccountCreateRequest;
import com.company.appli.model.Account;
import com.company.appli.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/security/admin")
public class AccountController {

    @Autowired
    @Qualifier("localService")
    AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{domaine}/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String domaine, @PathVariable String id) {
        Optional<Account> account = accountService.findById(id);
        return account.map(acc -> ResponseEntity.ok(acc)).orElse(ResponseEntity.notFound().build());


    }

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{domaine}")
    public ResponseEntity<List<Account>> getAccounts(@PathVariable String domaine) {
        List<Account> accounts = accountService.findAllByDomaine(domaine);
        return ResponseEntity.ok(accounts);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/accounts")
    @ApiOperation(tags = "Gestion des comptes", value = "Création du compte")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Compte créé"),
            @ApiResponse(code = 500, message = "Erreur interne"),
            @ApiResponse(code = 401, message = "Non authentifié"),
            @ApiResponse(code = 403, message = "Non authorisé")})
    public ResponseEntity<Object> createAccount(@RequestBody AccountCreateRequest accountCreateRequest, HttpServletRequest request) {
        System.out.println(accountCreateRequest);
        ///Creation du compte

        Account account = new Account(UUID.randomUUID().toString(), accountCreateRequest.getDomaine(),
                accountCreateRequest.getEmail(),
                accountCreateRequest.getName(),
                accountCreateRequest.getFirstName());
        String id = accountService.createAccount(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{domaine}/{id}")
                .buildAndExpand(accountCreateRequest.getDomaine(), id)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
