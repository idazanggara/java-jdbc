package com.enigma.jdbc.repository;

// ini yg akan menghandle relasi antara menu dan menu price
// entity kan mewakili menu
// kan enggak bisa nih kita tambahin price lansung
// jadi kalian perlu 1 tambahn desain pattern, namanya DTO = Data Tranfers Object
// kalau entitas menu kan hanya ada menu_id dan menu, nah untu pricernya gimana?
// jadi gunanya DTO itu, jadi kalau semisalnya entitas enggak mampu melakukan sebuah penyimpanan lebih dari satu table, maka dibuat lah yg namanya DTO sesimple data tranfer object jadi cuman numpang doang datanya di dalam suatu class.

//Contohnya gw buat class MenuPrice, yang isinya ada 2 property menu_name dan price aja udah, tujuan untuk menyimpan data nanti akan di tranfer ke query, relasi antara Menu dan MenuPrice

// buat dan bahas DTO dulu, baru balik kesini

import com.enigma.jdbc.dto.MenuDetailRequest;
import com.enigma.jdbc.dto.MenuDetailResponse;

public interface MenuDetailRepository {
	int save (MenuDetailRequest request);

	MenuDetailResponse findByMenuId(Integer menuId);

}
