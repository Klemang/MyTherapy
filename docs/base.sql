#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Pathologie
#------------------------------------------------------------

CREATE TABLE Pathologie(
        idPathologie Int  Auto_increment  NOT NULL ,
        nom          Varchar (255) NOT NULL
	,CONSTRAINT Pathologie_PK PRIMARY KEY (idPathologie)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Utilisateur
#------------------------------------------------------------

CREATE TABLE Utilisateur(
        idUtilisateur Int  Auto_increment  NOT NULL ,
        nom           Varchar (255) NOT NULL ,
        prenom        Varchar (255) NOT NULL ,
        age           Int NOT NULL ,
        email         Varchar (255) NOT NULL ,
        motdepasse    Varchar (255) NOT NULL ,
        role          Varchar (50) NOT NULL ,
        idPathologie  Int
	,CONSTRAINT Utilisateur_PK PRIMARY KEY (idUtilisateur)

	,CONSTRAINT Utilisateur_Pathologie_FK FOREIGN KEY (idPathologie) REFERENCES Pathologie(idPathologie) ON DELETE SET NULL
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Programme
#------------------------------------------------------------

CREATE TABLE Programme(
        idProgramme   Int  Auto_increment  NOT NULL ,
        nom           Varchar (255) NOT NULL ,
        etat          Varchar (255) NOT NULL ,
        idUtilisateur Int NOT NULL
	,CONSTRAINT Programme_PK PRIMARY KEY (idProgramme)

	,CONSTRAINT Programme_Utilisateur_FK FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(idUtilisateur) ON DELETE CASCADE
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Activite
#------------------------------------------------------------

CREATE TABLE Activite(
        idActivite  Int  Auto_increment  NOT NULL ,
        nom         Varchar (255) NOT NULL ,
        description Text NOT NULL ,
        adresse     Varchar (255) NOT NULL
	,CONSTRAINT Activite_PK PRIMARY KEY (idActivite)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Discipline
#------------------------------------------------------------

CREATE TABLE Discipline(
        idDiscipline Int  Auto_increment  NOT NULL ,
        nom          Varchar (255) NOT NULL
	,CONSTRAINT Discipline_PK PRIMARY KEY (idDiscipline)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: pathologie_activite
#------------------------------------------------------------

CREATE TABLE pathologie_activite(
        idActivite   Int NOT NULL ,
        idPathologie Int NOT NULL
	,CONSTRAINT pathologie_activite_PK PRIMARY KEY (idActivite,idPathologie)

	,CONSTRAINT pathologie_activite_Activite_FK FOREIGN KEY (idActivite) REFERENCES Activite(idActivite) ON DELETE CASCADE
	,CONSTRAINT pathologie_activite_Pathologie0_FK FOREIGN KEY (idPathologie) REFERENCES Pathologie(idPathologie) ON DELETE CASCADE
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: discipline_activite
#------------------------------------------------------------

CREATE TABLE discipline_activite(
        idDiscipline Int NOT NULL ,
        idActivite   Int NOT NULL
	,CONSTRAINT discipline_activite_PK PRIMARY KEY (idDiscipline,idActivite)

	,CONSTRAINT discipline_activite_Discipline_FK FOREIGN KEY (idDiscipline) REFERENCES Discipline(idDiscipline) ON DELETE CASCADE
	,CONSTRAINT discipline_activite_Activite0_FK FOREIGN KEY (idActivite) REFERENCES Activite(idActivite)  ON DELETE CASCADE
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: programme_activite
#------------------------------------------------------------

CREATE TABLE programme_activite(
        idActivite  Int NOT NULL ,
        idProgramme Int NOT NULL ,
        note        Int ,
        etat        Varchar (255) NOT NULL ,
        ordre       Int
	,CONSTRAINT programme_activite_PK PRIMARY KEY (idActivite,idProgramme)

	,CONSTRAINT programme_activite_Activite_FK FOREIGN KEY (idActivite) REFERENCES Activite(idActivite) ON DELETE CASCADE
	,CONSTRAINT programme_activite_Programme0_FK FOREIGN KEY (idProgramme) REFERENCES Programme(idProgramme) ON DELETE CASCADE
)ENGINE=InnoDB;
