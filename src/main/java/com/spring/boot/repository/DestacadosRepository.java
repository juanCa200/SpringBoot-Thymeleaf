package com.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Destacados;

@Repository
public interface DestacadosRepository extends JpaRepository<Destacados,Long>{

}
