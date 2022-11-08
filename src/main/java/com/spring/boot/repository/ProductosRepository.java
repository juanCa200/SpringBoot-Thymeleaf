package com.spring.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos,Long>{
	
	@Query(value = "SELECT * FROM productos WHERE nombre Like %?1%",
			nativeQuery = true)
	public List<Productos> findAll(String palabra);
	
}
