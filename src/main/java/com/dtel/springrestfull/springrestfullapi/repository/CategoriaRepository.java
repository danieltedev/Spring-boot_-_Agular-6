package com.dtel.springrestfull.springrestfullapi.repository;

import com.dtel.springrestfull.springrestfullapi.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoriaRepository
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}