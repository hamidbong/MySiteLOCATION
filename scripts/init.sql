CREATE TABLE IF NOT EXISTS utilisateur (
  id_utilisateur VARCHAR(64) PRIMARY KEY,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL,
  adresse_email VARCHAR(255) NOT NULL,
  mot_de_passe VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS bein (
  id_bien VARCHAR(64) PRIMARY KEY,
  typ VARCHAR(100) NOT NULL,
  description TEXT,
  prix_par_jour DOUBLE NOT NULL,
  disponible VARCHAR(20) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS reservation (
  id_reservation VARCHAR(64) PRIMARY KEY,
  id_utilisateur VARCHAR(64) NOT NULL,
  id_bien VARCHAR(64) NOT NULL,
  date_debut TIMESTAMP NOT NULL,
  date_fin TIMESTAMP NOT NULL,
  statut VARCHAR(50) NOT NULL,
  montant_total DOUBLE NOT NULL,
  CONSTRAINT fk_reservation_user FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id_utilisateur),
  CONSTRAINT fk_reservation_bien FOREIGN KEY (id_bien) REFERENCES bein(id_bien)
) ENGINE=InnoDB;
