package com.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.boot.dto.ProductosDTO;
import com.spring.boot.entity.Destacados;
import com.spring.boot.repository.DestacadosRepository;
import com.spring.boot.service.ProductosServiceImpl;

@Controller
public class ControladorDestacado {

	@Autowired
	public ProductosServiceImpl servicio;
	
	@Autowired
	public DestacadosRepository destacados;
	
	@GetMapping("/destacado/{id}")
	public String Destacado(@PathVariable("id") Long id, Model modelo) {
		ProductosDTO productos = servicio.BuscarPorId(id);
		Destacados destacar = new Destacados();
		destacar.setNombre(productos.getNombre());
		destacar.setCosto(productos.getCosto());
		destacar.setImagen(productos.getImagen());
		destacados.save(destacar);
		return "redirect:/";	
	}
	@GetMapping("/vista/destacados")
	public String DestacadoVista(Model modelo){
		modelo.addAttribute("destacados", destacados.findAll());
		return "destacados";
	}
	@GetMapping("/eliminar/destacado/{id}")
	public String EliminarDestacado(@PathVariable("id") Long id) {
		destacados.deleteById(id);
		return "redirect:/";
	}
	
}
