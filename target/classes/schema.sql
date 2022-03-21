CREATE TABLE plataformas
(
    id_plataforma smallint NOT NULL UNIQUE,
    plataforma character varying(25) NOT NULL UNIQUE,
    CONSTRAINT pk_plataformas PRIMARY KEY (id_plataforma)
);

CREATE TABLE generos
(
    id_genero smallint NOT NULL UNIQUE,
    genero character varying(25) NOT NULL UNIQUE,
    CONSTRAINT pk_generos PRIMARY KEY (genero)
);

CREATE TABLE videojuegos
(
    titulo character varying(1000) NOT NULL UNIQUE,
    imagen character varying(1000),
    fecha character varying(25),
    plataforma character varying(25),
    descripcion character varying(1000),
    genero character varying(25),
    CONSTRAINT pk_videojuegos PRIMARY KEY (titulo),
    CONSTRAINT fk_plataformas FOREIGN KEY (plataforma)
        REFERENCES plataformas (plataforma) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_generos FOREIGN KEY (genero)
        REFERENCES generos (genero)  MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

set dateformat dmy;