package com.tata.prueba.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tata.prueba.modelo.Clientes;
import com.tata.prueba.modelo.dao.IClientes;
@RunWith(SpringRunner.class)
@DataJpaTest

public class ClienteAgregar {
	@Autowired
	TestEntityManager entityManager;
	@Autowired 
	IClientes dao;
	
	@Before
	public void setUp() throws Exception {
		Clientes cliente= new Clientes("18859418-2","Franco Andres","Rodriguez Astargo","Franco@gmail.com","945111105");
		this.entityManager.persist(cliente);
	}

	@Test
	public void cuandoInsertaEntoncesRetornaTrue() {
		this.dao.save(new Clientes("18859418-2","Franco Andres","Rodriguez Astargo","Franco@gmail.com","945111105"));
		boolean correcto = this.dao.existsById("18859418-2");
		assertTrue("Es "+correcto+" Pero deberia ser true",correcto);
	}
	
	
	
	
	
	
	

}
