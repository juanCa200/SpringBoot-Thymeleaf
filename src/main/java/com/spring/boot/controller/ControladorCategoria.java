package com.spring.boot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.boot.dto.CategoriaDTO;
import com.spring.boot.service.CategoriasServiceImpl;

@Controller
public class ControladorCategoria {

	@Autowired
	public CategoriasServiceImpl serviceimpl;
	
	
	@GetMapping("/categoria")
    public String MostrarFormulario(Model modelo) {
    	modelo.addAttribute("categori", new CategoriaDTO());
    	modelo.addAttribute("lista", serviceimpl.Listar());
    	return "formCategoria";
    }
    @PostMapping("/guardarCategoria")
    public String Guarda(@Valid CategoriaDTO categoriaDTO, Model modelo, BindingResult result){
    	if(result.hasErrors()) {
    		return "redirect:/categoria";
    	}
    	serviceimpl.Guardar(categoriaDTO);
    	return "redirect:/";
    }
    @GetMapping("/categorias/buscar/{id}")
	public String BuscarPorIdProductos(@PathVariable("id") Long id, Model modelo ) {
	    modelo.addAttribute("categori",serviceimpl.BuscarPorID(id));
		return "formCategoria";
	}
	@PostMapping("/categorias/actualizar/{id}")
	public String ActualizarProducto(CategoriaDTO categoria,@PathVariable("id")Long id, Model modelo) {
		CategoriaDTO categoriadto = serviceimpl.BuscarPorID(id);
		categoriadto.setNombre(categoria.getNombre());
		serviceimpl.Guardar(categoriadto);
		return "redirect:/";
	}
	@GetMapping("/categorias/eliminar/{id}")
	public String EliminarProducto(@PathVariable("id") Long id) {
		serviceimpl.Eliminar(id);
		return "redirect:/";
	}
	
}
