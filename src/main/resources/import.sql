insert into editoriales(fecha_creacion, fecha_fundacion, fundador, estado, nombre_editorial) values ('2009-10-01', NOW(), 'Stan Lee', 1,'DC');

insert into categorias(nombre_categoria) values ('Terror');
insert into categorias(nombre_categoria) values ('Psicológico');

insert into idiomas(nombre_idioma) values ('Español');
insert into idiomas(nombre_idioma) values ('Inglés');
insert into idiomas(nombre_idioma) values ('Portugués');

insert into sexos(nombre_sexo) value ('Femenino');
insert into sexos(nombre_sexo) value ('Masculino');

insert into libros(descripcion, estado, fecha_actualizacion, fecha_creacion, fecha_publicacion, nombre_libro, isbn, id_categoria, id_editorial) VALUES ('Peter Parker era un adolescente que fue mordido por una araña radioctiva', 1, NOW(), NOW(), NOW(), 'Amazing Spiderman', '78951378465',2, 2);