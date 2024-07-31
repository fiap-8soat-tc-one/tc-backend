set schema 'security';
INSERT INTO system (id, name, description, active)
VALUES ('tc-food-backoffice', 'TC Food Backoffice', 'TC Food Backoffice System', true);
INSERT INTO system_module (id, name, description, active, id_system)
VALUES ('TC_FOOD_MODULE', 'TC_FOOD_MODULE', null, true, 'tc-food-backoffice');

INSERT INTO feature (id, name, description, id_system_module, active)
VALUES ('USERS_MAINTENANCE', 'User Maintenance', null, 'TC_FOOD_MODULE', true);

--feature user maintenance
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('REGISTER_USERS', 'USERS_MAINTENANCE', 'REGISTER_USERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('SEARCH_USERS', 'USERS_MAINTENANCE', 'SEARCH_USERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('EDIT_USERS', 'USERS_MAINTENANCE', 'EDIT_USERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('DELETE_USERS', 'USERS_MAINTENANCE', 'DELETE_USERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('LIST_USERS', 'USERS_MAINTENANCE', 'LIST_USERS', null, true);

--feature categories maintenance
INSERT INTO feature (id, name, description, id_system_module, active)
VALUES ('CATEGORIES_MAINTENANCE', 'CATEGORIES_MAINTENANCE', null, 'TC_FOOD_MODULE', true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('REGISTER_CATEGORIES', 'CATEGORIES_MAINTENANCE', 'REGISTER_CATEGORY', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('SEARCH_CATEGORIES', 'CATEGORIES_MAINTENANCE', 'SEARCH_CATEGORIES', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('EDIT_CATEGORIES', 'CATEGORIES_MAINTENANCE', 'REGISTER_CATEGORY', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('DELETE_CATEGORIES', 'CATEGORIES_MAINTENANCE', 'REGISTER_CATEGORY', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('LIST_CATEGORIES', 'CATEGORIES_MAINTENANCE', 'REGISTER_CATEGORY', null, true);

--feature products maintenance
INSERT INTO feature (id, name, description, id_system_module, active)
VALUES ('PRODUCTS_MAINTENANCE', 'PRODUCTS_MAINTENANCE', null, 'TC_FOOD_MODULE', true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('REGISTER_PRODUCTS', 'PRODUCTS_MAINTENANCE', 'REGISTER_PRODUCTS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('SEARCH_PRODUCTS', 'PRODUCTS_MAINTENANCE', 'SEARCH_PRODUCTS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('EDIT_PRODUCTS', 'PRODUCTS_MAINTENANCE', 'EDIT_PRODUCTS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('DELETE_PRODUCTS', 'PRODUCTS_MAINTENANCE', 'DELETE_PRODUCTS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('LIST_PRODUCTS', 'PRODUCTS_MAINTENANCE', 'LIST_PRODUCTS', null, true);

--feature customers maintenance
INSERT INTO feature (id, name, description, id_system_module, active)
VALUES ('CUSTOMERS_MAINTENANCE', 'CUSTOMERS_MAINTENANCE', null, 'TC_FOOD_MODULE', true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('REGISTER_CUSTOMERS', 'CUSTOMERS_MAINTENANCE', 'REGISTER_CUSTOMERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('SEARCH_CUSTOMERS', 'CUSTOMERS_MAINTENANCE', 'SEARCH_CUSTOMERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('EDIT_CUSTOMERS', 'CUSTOMERS_MAINTENANCE', 'EDIT_CUSTOMERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('DELETE_CUSTOMERS', 'CUSTOMERS_MAINTENANCE', 'DELETE_CUSTOMERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('LIST_CUSTOMERS', 'CUSTOMERS_MAINTENANCE', 'LIST_CUSTOMERS', null, true);

--feature orders maintenance
INSERT INTO feature (id, name, description, id_system_module, active)
VALUES ('ORDERS_MAINTENANCE', 'ORDERS_MAINTENANCE', null, 'TC_FOOD_MODULE', true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('REGISTER_ORDERS', 'ORDERS_MAINTENANCE', 'REGISTER_ORDERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('SEARCH_ORDERS', 'ORDERS_MAINTENANCE', 'SEARCH_ORDERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('EDIT_ORDERS', 'ORDERS_MAINTENANCE', 'EDIT_ORDERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('UPDATE_STATUS_ORDERS', 'ORDERS_MAINTENANCE', 'UPDATE_STATUS_ORDERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('DELETE_ORDERS', 'ORDERS_MAINTENANCE', 'DELETE_ORDERS', null, true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('LIST_ORDERS', 'ORDERS_MAINTENANCE', 'LIST_ORDERS', null, true);


--feature payments maintenance
INSERT INTO feature (id, name, description, id_system_module, active)
VALUES ('PAYMENTS_MAINTENANCE', 'PAYMENTS_MAINTENANCE', null, 'TC_FOOD_MODULE', true);
INSERT INTO role (id, id_feature, name, description, active)
VALUES ('PROCESS_PAYMENTS', 'PAYMENTS_MAINTENANCE', 'PROCESS_PAYMENTS', null, true);

INSERT INTO profile (id, name, description, active)
VALUES ('ADMINISTRATOR', 'ADMINISTRATOR', null, true);
INSERT INTO profile (id, name, description, active)
VALUES ('COORDINATOR', 'COORDINATOR', null, true);
INSERT INTO profile (id, name, description, active)
VALUES ('OPERATOR', 'OPERATOR', null, true);

--categories admin roles
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'REGISTER_CATEGORIES');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'SEARCH_CATEGORIES');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'EDIT_CATEGORIES');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'LIST_CATEGORIES');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'DELETE_CATEGORIES');

--products admin roles
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'REGISTER_PRODUCTS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'SEARCH_PRODUCTS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'EDIT_PRODUCTS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'LIST_PRODUCTS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'DELETE_PRODUCTS');

--customers admin roles
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'REGISTER_CUSTOMERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'SEARCH_CUSTOMERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'EDIT_CUSTOMERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'LIST_CUSTOMERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'DELETE_CUSTOMERS');

--orders admin roles
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'REGISTER_ORDERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'SEARCH_ORDERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'EDIT_ORDERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'UPDATE_STATUS_ORDERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'LIST_ORDERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'DELETE_ORDERS');

--payments admin roles
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'PROCESS_PAYMENTS');

--user system admin roles
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'REGISTER_USERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'SEARCH_USERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'EDIT_USERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'DELETE_USERS');
INSERT INTO roles_profile (id_profile, id_role)
VALUES ('ADMINISTRATOR', 'LIST_USERS');

INSERT INTO user_system(uuid, login, name, email, password, document_type, document_number, status)
VALUES ('34848e20-9679-11eb-9e13-0242ac110002', 'myller', 'Myller Sakaguchi', 'myllersakaguchi@gmail.com',
        '$2a$12$LwvLU2yfeDH63i/V7g5aCO.fLzVw0F9l06DEK3kYmh4c6exfWKm2G', 'DOCUMENT', '86079939096', 'ACTIVE');

INSERT INTO user_profile (id_user, id_profile)
VALUES (1, 'ADMINISTRATOR');
