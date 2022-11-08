package com.spring.boot.service;

import java.util.List;

import com.spring.boot.dto.CategoriaDTO;

public interface CategoriasService {
	
	public List<CategoriaDTO> Listar();
	public CategoriaDTO Guardar(CategoriaDTO categorias);
	public CategoriaDTO BuscarPorID(Long id);
	public CategoriaDTO Actualizar(CategoriaDTO categoriasdto);
    public void Eliminar(Long id);
	
}
