CREATE TABLE plataformas
(
    plataforma character varying(25) NOT NULL,
    CONSTRAINT pk_plataformas PRIMARY KEY (plataforma)
);

CREATE TABLE generos
(
    genero character varying(25) NOT NULL,
    CONSTRAINT pk_generos PRIMARY KEY (genero)
);

CREATE TABLE videojuegos
(
    titulo character varying(30) NOT NULL UNIQUE ,
    imagen character varying(1000),
    fecha date,
    plataforma character varying(25),
    descripcion character varying(1000),
    genero character varying(25),
    CONSTRAINT pk_videojuegos PRIMARY KEY (titulo),
    CONSTRAINT fk_plataformas FOREIGN KEY (plataforma)
        REFERENCES plataformas (plataforma) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_generos FOREIGN KEY (genero)
        REFERENCES generos (genero)  MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
);

set dateformat dmy;