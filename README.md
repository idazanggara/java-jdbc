# DTO (Data Transfer Object)

```sql
INSERT INTO  m_menu (menu_name) VALUES  ('Cilung');


INSERT INTO m_menu_price(price) VALUES (1000)

```
## DTO Opsi 1

```java
// nah ini kelas bukan? bukan hanya DTO
// tujuan DTO untuk inputan dan query join
// pertanyaan gw, untuk join mungkin enggak dengan 1 inputan aja?
class  MenuDetailDTO {
	private String name;
	private String price;
}
```

ini kan data yg kita imputkan ada 2, nah kalau kita buat 2 entitas itu enggak mungkin. dalam 1 repo parameternya ada 2 untuk mengimputkan menu dan menuPrice jadi jangan seperti ini
nah buatlah kelas tambahan atau cuman penampung jadi buatlah DTO

// ini untuk yg lebih komplex
## DTO Opsi 2
```java
class MenuDetailRequest { // insert/update 
	private  String name;
	private Integer price;
}
```

```java
class MenuDetailResponse { // query Select
	private String menuId;
	private String menuPriceId;
	private String name;
	private Integer price;
}
```