package com.example.clientsservice.controllers.restcontrollers;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.AccountService;
import com.example.clientsservice.services.data.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MainRestController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

//    @PostMapping("/rest/clientForm")
//    public ResponseEntity<?> clientForm(@RequestBody Client client) {
//        System.out.println("hello");
//        System.err.println("user = " + client);
//        clientService.save(client);
//        List<Client> clients = clientService.findAll();
//        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
//    }

    @PostMapping("/rest/clientForm")
    public ResponseEntity<List<Client>> clientForm(@RequestBody Client client) {//@RequestBody Client client-передали клиента из Js
        System.err.println("user = " + client);
        clientService.save(client);
        List<Client> clients = clientService.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);//ответ от сервера назад Js
    }

//    @PostMapping("/rest/accountForm")
//    public ResponseEntity<List<Account>> accountForm(@RequestBody Account account) {
//        System.err.println("account = " + account);
//        accountService.save(account);
//        List<Account> accounts = accountService.findAll();
//        return new ResponseEntity<>(accounts, HttpStatus.OK);
//    }



    // Exception
//    @PostMapping("/rest/accountForm")
//    public ResponseEntity<List<Account>> accountForm(
//                                                     @RequestParam("amount") String amount,
//                                                     @RequestParam("id") String clientId) {
//        System.err.println("clientId = " + clientId);
//        Account account = new Account(0L, Integer.parseInt(amount));
//        accountService.save(account);
//        List<Account> accounts = accountService.findAll();
//        return new ResponseEntity<>(accounts, HttpStatus.OK);
//    }



    @PostMapping("/rest/accountForm")
    public ResponseEntity<List<Account>> accountForm(@RequestBody Map<String, Object> payload) {
       //  System.err.println("clientId = " + clientId);
        int amount = Integer.parseInt(payload.get("amount").toString());
        Account account = new Account(0L, amount);
        long clientId = Long.parseLong(payload.get("client_id").toString());
        account.getClients().add(clientService.findById(clientId));
        accountService.save(account);
        List<Account> accounts = accountService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }




}
