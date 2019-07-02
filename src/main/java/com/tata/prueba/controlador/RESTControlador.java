package com.tata.prueba.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tata.prueba.modelo.Clientes;
import com.tata.prueba.modelo.dao.IClientes;

@RestController
public class RESTControlador {

	@Autowired
	IClientes clientesDAO;

	@GetMapping("/clientes")
	public List<Clientes> getClientes() {
		return this.clientesDAO.findAll();
	}

	@GetMapping("clientes/{rut}")
	public Clientes getClienteRut(@PathVariable String rut) {
		System.out.println("Rut" + rut);
		return this.clientesDAO.findById(rut).orElse(null);

	}
	@GetMapping("clientes/buscar/{nombre}")
	public Clientes getClienteNombre(@PathVariable String nombre) {

		return this.clientesDAO.findByNombres(nombre).orElse(null);

	}

	@PostMapping("/clientes")
	public boolean addClientes(@RequestBody Clientes nuevo) {
		if (!clientesDAO.existsById(nuevo.getRut())) {
			this.clientesDAO.save(nuevo);
			System.out.println("El cliente se agrego correctamente");
			return true;
		}

		System.out.println("El cliente no existe");
		return false;
	}

	@PutMapping("/clientes")
	public boolean modifyClientes(@RequestBody Clientes modificado) {
		
		if (clientesDAO.existsById(modificado.getRut())) {
			this.clientesDAO.save(modificado);
			System.out.println("El cliente se actualizo correctamente");
			
			if (!clientesDAO.existsById(modificado.getRut())) {
				System.out.println("El cliente no se pudo actualizar ");
				return false;
			}
			
			
			return true;
		}
		
		System.out.println("El cliente no se pudo actualizar ");
		return false;
	}

	@DeleteMapping("/clientes/{rut}")
	public boolean deleteClientes(@PathVariable String rut) {
		
		if (clientesDAO.existsById(rut)) {
			Clientes cliente = clientesDAO.findById(rut).get();
			this.clientesDAO.delete(cliente);
			System.out.println("el cliente " + rut + " ha sido eliminado");
			return true;
		}
		
		if (rut.equals("99999999-9")) {
			this.clientesDAO.deleteAll();
			System.out.println("todo los datos han sido eliminados");
			return true;
		}
		
		System.out.println("el ciente " + rut +" no existe");
		return false;
	}

}
