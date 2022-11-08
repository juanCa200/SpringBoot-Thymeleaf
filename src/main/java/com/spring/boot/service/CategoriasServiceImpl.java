package com.spring.boot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.CategoriaDTO;
import com.spring.boot.entity.Categoria;
import com.spring.boot.repository.CategoriaRepository;

@Service
public class CategoriasServiceImpl implements CategoriasService {

	@Autowired
	public CategoriaRepository repositorio;
	
	@Autowired
	public ModelMapper mapeo;
	
	@Override
	public List<CategoriaDTO> Listar() {
		return repositorio.findAll()
			.stream()
			.map(this::MapearDTO)
			.collect(Collectors.toList());
	}

	@Override
	public CategoriaDTO Guardar(CategoriaDTO categorias) {
		Categoria categori = MapearEntidad(categorias);
		Categoria guardar = repositorio.save(categori);
		return MapearDTO(guardar);
	}

	@Override
	public CategoriaDTO BuscarPorID(Long id) {
		Categoria categoria = repositorio.findById(id).get();
		CategoriaDTO categoriadto = MapearDTO(categoria);
		return categoriadto;
	}

	@Override
	public CategoriaDTO Actualizar(CategoriaDTO categoriasdto) {
		Categoria categoria = MapearEntidad(categoriasdto);
		Categoria guardar = repositorio.save(categoria);
		return MapearDTO(guardar);
	}

	@Override
	public void Eliminar(Long id) {
		repositorio.deleteById(id);
	}
	
	public Categoria MapearEntidad(CategoriaDTO categoriasdto) {
		Categoria categorias = mapeo.map(categoriasdto, Categoria.class);
		return categorias;
	}
	
	public CategoriaDTO MapearDTO(Categoria categoria) {
		CategoriaDTO categoriadto = mapeo.map(categoria, CategoriaDTO.class);
        return categoriadto;		
	}
	

}
