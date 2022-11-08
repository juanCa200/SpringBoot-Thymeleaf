package com.spring.boot.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.boot.dto.ProductosDTO;
import com.spring.boot.repository.CategoriaRepository;
import com.spring.boot.service.ProductosServiceImpl;


@Controller
@RequestMapping("/")
public class Controlador {
	
	@Autowired
	public ProductosServiceImpl servicio;
	
	@Autowired
	public CategoriaRepository repositorio;
	
	@GetMapping
	public String ListarProductos(Model modelo, @Param("palabra")String palabra) {
		modelo.addAttribute("lista", servicio.ListarConsulta(palabra));
		modelo.addAttribute("categoria", repositorio.findAll());
		return "index";
	}
	
	@GetMapping("/formulario")
	public String MostrarFormularioProductos(Model modelo){
		modelo.addAttribute("productos", new ProductosDTO());
		modelo.addAttribute("product", servicio.Listar());
		modelo.addAttribute("categorias", repositorio.findAll());
		return "form";
	}
	
	@PostMapping("/guardar")
	public String GuardarProductos(@Valid ProductosDTO productosdto, BindingResult bindingresult,@RequestParam(name = "file") MultipartFile imagen) {
		if(bindingresult.hasErrors()) {	
			return "redirect:/formulario";
		}
	    if(!imagen.isEmpty()) {
	    	Path directorioImagenes= Paths.get("src//main//resources//static/images");
	        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
	        
	        try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				productosdto.setImagen(imagen.getOriginalFilename());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    servicio.Guardar(productosdto);
	    return "redirect:/";
	  
	}
	
	@GetMapping("/buscar/{id}")
	public String BuscarPorIdProductos(@PathVariable("id") Long id, Model modelo ) {
	    modelo.addAttribute("productos",servicio.BuscarPorId(id));
		return "form";
	}
	@PostMapping("/actualizar/{id}")
	public String ActualizarProducto(ProductosDTO productos,@PathVariable("id")Long id, Model modelo) {
		ProductosDTO producto = servicio.BuscarPorId(id);
		producto.setNombre(productos.getNombre());
		producto.setCosto(productos.getCosto());
		producto.setFecha(productos.getFecha());
		producto.setCategorias(productos.getCategorias());
		servicio.Guardar(productos);
		return "redirect:/";
	}
	@GetMapping("/eliminar/{id}")
	public String EliminarProducto(@PathVariable("id") Long id) {
		servicio.Eliminar(id);
		return "redirect:/";
	}
	
	
}
