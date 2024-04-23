package com.enigma.jdbc;

import com.enigma.jdbc.entity.Menu;
import com.enigma.jdbc.entity.User;
import com.enigma.jdbc.repository.MenuRepository;
import com.enigma.jdbc.repository.RegisterRepository;
import com.enigma.jdbc.repository.impl.MenuRepositoryImpl;
import com.enigma.jdbc.repository.impl.RegisterRepositoryImpl;

public class Main5RepoPattern {
	public static void main(String[] args) {
//		RegisterRepository regisRepository = new RegisterRepositoryImpl();
//		if (regisRepository.isEmailExist("dazdiz@gmail.com")) {
//			System.out.println("Email already exist");
//		} mungkin bisa kita kasih kondisi dulu sebelum dimasukkin datanya, tapi ini nanti ada. karena ada desain pattern lain yg akan handle ini
//		int count = 0;
//		count += regisRepository.save("Idaz@dazdiz.com");
//		count += regisRepository.save("dazdiz@gmail.com");
//		count += regisRepository.save("dizdaz@diz.com");
//		System.out.println(count);

//		User userById = regisRepository.findById(2);
//		System.out.println(userById.getId());
//		System.out.println(userById.getEmail());
//    System.out.println(userById.getPassword());

		MenuRepository menuRepository = new MenuRepositoryImpl();
		Menu menuById = menuRepository.findById(8);
		System.out.println(menuById.getId());
    System.out.println(menuById.getName());

	}
}
