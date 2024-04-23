package com.enigma.jdbc.repository;

import com.enigma.jdbc.entity.User;

// repository itu tempat penyimpanan lah, tempat hubungan antara model dan database.
public interface RegisterRepository {
	// ini bisa void juga, tapi kita pakai int buat dapetin kembalian saat data berhasil kita inputkan, untuk pengecekkan.
	int save(String name);
	// getById, balikan datanya apa sih? detail tentang user kan?
	// isinya apa? (id, email pw) kan
	// nah cuman bisa enggak kita return itu lebih dari satu? enggak kan?
	// maka dari itu kita perlu entity/modelnya.
	// entity itu adalah object yang merepresentasikan tabel-tabel yang ada di database
	// kita buat class baru, buat nama classnya Register di entiity.
	User findById(Integer id);


	// tugas
	// findAll() -> list
	// update(User user -> int
	// deleteById(Integer id) -> int
	// findByName(String name) -> User
	// repo -> customer, table, category
}
