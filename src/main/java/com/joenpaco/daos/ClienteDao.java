package com.joenpaco.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joenpaco.models.entities.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Long> {

}
