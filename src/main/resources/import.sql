
-- --------------------------------------------------------

INSERT INTO `autores` (`id_autor`, `apellido`, `biografia`, `fecha_modificacion`, `fecha_nacimiento`, `nombre_autor`, `url_foto`, `created_by`, `date_time_created`, `date_time_last_update`, `last_update_by`) VALUES
(1, 'Vallejo', 'César Abraham Vallejo Mendoza fue un poeta y escritor peruano. Es considerado uno de los mayores innovadores de la poesía del siglo XX y el máximo exponente de las letras en su país.​', '2021-01-28 09:50:52', '1892-03-16', 'César', 'https://upload.wikimedia.org/wikipedia/commons/3/36/Cesar_vallejo_1929.jpg', NULL, NULL, NULL, NULL),
(2, 'Arguedas Altamirano', 'Fue autor de novelas y cuentos que lo han llevado a ser considerado como uno de los grandes representantes de la literatura en el Perú.', '2021-01-28 10:17:13', '1911-01-18', 'José María', 'https://www.biografiasyvidas.com/biografia/a/fotos/arguedas_jose_maria.jpg', NULL, NULL, NULL, NULL),
(3, 'Valdelomar Pinto', 'Pedro Abraham Valdelomar Pinto, también mencionado como el Conde de Lemos, fue un narrador, poeta, periodista, dibujante, ensayista y dramaturgo peruano.', '2021-01-28 16:02:06', '1888-04-27', 'Pedro Abraham', 'https://upload.wikimedia.org/wikipedia/commons/c/c5/Valdelomar1.jpg', NULL, NULL, NULL, NULL),
(4, 'Ribeyro Zúñiga', 'Es una figura destacada de la Generación del 50 de su país, a la que también pertenecen narradores como Mario Vargas Llosa, Enrique Congrains Martin y Carlos Eduardo Zavaleta.', '2021-01-28 16:08:13', '1929-08-31', 'Julio Ramón', 'https://portal.andina.pe/EDPfotografia2/Thumbnail/2011/12/03/000171146W.jpg', NULL, NULL, NULL, NULL),
(5, 'Mariátegui La Chira', ' Autor prolífico a pesar de su temprana muerte, El Amauta (del quechua: hamawt\'a, \"maestro\", nombre con el que también es conocido en su país) es considerado uno de los mayores estudiosos de la realidad latinoamericana.', '2021-01-30 13:43:48', '1894-06-14', 'José Carlos', 'https://d1bvpoagx8hqbg.cloudfront.net/originals/jose-carlos-mariategui-715f4eba9241d70cd75f1d3263a919e6.jpg', 'paulvaso', '2021-01-30 13:36:37', '2021-01-30 13:43:48', 'paulvaso');

-- --------------------------------------------------------

INSERT INTO `categorias` (`id_categoria`, `nombre_categoria`, `created_by`, `date_time_created`, `date_time_last_update`, `last_update_by`) VALUES
(1, 'Terror', NULL, NULL, NULL, NULL),
(2, 'Psicológico', NULL, NULL, NULL, NULL),
(3, 'Poema', NULL, NULL, NULL, NULL),
(4, 'Novela', NULL, NULL, NULL, NULL),
(5, 'Ficcion', NULL, NULL, NULL, NULL),
(6, 'Cuento', NULL, NULL, NULL, NULL),
(7, 'Sociologia', 'paulvaso', '2021-01-30 13:38:15', '2021-01-30 13:38:15', 'paulvaso');

-- --------------------------------------------------------

