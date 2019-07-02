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
public class ListarClientes {
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
	public void cuandoListoTodoEntonces3Registros() {
		int largo = this.dao.findAll().size();
		
		assertTrue("el"+ largo +" deberia ser 3 pero es ",largo==3);
	}
	
	@Test
	public void cuandoAgrego1Entonces4Registros() {
		this.dao.save(new Clientes("22222222-2"," Andres","Rodriguez ","Andres@gmail.com","58458695"));
		int largo = this.dao.findAll().size();
		
		assertTrue("el"+ largo +" deberia ser 4 pero es ",largo==4);
	}
	@Test
	public void cuandoElimino1Entonces2Registros() {
		this.dao.deleteById("18859418-2");
		int largo = this.dao.findAll().size();
		
		assertTrue("el"+ largo +" deberia ser 2 registros pero son ",largo==2);
	}
	
	@Test
	public void cuandoLaListaEstaVaciaEntonces0() {
		this.dao.deleteAll();
		int largo = this.dao.findAll().size();		
		assertTrue("el "+ largo +" deberia ser 0 pero es ",largo==0);
	}
	

}
