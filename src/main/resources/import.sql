insert into editoriales(fecha_creacion, fecha_fundacion, fundador, estado, nombre_editorial) values ('2009-10-01', NOW(), 'Stan Lee', 1,'DC');

insert into autores(apellido, fecha_creacion, fecha_nacimiento, nombre_autor) VALUES ('Gonzales', NOW(), NOW(), 'Pepito');

insert into sexos(nombre_sexo) value ('Femenino');
insert into sexos(nombre_sexo) value ('Masculino');

insert into categorias(nombre_categoria) values ('Terror');
insert into categorias(nombre_categoria) values ('Psicológico');

insert into idiomas(nombre_idioma) values ('Español');
insert into idiomas(nombre_idioma) values ('Inglés');
insert into idiomas(nombre_idioma) values ('Portugués');

insert into roles(nombre_rol) values ('ROLE_USER');
insert into roles(nombre_rol) values ('ROLE_ADMIN');
insert into roles(nombre_rol) values ('ROLE_BIBLIOTECARIO');

insert into usuarios (activado, apellido, contrasenha, fecha_creacion, nombre, username, id_sexo)
values (1, 'Arauco', '12345', NOW(), 'Enzo', 'halstan', 1);

insert into libros(descripcion, estado, fecha_actualizacion, fecha_creacion, fecha_publicacion, nombre_libro, isbn, id_categoria, id_editorial, id_idioma, id_autor, url_portada) VALUES ('Peter Parker era un adolescente que fue mordido por una araña radioctiva', 1, NOW(), NOW(), NOW(), 'Amazing Spiderman', '78951378465',2, 1, 1, 1, 'https://cdn.discordapp.com/attachments/768482210620768306/772871882286039050/unknown.png');