INSERT INTO `editoriales` (`id_editorial`, `estado`, `fecha_creacion`, `fecha_fundacion`, `fundador`, `nombre_editorial`, `created_by`, `date_time_created`, `date_time_last_update`, `last_update_by`) VALUES
(1, b'0', NULL, '1939-01-10', 'Stan Lee', 'Marvel', NULL, NULL, NULL, NULL),
(2, b'1', '2021-01-28', '2010-03-18', 'Cesar Acuña', 'Universidad César Vallejo', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

INSERT INTO `idiomas` (`id_idioma`, `nombre_idioma`, `created_by`, `date_time_created`, `date_time_last_update`, `last_update_by`) VALUES
(1, 'Español', NULL, NULL, NULL, NULL),
(2, 'Inglés', NULL, NULL, NULL, NULL),
(3, 'Portugués', NULL, NULL, NULL, NULL),
(4, 'Francés', NULL, NULL, '2021-01-29 17:41:38', 'halstan483'),
(5, 'Quechua', 'halstan483', '2021-01-29 17:43:13', '2021-01-29 17:43:38', 'paulvaso');

-- --------------------------------------------------------

-- --------------------------------------------------------

INSERT INTO `libros` (`id_libro`, `descripcion`, `estado`, `fecha_actualizacion`, `fecha_creacion`, `fecha_publicacion`, `fecha_vigencia`, `isbn`, `nombre_libro`, `url_portada`, `precio`, `id_autor`, `id_categoria`, `id_editorial`, `id_idioma`, `created_by`, `date_time_created`, `date_time_last_update`, `last_update_by`) VALUES
(1, 'Los heraldos negros es el título de un libro de poemas escrito por el poeta peruano César Vallejo entre 1915 y 1918, y publicado por primera vez en julio de 1919.', b'1', '2021-01-28', '2021-01-28', '1919-07-16', '2021-01-31 00:00:00', '154621546', 'Heraldos Negros', 'https://simehbucket.s3.amazonaws.com/images/5a2d2f9c29b2605bc4176f806820f3ca-full.jpg', 12.9, 1, 3, 2, 1, NULL, NULL, NULL, NULL),
(2, 'Trilce es el poemario más importante y conocido del poeta peruano César Vallejo, y está considerado, merced a sus audacias lexicográficas y sintácticas.', b'1', '2021-01-28', '2021-01-28', '2021-01-27', '2021-01-20 00:00:00', '5983164712', 'Trilce', 'https://www.elvirrey.com/imagenes/9786124/978612415859.GIF', NULL, 1, 3, 2, 1, NULL, NULL, NULL, NULL),
(3, 'Los ríos profundos es la tercera novela del escritor peruano José María Arguedas. El título de la obra alude a la profundidad de los ríos andinos, que nacen en la cima de la Cordillera de los Andes', b'1', '2021-01-28', '2021-01-28', '2021-01-29', '2021-01-20 00:00:00', '236784535', 'Los ríos profundos', 'https://resumencortodelaobra.com/wp-content/uploads/2018/08/24.jpg', NULL, 2, 4, 2, 1, NULL, NULL, NULL, NULL),
(4, 'El caballero Carmelo es un cuento del escritor peruano Abraham Valdelomar, considerado por la crítica como lo mejor de toda su creación ficticia.', b'1', '2021-01-28', '2021-01-28', '1913-11-13', '2021-02-04 00:00:00', '23343433453', 'El caballero Carmelo', 'https://www.elsotano.com/imagenes_grandes/9786123/978612305056.JPG', 25.55, 3, 6, 2, 1, NULL, NULL, NULL, NULL),
(5, 'Silvio en El Rosedal es un libro de cuentos del escritor peruano Julio Ramón Ribeyro. Fue publicado en 1977 como parte del tercer tomo de La palabra del mudo.', b'1', '2021-01-29', '2021-01-28', '1981-02-11', '2021-02-02 00:00:00', '8975434534', 'Silvio en El Rosedal', 'https://www.clubdellector.com/sites/default/files/img/libros/silvio.jpg', 3.6, 4, 5, 2, 1, NULL, NULL, '2021-01-29 12:58:29', 'paulvaso'),
(6, 'Considerada la obra principal del escritor, periodista y pensador peruano José Carlos Mariátegui. Convirtió a su autor en una de las voces marxistas más difundidas de Latinoamérica.', b'1', '2021-01-30', '2021-01-30', '1928-03-14', '2021-01-31 00:00:00', '233126573453', 'Siete Ensayos de Interpretación de la Realidad Peruana', 'https://www.marxists.org/espanol/mariateg/1928/7ensayos/portada.jpg', 45.7, 5, 7, 2, 1, 'paulvaso', '2021-01-30 13:40:38', '2021-01-30 13:40:38', 'paulvaso');

-- --------------------------------------------------------

INSERT INTO `roles` (`id_rol`, `nombre_rol`, `created_by`, `date_time_created`, `date_time_last_update`, `last_update_by`) VALUES
(1, 'ROLE_LECTOR', NULL, NULL, NULL, NULL),
(2, 'ROLE_ADMIN', NULL, NULL, NULL, NULL),
(3, 'ROLE_BIBLIOTECARIO', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

INSERT INTO `usuarios` (`id_usuario`, `activado`, `apellido`, `contrasenha`, `correo`, `fecha_modificacion`, `nombre`, `sexo`, `username`, `created_by`, `date_time_created`, `date_time_last_update`, `last_update_by`) VALUES
(1, b'1', 'Arauco Belahonia', '$2a$10$tMHEThDQtr9SfxTOcUZw1O73kffFi8W.bv/h3Seglj8PwiKNWm0X6', 'enzoarauco@gmail.com', '2020-12-21 00:00:00', 'Enzo Daniel', 'Masculino', 'halstan483', NULL, NULL, NULL, NULL),
(2, b'1', 'Gomez Presentacion', '$2a$10$DibZlaNUEWArW7CTa3lA6eELC9aqdJ5SHqr5M3HurSgF4qF6rTU9W', 'paul@gmail.com', '2021-01-28 12:13:25', 'Paul Marcel', 'Masculino', 'paulvaso', NULL, NULL, NULL, NULL),
(3, b'1', 'Guillen Velarde', '$2a$10$ESqiBGis4gfwgtrNxbwf7.3Kw8zinzTepul9u0TSCXQx3oz0GFhwC', 'brian@gmail.com', '2021-01-28 16:16:43', 'Brian Arnaldo', 'Masculino', 'brian123', NULL, NULL, NULL, NULL),
(4, b'1', 'Mostacero Obando', '$2a$10$9yM.JImAQyJVbOa0d5LUj.uTXFc/rnwuqoqrWa5V80aPUSfEkJCMW', 'martin@gmail.com', '2021-01-28 19:43:22', 'Martin Alexander', 'Masculino', 'martin12345', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

INSERT INTO `usuario_rol` (`id_usuario`, `id_rol`) VALUES
(1, 2),
(1, 3),
(2, 3),
(3, 1),
(4, 1);
