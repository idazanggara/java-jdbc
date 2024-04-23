package com.enigma.jdbc;

import com.enigma.jdbc.dto.MenuDetailRequest;
import com.enigma.jdbc.dto.MenuDetailResponse;
import com.enigma.jdbc.repository.impl.MenuDetailRepositoryImpl;

public class Main6Transaction {
	public static void main(String[] args) {
		MenuDetailRepositoryImpl menuDetailRepository = new MenuDetailRepositoryImpl();
//		MenuDetailRequest request = new MenuDetailRequest(
//						"Siomay Bogo",
//						10000F
//		);
//		menuDetailRepository.save(request);

		MenuDetailResponse menuDetailResponse = menuDetailRepository.findByMenuId(22);
		System.out.println(menuDetailResponse);
		System.out.println(menuDetailResponse.getMenuId());
    System.out.println(menuDetailResponse.getMenuPriceId());
    System.out.println(menuDetailResponse.getName());
		System.out.println(menuDetailResponse.getPrice());
	}
}
