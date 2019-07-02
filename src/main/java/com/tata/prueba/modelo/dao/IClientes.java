package com.tata.prueba.modelo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tata.prueba.modelo.Clientes;

@Repository
public interface IClientes extends JpaRepository<Clientes, String>{
	Optional<Clientes> findByNombres(String nombre);
}
