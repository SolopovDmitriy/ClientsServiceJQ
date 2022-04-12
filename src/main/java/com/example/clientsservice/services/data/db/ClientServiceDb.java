package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.repositories.AccountRepository;
import com.example.clientsservice.repositories.ClientRepository;
import com.example.clientsservice.services.data.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceDb implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Client> findAllBySurnameAndNameAndPatronymic(String surname, String name,
                                                             String patronymic) {
        return clientRepository.findAllBySurnameAndNameAndPatronymic(
                surname, name,patronymic);
    }

    @Override
    public Client save(Client client){
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    @Override
    public List<Client> saveAll(List<Client> clients) {
        return clientRepository.saveAll(clients);
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws Exception {
        Client client = clientRepository.findById(id).get();
        if(client.getAccounts().isEmpty()){
            clientRepository.deleteById(id);
        }else{
            throw new Exception("Delete all client's accounts first");
        }
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).get();
    }
}
