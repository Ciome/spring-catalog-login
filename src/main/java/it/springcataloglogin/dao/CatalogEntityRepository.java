package it.springcataloglogin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.springcataloglogin.entity.CatalogEntity;

public interface CatalogEntityRepository extends JpaRepository<CatalogEntity, Integer> {

}
