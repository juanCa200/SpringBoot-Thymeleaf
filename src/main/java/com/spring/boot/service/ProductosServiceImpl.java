package com.spring.boot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.ProductosDTO;
import com.spring.boot.entity.Productos;
import com.spring.boot.repository.ProductosRepository;

@Service
public class ProductosServiceImpl implements ProductosService {

	@Autowired
	public ProductosRepository repositorio;

	@Autowired
	public ModelMapper mapper;

	@Override
	public List<ProductosDTO> ListarConsulta(String palabra) {
		if(palabra!=null) {
			return repositorio.findAll(palabra)
					.stream()
					.map(this::MapearDTO)
					.collect(Collectors.toList());
		}
		return repositorio.findAll()
				.stream()
				.map(this::MapearDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<ProductosDTO> Listar() {
		return repositorio.findAll()
				.stream()
				.map(this::MapearDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ProductosDTO Guardar(ProductosDTO productosdto) {
		Productos productos = MapearEntidad(productosdto);
		Productos guardar = repositorio.save(productos);
		return MapearDTO(guardar);
	}

	@Override
	public ProductosDTO BuscarPorId(Long id) {
		Productos productos = repositorio.findById(id).get();
		ProductosDTO productosdto = MapearDTO(productos);
		return productosdto;
	}

	@Override
	public ProductosDTO Actualizar(ProductosDTO productosdto) {
		Productos productos = MapearEntidad(productosdto);
		Productos guardar = repositorio.save(productos);
		return MapearDTO(guardar);
	}

	@Override
	public void Eliminar(Long id) {
		repositorio.deleteById(id);
	}

	public Productos MapearEntidad(ProductosDTO productosdto) {
		Productos productos = mapper.map(productosdto, Productos.class);
		return productos;
	}

	public ProductosDTO MapearDTO(Productos productos) {
		ProductosDTO productosDTO = mapper.map(productos, ProductosDTO.class);
		return productosDTO;
	}


}
