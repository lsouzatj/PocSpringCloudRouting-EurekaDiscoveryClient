package com.br.msclientes.servicies.implement;

import com.br.msclientes.entities.Cliente;
import com.br.msclientes.repositories.ClienteRepository;
import com.br.msclientes.servicies.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    @Override
    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    @Override
    public Optional<Cliente> findByCpf(String cpf) {
        return Optional.ofNullable(clienteRepository.findByCpf(cpf));
    }
}
