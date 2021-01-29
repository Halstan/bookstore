
insert into categorias(nombre_categoria) values ('Terror');
insert into categorias(nombre_categoria) values ('Psicológico');

insert into idiomas(nombre_idioma) values ('Español');
insert into idiomas(nombre_idioma) values ('Inglés');
insert into idiomas(nombre_idioma) values ('Portugués');

insert into roles(nombre_rol) values ('ROLE_LECTOR');
insert into roles(nombre_rol) values ('ROLE_ADMIN');
insert into roles(nombre_rol) values ('ROLE_BIBLIOTECARIO');

insert into editoriales(estado, fecha_creacion, fecha_fundacion, fundador, nombre_editorial) VALUES (1, NOW(), NOW(), 'Stan Lee', 'Marvel');

insert into usuarios(activado, apellido, contrasenha, correo, fecha_modificacion, nombre, sexo, username)
    values (TRUE,
            'Arauco Belahonia',
            '$2a$10$tMHEThDQtr9SfxTOcUZw1O73kffFi8W.bv/h3Seglj8PwiKNWm0X6',
            'enzoarauco@gmail.com',
            '2020-12-21',
            'Enzo Daniel',
            'Masculino',
            'halstan483');

insert into usuario_rol (id_usuario, id_rol) values(1,2);
insert into usuario_rol (id_usuario, id_rol) values(1,3);