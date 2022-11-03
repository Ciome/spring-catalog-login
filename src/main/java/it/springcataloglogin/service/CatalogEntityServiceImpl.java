package it.springcataloglogin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.springcataloglogin.dao.CatalogEntityRepository;
import it.springcataloglogin.entity.CatalogEntity;

@Service
public class CatalogEntityServiceImpl implements CatalogEntityService {
	
	private CatalogEntityRepository repository;
	
	@Autowired
	public CatalogEntityServiceImpl(CatalogEntityRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<CatalogEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public CatalogEntity findById(int id) {
		Optional<CatalogEntity> entityOptional = repository.findById(id);
		if(entityOptional.isEmpty())
			throw new RuntimeException("Did not find entity with id: " + id);

		return entityOptional.get();
	}

	@Override
	public void save(CatalogEntity catalogEntity) {
		repository.save(catalogEntity);
	}

	@Override
	public void deleteById(int id) {
		repository.deleteById(id);
	}

}
