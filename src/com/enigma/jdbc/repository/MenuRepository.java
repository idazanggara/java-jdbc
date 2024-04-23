package com.enigma.jdbc.repository;

import com.enigma.jdbc.entity.Menu;

public interface MenuRepository {
	int save(String name);
	Menu findById(Integer id);
}
