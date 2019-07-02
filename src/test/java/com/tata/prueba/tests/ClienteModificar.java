package com.tata.prueba.tests;

import static org.junit.Assert.*;

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
public class ClienteModificar {
	@Autowired
	TestEntityManager entityManager;
	@Autowired 
	IClientes dao;
	@Before
	public void setUp() throws Exception {
		Clientes cliente1= new Clientes("18859418-2","Franco Andres","Rodriguez Astargo","Franco@gmail.com","945111105");
		this.entityManager.persist(cliente1);
		Clientes cliente2= new Clientes("12545866-5","Rosita","Morales","Rosita@gmail.com","955845854");
		this.entityManager.persist(cliente2);
		Clientes cliente3= new Clientes("98854475-5","John","Cena","Cena@gmail.com","898545857");
		this.entityManager.persist(cliente3);
	}

	@Test
	public void cuandoModificaNumeroEntoncesDebeSer111111111YTrue() {
		Clientes cliente= this.dao.save(new Clientes("18859418-2","Franco Andres","Rodriguez Astargo","Franco@gmail.com","111111111"));
		assertTrue("deberia ser 945111105 el numero" + cliente.getCelular() +" y es ", cliente.getCelular().equals("111111111"));;
	}
	
	@Test
	public void cuandoModificaNumeroEntoncesDebeSerFalse() {
		boolean modificado = false;
		Clientes cliente= new Clientes("18859418-5","Franco Andres","Rodriguez Astargo","Franco@gmail.com","111111111");
		
		if (this.dao.existsById("18859418-5")) {
			this.dao.save(cliente);
			modificado = true;
		}
		
		assertFalse(modificado);
	}
	

	

}