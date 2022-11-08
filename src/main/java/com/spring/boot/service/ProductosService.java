package com.spring.boot.service;

import java.util.List;

import com.spring.boot.dto.ProductosDTO;

public interface ProductosService {

	public List<ProductosDTO> ListarConsulta(String palabra);
	public List<ProductosDTO> Listar();
	public ProductosDTO Guardar(ProductosDTO productos);
	public ProductosDTO BuscarPorId(Long id);
	public ProductosDTO Actualizar(ProductosDTO productos);
	public void Eliminar(Long id);

	
}
