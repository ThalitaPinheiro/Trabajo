
CREATE SCHEMA IF NOT EXISTS agenda1 ;

USE agenda1 ;



-- -----------------------------------------------------

-- Table pessoa

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS pessoa (

  idpessoa INT(11) NOT NULL AUTO_INCREMENT ,

  nome VARCHAR(100) NOT NULL ,

  email VARCHAR(50)  NOT NULL ,

  senha VARCHAR(512)  NOT NULL ,

  dataNascimento DATE NOT NULL ,

  PRIMARY KEY (idpessoa));






-- -----------------------------------------------------

-- Table compromisso

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS compromisso (

  idcompromisso INT(11) NOT NULL AUTO_INCREMENT ,

  titulo VARCHAR(45)  NOT NULL ,

  descricao VARCHAR(200) NOT NULL ,

  dia DATE NOT NULL ,

  hora TIME NOT NULL ,

  idpessoa INT(11) NOT NULL ,

  status INT(11) NOT NULL ,

  PRIMARY KEY (idcompromisso) ,


  CONSTRAINT fk_compromisso_usuario

    FOREIGN KEY (idpessoa )

    REFERENCES pessoa (idpessoa )

    ON DELETE CASCADE

    ON UPDATE NO ACTION);

