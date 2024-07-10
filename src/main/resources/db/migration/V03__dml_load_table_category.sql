set schema 'public';
INSERT INTO category(uuid, name, description) VALUES (gen_random_uuid(),'Lanche', 'Categoria de Lanches');
INSERT INTO category(uuid, name, description) VALUES (gen_random_uuid(),'Acompanhamento', 'Categoria de Acompanhamento');
INSERT INTO category(uuid, name, description) VALUES (gen_random_uuid(),'Bebida', 'Categoria de Bebida');
INSERT INTO category(uuid, name, description) VALUES (gen_random_uuid(),'Sobremesa', 'Categoria de Sobremesa');
