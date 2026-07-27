CREATE DATABASE InfoMed;
USE InfoMed;

CREATE TABLE Usuario (
    id_usuario        INT			 PRIMARY KEY NOT NULL,
    nome              VARCHAR(30)    NOT NULL,
    email             VARCHAR(50)    NOT NULL,
    senha             VARCHAR(40)    NOT NULL
);

CREATE TABLE Codigo_Barras (
    id_codigobarras   INT        PRIMARY KEY,
    pais              INT        NOT NULL,
    empresa           INT        NOT NULL,
    info_produto      INT        NOT NULL,
    verificador       INT        NOT NULL
);

CREATE TABLE Medicamento (
    id_medicamento      INT        	   PRIMARY KEY NOT NULL,
    nome_medicamento    VARCHAR(30)    NOT NULL,
    data_validade       DATE           NOT NULL,
    status_medicamento  VARCHAR(30)    NOT NULL,     
    id_usuario          INT       	   NOT NULL,
    id_codigobarras     INT      	   ,

    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_codigobarras) REFERENCES Codigo_Barras(id_codigobarras)
);

SELECT * FROM Usuario;
INSERT INTO Usuario (id_usuario, nome, email, senha) VALUES
(1, 'CamisMurilex', 'camismuriles@gmail.com', '123');