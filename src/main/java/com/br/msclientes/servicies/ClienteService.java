package com.br.msclientes.servicies;

import com.br.msclientes.entities.Cliente;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ClienteService {
    void save(Cliente cliente);

    Optional<Cliente> findByCpf(String cpf);
}
