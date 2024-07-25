set schema 'seguranca';
INSERT INTO sistema (id, nome, descricao, fl_ativo)
VALUES ('sesau-front', 'Sistema Sesau', 'Sistema Sesau Ananindeua', true);
INSERT INTO modulo (id, nome, descricao, fl_ativo, id_sistema)
VALUES ('SESAU-SI-PNI', 'SESAU-SI-PNI', null, true, 'sesau-front');

INSERT INTO grupo (id, nome, descricao, id_modulo, fl_ativo)
VALUES ('MANUTENCAO_USUARIOS', 'Manutenção de Usuários', null, 'SESAU-SI-PNI', true);

INSERT INTO permissao (id, id_grupo, nome, descricao, fl_ativo)
VALUES ('CADASTRAR_USUARIO', 'MANUTENCAO_USUARIOS', 'Cadastrar Usuário', null, true);
INSERT INTO permissao (id, id_grupo, nome, descricao, fl_ativo)
VALUES ('CONSULTAR_USUARIO', 'MANUTENCAO_USUARIOS', 'Consultar Usuário', null, true);
INSERT INTO permissao (id, id_grupo, nome, descricao, fl_ativo)
VALUES ('EDITAR_USUARIO', 'MANUTENCAO_USUARIOS', 'Editar Usuário', null, true);
INSERT INTO permissao (id, id_grupo, nome, descricao, fl_ativo)
VALUES ('EXCLUIR_USUARIO', 'MANUTENCAO_USUARIOS', 'Excluir Usuário', null, true);
INSERT INTO permissao (id, id_grupo, nome, descricao, fl_ativo)
VALUES ('LISTAR_USUARIOS', 'MANUTENCAO_USUARIOS', 'Listar Usuários', null, true);

INSERT INTO grupo (id, nome, descricao, id_modulo, fl_ativo)
VALUES ('SINCRONIZACAO_BASE_SI_PNI', 'Sincronizar base SI-PNI', null, 'SESAU-SI-PNI', true);
INSERT INTO permissao (id, id_grupo, nome, descricao, fl_ativo)
VALUES ('SINCRONIZAR_CPF', 'SINCRONIZACAO_BASE_SI_PNI', 'Sincronizar CPF', null, true);

INSERT INTO perfil (id, nome, descricao, fl_ativo)
VALUES ('ADMINISTRADOR', 'Administrador', null, true);
INSERT INTO perfil (id, nome, descricao, fl_ativo)
VALUES ('COORDENADOR', 'Coordenador', null, true);
INSERT INTO perfil (id, nome, descricao, fl_ativo)
VALUES ('OPERADOR', 'Operador', null, true);

INSERT INTO perfil_permissao (id_perfil, id_permissao)
VALUES ('ADMINISTRADOR', 'SINCRONIZAR_CPF');
INSERT INTO perfil_permissao (id_perfil, id_permissao)
VALUES ('COORDENADOR', 'SINCRONIZAR_CPF');
INSERT INTO perfil_permissao (id_perfil, id_permissao)
VALUES ('OPERADOR', 'SINCRONIZAR_CPF');

INSERT INTO perfil_permissao (id_perfil, id_permissao)
VALUES ('ADMINISTRADOR', 'CADASTRAR_USUARIO');
INSERT INTO perfil_permissao (id_perfil, id_permissao)
VALUES ('ADMINISTRADOR', 'CONSULTAR_USUARIO');
INSERT INTO perfil_permissao (id_perfil, id_permissao)
VALUES ('ADMINISTRADOR', 'EDITAR_USUARIO');
INSERT INTO perfil_permissao (id_perfil, id_permissao)
VALUES ('ADMINISTRADOR', 'EXCLUIR_USUARIO');
INSERT INTO perfil_permissao (id_perfil, id_permissao)
VALUES ('ADMINISTRADOR', 'LISTAR_USUARIOS');

INSERT INTO usuario(uuid, login, nome, email, senha, tipo_documento, num_documento, status)
VALUES ('34848e20-9679-11eb-9e13-0242ac110002', 'myller', 'Myller Sakaguchi', 'myllersakaguchi@gmail.com',
        '$2a$12$LwvLU2yfeDH63i/V7g5aCO.fLzVw0F9l06DEK3kYmh4c6exfWKm2G', 'CPF', '86079939096', 'ATIVO');

INSERT INTO usuario_perfil (id_usuario, id_perfil)
VALUES (1, 'ADMINISTRADOR');
