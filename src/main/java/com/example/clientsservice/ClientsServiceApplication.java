package com.example.clientsservice;

import com.example.clientsservice.services.data.AccountService;
import com.example.clientsservice.services.data.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ClientsServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(ClientsServiceApplication.class, args);
//        WebMvcConfig webMvcConfig = context.getBean(WebMvcConfig.class);
    }
    /*
    @Autowired
    private ClientRepository clientRepository;
    //    @Qualifier("clientServiceJson")
    @Autowired
    @ClientServiceQualifier
    private ClientService clientService;
    */

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {

//        Client Dima = new Client(1L, "Solopov", "Dima", "Ivanovich",
//                        "admin@domain.com", LocalDate.now(), Client.Gender.MALE, null);
//
//        Client Julia = new Client(2L, "Solopova", "Julia", "Mikhailivna",
//                "admin@domain.com",  LocalDate.now(), Client.Gender.MALE, null);
//
//        Account grnD = new Account(1L, 10000);
//
//        Account grnJ = new Account(2L, 10000);
//
//        Account dollarFamily = new Account(3L, 10000);
//
//        accountService.save(grnD);
//        accountService.save(grnJ);
//        accountService.save(dollarFamily);
//
//        clientService.save(Dima);
//        clientService.save(Julia);
//
//        // Relations
//
//        Dima.getAccounts().add(grnD);
//        Julia.getAccounts().add(grnJ);
//        Julia.getAccounts().add(dollarFamily);
//        Dima.getAccounts().add(dollarFamily);
//
//        clientService.save(Dima);
//        clientService.save(Julia);
//
//
//        accountService.deleteById(1L);
//        accountService.deleteById(3L);
//        try {
//            clientService.deleteById(1L);
//        }catch (Exception e){
//            System.out.println("DELETE ALL CLIENT`S  ACCOUNTS FIRST" + e.getMessage());
//        }




    }
}
