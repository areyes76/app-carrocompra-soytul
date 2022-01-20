package cl.app.arg.compra.service;

import cl.app.arg.compra.model.Cliente;
import cl.app.arg.compra.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        clienteRepository.findAll().forEach(cliente -> clientes.add(cliente));
        return clientes;
    }

    public Cliente getById(Long idCliente) {
        return clienteRepository.findById(idCliente).get();
    }

    @Transactional(readOnly = false)
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    @Transactional(readOnly = false)
    public Cliente update(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

}
