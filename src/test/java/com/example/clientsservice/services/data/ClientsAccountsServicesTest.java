package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.qualifiers.ClientServiceQualifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.example.clientsservice.services.data.ClientServiceTest.generateClients;

//@Transactional
@SpringBootTest
public class ClientsAccountsServicesTest {
    @ClientServiceQualifier
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;

    @Transactional
    @Test
    void saveClientsAccounts() {
        //
        List<Client> clients = generateClients(2);
        clients = clientService.saveAll(clients);
        List<Account> accounts = Arrays.asList(
                new Account(0L, 1000),
                new Account(0L, 1000)
        );
        accounts = accountService.saveAll(accounts);
        for (Client client : clients) {
            for (Account account : accounts) {
                if (client.getAccounts() == null)
                    client.setAccounts(new HashSet<>());
                if (account.getClients() == null)
                    account.setClients(new HashSet<>());
                client.getAccounts().add(account);
                account.getClients().add(client);
            }
        }
        //clients.forEach(client -> System.err.println(client + " " + client.getAccounts()));
        clientService.saveAll(clients);
        //
        clients = clientService.findAll();
        clients.get(0).getAccounts().forEach(System.err::println);
    }

    @Test
    void sessionTest() {
    }
}
