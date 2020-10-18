package com.joenpaco.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joenpaco.daos.ClienteDao;
import com.joenpaco.errors.ClienteNotFoundException;
import com.joenpaco.models.dtos.ClienteDto;
import com.joenpaco.models.entities.Cliente;
import com.joenpaco.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private ClienteDto clienteDto;

	@Override
	public List<ClienteDto> findAll() {
		
		List<ClienteDto> clientesDto = new ArrayList<>();
		
		List<Cliente> clientes = clienteDao.findAll();
		
		clientes.forEach(cliente -> clientesDto.add(new ObjectMapper().convertValue(cliente, ClienteDto.class)));
		
		return clientesDto;
	}

	@Override
	public ClienteDto findById(String id) {
		
		Cliente cliente = clienteDao.findById(Long.parseLong(id)).orElse(null);
		
		if (cliente == null) {
			throw new ClienteNotFoundException("id: " + id + " No Existe");
		}
		
		clienteDto = new ObjectMapper().convertValue(cliente, ClienteDto.class);
		
		return clienteDto;
	}

	@Override
	public void addNewCliente(Cliente cliente) {
		
		clienteDao.save(cliente);
		
	}

	@Override
	public void deleteById(Long id) {
		
		Cliente cliente = new ObjectMapper().convertValue(findById(Long.toString(id)), Cliente.class);
		
		clienteDao.delete(cliente);
		
	}

}
