package it.springcataloglogin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.springcataloglogin.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	public Role findRoleByName(String roleName);
}
