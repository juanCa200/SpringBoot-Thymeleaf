package com.spring.boot.dto;

import com.spring.boot.entity.Categoria;

public class ProductosDTO {

	private long id;
	private String nombre;
	private String costo;
	private String Fecha;
	private String imagen;
	private Categoria categorias;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCosto() {
		return costo;
	}
	public void setCosto(String costo) {
		this.costo = costo;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Categoria getCategorias() {
		return categorias;
	}
	public void setCategorias(Categoria categorias) {
		this.categorias = categorias;
	}
	
	
	
}
