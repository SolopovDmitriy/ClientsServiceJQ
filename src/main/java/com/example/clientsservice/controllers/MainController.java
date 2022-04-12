package com.example.clientsservice.controllers;

import com.example.clientsservice.controllers.tools.BootstrapManager;
import com.example.clientsservice.models.Account;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.models.Client.Gender;
import com.example.clientsservice.services.data.AccountService;
import com.example.clientsservice.services.data.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MainController {
    //@ClientServiceQualifier
    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String main(Model model) {
        BootstrapManager.setBoostrapHead(model);
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        if (clients.isEmpty())
            model.addAttribute("no-clients", true);
        return "main";
    }

    @GetMapping("main")
    public String main() {
        return "redirect:";
    }



    @GetMapping("/add_account")
    public String addAccount(Model model) {
        BootstrapManager.setBoostrapHead(model);
        List<Account> accounts = accountService.findAll();
        model.addAttribute("accounts", accounts);
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "accounts"; // accounts.mustache
    }


    @GetMapping("`")
    public String mainClear(){
        clientService.deleteAll();
        return "redirect:";
    }

    @PostMapping("add_client")
    public String addClient(Model model,
                            @RequestParam("surname") String surname,
                            @RequestParam("name") String name,
                            @RequestParam("patronymic") String patronymic,
                            @RequestParam("email") String email,
                            @RequestParam("birthDate") String birthDate,
                            @RequestParam("gender") Gender gender
    ) {
        System.err.println(gender);
        Client client = new Client(0L, surname, name, patronymic, email,
                LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                gender
        );
        clientService.save(client);
        return "redirect:";
    }
}
