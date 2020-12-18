
insert into categorias(nombre_categoria) values ('Terror');
insert into categorias(nombre_categoria) values ('Psicológico');

insert into idiomas(nombre_idioma) values ('Español');
insert into idiomas(nombre_idioma) values ('Inglés');
insert into idiomas(nombre_idioma) values ('Portugués');

insert into roles(nombre_rol) values ('ROLE_USER');
insert into roles(nombre_rol) values ('ROLE_ADMIN');
insert into roles(nombre_rol) values ('ROLE_BIBLIOTECARIO');

insert into usuarios(activado, apellido, contrasenha, correo, fecha_modificacion, nombre, sexo, username)
    values (true, 'Arauco Belahonia', '$2a$10$M1cc7lYCzrQUfQaK0C6RfeEYR0p1GFg1I1Up6gQVlJWHys1JyZxzi', 'enzoarauco@gmail.com', now(), 'Enzo Daniel', 'halstan483', 'Masculino');

insert into usuario_rol (id_usuario, id_rol) values(1,2);
insert into usuario_rol (id_usuario, id_rol) values(1,3);