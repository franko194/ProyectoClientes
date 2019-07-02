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
public class ClienteEliminar {
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
	public void cuandoElimino1Entoncestrue() {
		this.dao.deleteById("18859418-2");
		boolean correcto = this.dao.existsById("18859418-2");
		assertTrue("el cliente "+correcto+" deberia ser true",!correcto);
	}
	@Test
	public void cuandoNoEliminoEntoncesFalse() {
		boolean eliminado = false;
		
		boolean existe = this.dao.existsById("18859418-5");
		if (existe) {
			this.dao.deleteById("18859418-5");
			eliminado = true;
		}
		
		assertFalse("el cliente "+eliminado+" deberia ser false",eliminado);
	}

}
