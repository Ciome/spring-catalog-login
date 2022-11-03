package it.springcataloglogin.dao;

import it.springcataloglogin.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String roleName);
}
