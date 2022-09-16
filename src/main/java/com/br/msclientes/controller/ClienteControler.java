package com.br.msclientes.controller;

import com.br.msclientes.dtos.ClienteDTO;
import com.br.msclientes.entities.Cliente;
import com.br.msclientes.servicies.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Log4j2
@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteControler {

    private final ClienteService clienteService;

    @GetMapping("/status")
    public String status(){
        log.info("Verificando o Status da Aplicacao.");
        return "Status cliente ok.";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteDTO clienteDTO){
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        clienteService.save(cliente);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();

        log.info("Cliente salvo {}", cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(headerLocation);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Object> getByCpf(@RequestParam(value = "cpf") String cpf){
        log.info("Consultando o cpf de número {}", cpf);
        var cliente = clienteService.findByCpf(cpf);
        if (!cliente.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não localizado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }
}
