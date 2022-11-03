package it.springcataloglogin.service;

import java.util.List;

import it.springcataloglogin.entity.CatalogEntity;

public interface CatalogEntityService {
	public List<CatalogEntity> findAll();
	public CatalogEntity findById(int id);
	public void save(CatalogEntity catalogEntity);
	public void deleteById(int id);
}
