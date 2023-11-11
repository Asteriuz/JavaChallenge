/* 
table TDSS_TB_EMPRESA
cd_empresa NUMBER (10)
nm_empresa VARCHAR2 (100)
nr_funcionario NUMBER (10)

TDSS_TB_EMPRESA_PK (cd_empresa)


table TDSS_TB_VAGA
cd_vaga NUMBER (10)
ds_titulo VARCHAR2 (50)
ds_vaga VARCHAR2 (255)
vl_salario NUMBER (10, 2)
dt_publicacao DATE
cd_empresa NUMBER (10)

TDSS_TB_VAGA_PK (cd_vaga)
TDSS_TB_VAGA_TDSS_TB_EMPRESA_FK (cd_empresa)

-- relacionamento entre empresa e vaga é 1 para N
 */

-- CREATE TABLE TDSS_TB_EMPRESA (
-- 	cd_empresa NUMBER (10) NOT NULL,
-- 	nm_empresa VARCHAR2 (100) NOT NULL,
-- 	nr_funcionario NUMBER (10) NOT NULL,
-- 	CONSTRAINT TDSS_TB_EMPRESA_PK PRIMARY KEY (cd_empresa)
-- );

-- CREATE TABLE TDSS_TB_VAGA (
-- 	cd_vaga NUMBER (10) NOT NULL,
-- 	ds_titulo VARCHAR2 (50) NOT NULL,
-- 	ds_vaga VARCHAR2 (255) NOT NULL,
-- 	vl_salario NUMBER (10, 2) NOT NULL,
-- 	dt_publicacao DATE NOT NULL,
-- 	cd_empresa NUMBER (10) NOT NULL,
-- 	CONSTRAINT TDSS_TB_VAGA_PK PRIMARY KEY (cd_vaga),
-- 	CONSTRAINT TDSS_TB_VAGA_TDSS_TB_EMPRESA_FK FOREIGN KEY (cd_empresa) REFERENCES TDSS_TB_EMPRESA (cd_empresa)
-- );


-- Create table Casos, id, nome completo, email, marca, modelo, lista de imagens base64, endereco, data, status

CREATE TABLE TDSS_TB_CASO (
	CD_CASO NUMBER (10) NOT NULL,
	NM_COMPLETO VARCHAR2 (100) NOT NULL,
	DS_EMAIL VARCHAR2 (100) NOT NULL,
	DS_MARCA VARCHAR2 (100) NOT NULL,
	DS_MODELO VARCHAR2 (100) NOT NULL,
	DS_IMAGEM VARCHAR2 (100) NOT NULL,
	DS_ENDERECO VARCHAR2 (100) NOT NULL,
	DT_CRIACAO DATE NOT NULL,
	DS_STATUS VARCHAR2 (100) NOT NULL,
	CONSTRAINT TDSS_TB_CASOS_PK PRIMARY KEY (CD_CASO)
);

-- Insert rows into TDSS_TB_CASO
INSERT INTO TDSS_TB_CASO (
	CD_CASO,
	NM_COMPLETO,
	DS_EMAIL,
	DS_MARCA,
	DS_MODELO,
	DS_IMAGEM,
	DS_ENDERECO,
	DT_CRIACAO,
	DS_STATUS
) VALUES (
	1,
	'Augusto',
	'augustobb@live.com',
	'Fiat',
	'Uno',
	'https://www.google.com.br',
	'Rua 1',
	TO_DATE('2021-10-10', 'YYYY-MM-DD'),
	'ABERTO'
);

INSERT INTO TDSS_TB_CASO (
	CD_CASO,
	NM_COMPLETO,
	DS_EMAIL,
	DS_MARCA,
	DS_MODELO,
	DS_IMAGEM,
	DS_ENDERECO,
	DT_CRIACAO,
	DS_STATUS
) VALUES (
	2,
	'Gutocebola',
	'gutocebola@gmail.com',
	'Tesla',
	'Model 3',
	'https://www.google.com.br',
	'Rua 2',
	TO_DATE('2022-10-10', 'YYYY-MM-DD'),
	'ABERTO'
);

DROP TABLE TDSS_TB_CASO;