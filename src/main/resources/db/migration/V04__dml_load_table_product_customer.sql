set schema 'public';
--insert lanches
INSERT INTO product(id_category, uuid, name, description, price, register_date)
VALUES (1, 'b1f859e6-07df-4b67-a1cd-74d946442207', 'x-salada', 'x-salada', 30.50, now());
INSERT INTO product(id_category, uuid, name, description, price, register_date)
VALUES (1, 'ea1697ef-b794-45c0-b2bb-ebd36803ece3', 'x-tudo', 'x-salada', 40.85, now());

--insert acompanhamentos
INSERT INTO product(id_category, uuid, name, description, price, register_date)
VALUES (2, '68a589ce-979f-4350-bcd6-ca049f3beb16', 'batata frita', 'batata frita 100g', 10.75, now());
INSERT INTO product(id_category, uuid, name, description, price, register_date)
VALUES (2, '3f6b7762-8798-4c45-a3f6-105406e8baaf', 'porção onions rings 100g', 'porção onions rings 100g', 15.25, now());

--insert bebida
INSERT INTO product(id_category, uuid, name, description, price, register_date)
VALUES (3, '56199d36-969b-4e1b-9515-f84ffed6a19b', 'refrigerante', 'refrigerante coca cola zero lata 350 ml', 8.50, now());
INSERT INTO product(id_category, uuid, name, description, price, register_date)
VALUES (3, 'a763f6ce-1122-434b-9721-b348db2894e6', 'refrigerante', 'refrigerante coca cola lata 350 ml', 8.50, now());

--insert sobremesa
INSERT INTO product(id_category, uuid, name, description, price, register_date)
VALUES (4, '7b3c010c-9f03-4a56-8c85-b519a5f6b86e', 'pudim', 'pudim de leite 100g', 12.75, now());
INSERT INTO product(id_category, uuid, name, description, price, register_date)
VALUES (4, 'f6d7f266-33d9-42aa-a2ee-63d2e08f5176', 'bolo', 'bolo chocolate 100g' , 13.45, now());

--insert customer
INSERT INTO customer(uuid, name, document, email, register_date)
VALUES ('3fa85f64-5717-4562-b3fc-2c963f66afa6', 'Myller Sakaguchi', '88404071039', 'myllersakaguchi@gmail.com', now());