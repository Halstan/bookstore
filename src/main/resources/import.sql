insert into editoriales(fecha_creacion, fecha_fundacion, fundador, estado, nombre_editorial) values ('2009-10-01', now(), 'Stan Lee', true, 'DC');

insert into autores(apellido, fecha_modificacion, fecha_nacimiento, nombre_autor, url_foto) VALUES ('Gonzales', now(), now(), 'Pepito', 'aasdasdasdasd');

insert into categorias(nombre_categoria) values ('Terror');
insert into categorias(nombre_categoria) values ('Psicológico');

insert into idiomas(nombre_idioma) values ('Español');
insert into idiomas(nombre_idioma) values ('Inglés');
insert into idiomas(nombre_idioma) values ('Portugués');

insert into roles(nombre_rol) values ('ROLE_USER');
insert into roles(nombre_rol) values ('ROLE_ADMIN');
insert into roles(nombre_rol) values ('ROLE_BIBLIOTECARIO');

insert into usuarios (activado, apellido, contrasenha, fecha_modificacion, nombre, username, sexo)
values (true, 'Arauco', '$2a$10$M1cc7lYCzrQUfQaK0C6RfeEYR0p1GFg1I1Up6gQVlJWHys1JyZxzi', now(), 'Enzo', 'halstan483', 'Masculino');

insert into usuario_rol (id_usuario, id_rol) values(1,2);
insert into usuario_rol (id_usuario, id_rol) values(1,3);

insert into libros(descripcion, estado, fecha_actualizacion, fecha_creacion, fecha_publicacion,
                   nombre_libro, isbn, id_categoria, id_editorial, id_idioma, id_autor, url_portada)
                   VALUES ('Peter Parker era un adolescente que fue mordido por una araña radioctiva', true, now(), now(), now(),
                           'Amazing Spiderman', '78951378465',2, 1, 1, 1, 'https://cdn.discordapp.com/attachments/768482210620768306/772871882286039050/unknown.png');