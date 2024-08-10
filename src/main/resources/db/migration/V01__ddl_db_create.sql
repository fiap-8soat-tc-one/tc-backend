CREATE SCHEMA security;

create table security.feature
(
    id               varchar(255) not null,
    active           boolean default true,
    description      varchar(255),
    name             varchar(255),
    id_system_module varchar(255),
    primary key (id)
);

create table security.profile
(
    id          varchar(255) not null,
    active      boolean default true,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);

create table security.role
(
    id          varchar(255) not null,
    active      boolean default true,
    description varchar(255),
    name        varchar(255),
    id_feature  varchar(255),
    primary key (id)
);

create table security.roles_profile
(
    id_profile varchar(255) not null,
    id_role    varchar(255) not null,
    primary key (id_profile, id_role)
);

create table security.system
(
    id          varchar(255) not null,
    active      boolean default true,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);

create table security.system_module
(
    id          varchar(255) not null,
    active      boolean default true,
    description varchar(255),
    name        varchar(255),
    id_system   varchar(255),
    primary key (id)
);

create table security.user_profile
(
    id_user    int8         not null,
    id_profile varchar(255) not null,
    primary key (id_user, id_profile)
);

create table security.user_system
(
    id                   bigserial    not null,
    document_number      varchar(255),
    document_type        varchar(100) not null,
    email                varchar(255) not null,
    last_access          timestamp,
    login                varchar(255),
    name                 varchar(255) not null,
    password             varchar(255) not null,
    qty_invalid_attempts int4,
    status               varchar(100) not null,
    uuid                 uuid,
    primary key (id)
);

create index user_index_status on security.user_system (status);
create index user_index_doc_type on security.user_system (document_type);

alter table security.feature
    add constraint FK2avs0t4ujifn6olnuow5t9fdn
        foreign key (id_system_module)
            references security.system_module;

alter table security.role
    add constraint FKeund6rnm60un45wi6ba0cvacc
        foreign key (id_feature)
            references security.feature;

alter table security.roles_profile
    add constraint FK56qbos2s4oknbkevqjsa56lr0
        foreign key (id_role)
            references security.role;

alter table security.roles_profile
    add constraint FKc0w2w2eux8lbajydghtqsmyny
        foreign key (id_profile)
            references security.profile;

alter table security.system_module
    add constraint FK6aueaexykvybh6xhr0hma6l7r
        foreign key (id_system)
            references security.system;

alter table security.user_profile
    add constraint FK78bw1kv69go0c2tr0jv08dcjx
        foreign key (id_profile)
            references security.profile;

alter table security.user_profile
    add constraint FKcspgan7a5397ba6me049ukbhj
        foreign key (id_user)
            references security.user_system;

create table category
(
    id            serial not null,
    active        boolean default true,
    register_date timestamp,
    updated_date  timestamp,
    description   varchar(255),
    name          varchar(255),
    uuid          uuid,
    primary key (id)
);

create table customer
(
    id            serial not null,
    active        boolean default true,
    register_date timestamp,
    updated_date  timestamp,
    document      varchar(20),
    email         varchar(255),
    name          varchar(255),
    uuid          uuid,
    primary key (id)
);

create table item
(
    id            serial not null,
    active        boolean default true,
    register_date timestamp,
    updated_date  timestamp,
    quantity      int4,
    total         numeric(19, 2),
    unit_value    numeric(19, 2),
    id_order      int4   not null,
    id_product    int4   not null,
    primary key (id)
);

create table order_payment
(
    id                   serial      not null,
    active               boolean default true,
    register_date        timestamp,
    updated_date         timestamp,
    payment_type         varchar(20) not null,
    result               varchar(20) not null,
    result_error_date    timestamp,
    result_success_date  timestamp,
    total                numeric(19, 2),
    transaction_document varchar(30) not null,
    transaction_message  varchar(255),
    transaction_number   varchar(255),
    uuid                 uuid,
    id_order             int4        not null,
    primary key (id)
);

create table order_payment_historic
(
    id               serial      not null,
    register_date    timestamp   not null,
    result           varchar(20) not null,
    id_order_payment int4        not null,
    primary key (id)
);

create table order_request (
       id int4 not null,
        active boolean default true,
        register_date timestamp,
        updated_date timestamp,
        order_number varchar(255),
        status varchar(255) not null,
        total numeric(19, 2),
        uuid uuid,
        id_customer int4,
        primary key (id)
    );

create table order_request_historic
(
    id            serial       not null,
    register_date timestamp    not null,
    status        varchar(255) not null,
    id_order      int4,
    primary key (id)
);

create table product
(
    id            serial not null,
    active        boolean default true,
    register_date timestamp,
    updated_date  timestamp,
    description   varchar(255),
    name          varchar(255),
    price         numeric(19, 2),
    uuid          uuid,
    id_category   int4   not null,
    primary key (id)
);

create sequence order_request_id_seq start 1 increment 1;

alter table customer
    add constraint UK_phlle50dp6ivt0paa1d5gkvk2 unique (document);

alter table item
    add constraint FKmvybm38wikbsa2eh5vcgq2k8j
        foreign key (id_order)
            references order_request;

alter table item
    add constraint FKf9b4g1jcujxd4mgfyqt54sys2
        foreign key (id_product)
            references product;

alter table order_payment
    add constraint FK22ae8c8eusj6bcfeep1jp8l7s
        foreign key (id_order)
            references order_request;

alter table order_payment_historic
    add constraint FKlb7k4tfm886jv3g9urhhwtqtu
        foreign key (id_order_payment)
            references order_payment;

alter table order_request
    add constraint FK4ipjohfnii23loku21jgm6fxr
        foreign key (id_customer)
            references customer;

alter table order_request_historic
    add constraint FKiql9vtdg3qadyne7uy26d9a5v
        foreign key (id_order)
            references order_request;

alter table product
    add constraint FK5cxv31vuhc7v32omftlxa8k3c
        foreign key (id_category)
            references category;