    CREATE SCHEMA seguranca;
    create table seguranca.grupo (
       id varchar(255) not null,
        fl_ativo boolean default true,
        descricao varchar(255),
        nome varchar(255),
        id_modulo varchar(255),
        primary key (id)
    );

    create table seguranca.historico_usuario (
       id  bigserial not null,
        dh_registro timestamp not null,
        tipo_operacao int4 not null,
        id_usuario int8 not null,
        primary key (id)
    );

    create table seguranca.modulo (
       id varchar(255) not null,
        fl_ativo boolean default true,
        descricao varchar(255),
        nome varchar(255),
        id_sistema varchar(255),
        primary key (id)
    );

    create table seguranca.perfil (
       id varchar(255) not null,
        fl_ativo boolean default true,
        descricao varchar(255),
        nome varchar(255),
        primary key (id)
    );

    create table seguranca.perfil_permissao (
       id_perfil varchar(255) not null,
        id_permissao varchar(255) not null,
        primary key (id_perfil, id_permissao)
    );

    create table seguranca.permissao (
       id varchar(255) not null,
        fl_ativo boolean default true,
        descricao varchar(255),
        nome varchar(255),
        id_grupo varchar(255),
        primary key (id)
    );

    create table seguranca.sessao (
       id  bigserial not null,
        dh_inicio timestamp,
        ip_usuario varchar(255),
        qtd_acessos int4,
        nm_servidor varchar(255),
        dh_termino timestamp,
        dh_ultimo_acesso timestamp,
        user_agent varchar(255),
        id_usuario int8,
        primary key (id)
    );

    create table seguranca.sistema (
       id varchar(255) not null,
        fl_ativo boolean default true,
        descricao varchar(255),
        nome varchar(255),
        primary key (id)
    );

    create table seguranca.usuario (
       id  bigserial not null,
        email varchar(255),
        login varchar(255),
        nome varchar(255),
        num_documento varchar(255),
        qtd_tentativas int4,
        senha varchar(255),
        status varchar(100) not null,
        tipo_documento varchar(100) not null,
        dh_ultimo_acesso timestamp,
        uuid uuid,
        primary key (id)
    );

    create table seguranca.usuario_perfil (
       id_usuario int8 not null,
        id_perfil varchar(255) not null,
        primary key (id_usuario, id_perfil)
    );

    create index historico_usuario_index_tipo_operacao on seguranca.historico_usuario (tipo_operacao);
    create index usuario_index_status on seguranca.usuario (status);
    create index usuario_index_tipo_doc on seguranca.usuario (tipo_documento);

    alter table seguranca.grupo
       add constraint FKlgkyvbhhpj6megts7a7ixtgen 
       foreign key (id_modulo) 
       references seguranca.modulo;

    alter table seguranca.historico_usuario 
       add constraint FKnh8qq3y5c0acn2iofqx8gly15 
       foreign key (id_usuario) 
       references seguranca.usuario;

    alter table seguranca.modulo 
       add constraint FKr4cksfb2kutytuqvsh4okkqdf 
       foreign key (id_sistema) 
       references seguranca.sistema;

    alter table seguranca.perfil_permissao 
       add constraint FKnd1t4pihqarwej32h91fg6nob 
       foreign key (id_permissao) 
       references seguranca.permissao;

    alter table seguranca.perfil_permissao 
       add constraint FKk4cd3462ck5un16e2y9n52rwq 
       foreign key (id_perfil) 
       references seguranca.perfil;

    alter table seguranca.permissao 
       add constraint FK7kohvlm012l19e27qie5axvaq 
       foreign key (id_grupo) 
       references seguranca.grupo;

    alter table seguranca.sessao 
       add constraint FKhrj50m90upq4l7xyg935xne1p 
       foreign key (id_usuario) 
       references seguranca.usuario;

    alter table seguranca.usuario_perfil 
       add constraint FK3cxlmf5q4y8mllkos025n9px8 
       foreign key (id_perfil) 
       references seguranca.perfil;

    alter table seguranca.usuario_perfil 
       add constraint FK2qe6tjawyl6ojl32uhbwllldh 
       foreign key (id_usuario) 
       references seguranca.usuario;

    create table category (
       id  serial not null,
        fl_active boolean default true,
        description varchar(255),
        name varchar(255),
        uuid uuid,
        primary key (id)
    );

    create table product (
       id  bigserial not null,
        fl_active boolean default true,
        description varchar(255),
        name varchar(255),
        uuid uuid,
        primary key (id)
    );

    create table product_category (
       id_product int8 not null,
        id_category int4 not null,
        primary key (id_product, id_category)
    );

    create table customer (
        document varchar(11) not null,
        name varchar(20) null,
        email varchar(255) null,
        primary key (document)
    );