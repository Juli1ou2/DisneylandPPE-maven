drop database if exists disneyland_heritage;
create database disneyland_heritage;
use disneyland_heritage;



CREATE TABLE user(
   iduser int(3) not null auto_increment,
   nom VARCHAR(50),
   prenom VARCHAR(50),
   adresse VARCHAR(50),
   email VARCHAR(50),
   mdp VARCHAR(50),
   tel VARCHAR(50),
   role enum("admin", "technicien", "client", "restaurateur"),
   PRIMARY KEY(iduser)
); 

CREATE TABLE technicien(
   iduser int(3) not null ,
   qualification VARCHAR(50),
   dateentree date,
   roleBis enum("admin", "secretaire"),
   PRIMARY KEY(iduser), 
   foreign key (iduser) references user(iduser)
);

CREATE TABLE client(
   iduser int(3) not null ,
   fidelite  int(3),
   dateNaissance date,
   promotion float, 
   PRIMARY KEY(iduser), 
   foreign key (iduser) references user(iduser)
);

CREATE TABLE restaurateur(
   iduser int(3) not null ,
   qualification VARCHAR(50),
   anciennete varchar (30),
   PRIMARY KEY(iduser), 
   foreign key (iduser) references user(iduser)
);


CREATE TABLE transport(
   idTransport int(3) not null auto_increment,
   libelle VARCHAR(50),
   type VARCHAR(50),
   capacite int(5),
   affluence enum ("Vide", "10%", "20%", "30%", "40%", "50%", "60", "70%", "80%", "90%", "Pleine"),
   horaire time,
   prix int(5),
   PRIMARY KEY(idTransport)
);

CREATE TABLE parc(
   idParc int(3) not null auto_increment,
   nom VARCHAR(50),
   capacite int(5),
   nbAttractionsTotales int(5),
   nbAttractionsFonctionnelles int(5),
   PRIMARY KEY(idParc)
);

 

CREATE TABLE attraction(
   idAttraction int(3) not null auto_increment,
   nom VARCHAR(50) NOT NULL,
   status enum ("Ouverte", "Fermée", "En Travaux"),
   type enum ("Spectacle", "Montagne Russe", "Manège", "Dark Ride", "Simulateur de vol", "Chute dans le vide" ),
   capacite int(5),
   affluence enum ("Vide", "10%", "20%", "30%", "40%", "50%", "60", "70%", "80%", "90%", "Pleine"),
   prix int(5),
   heureOuv TIME,
   heureFerm TIME,
   url varchar(50),
   idParc int(3) NOT NULL,
   iduser int(3) NOT NULL,
   PRIMARY KEY(idAttraction),
   FOREIGN KEY(iduser) REFERENCES technicien(iduser),
   FOREIGN KEY(idParc) REFERENCES parc(idParc)
);

CREATE TABLE restaurant(
   idRestaurant int(3) not null auto_increment,
   nom VARCHAR(50),
   theme VARCHAR(50),
   effectifMax int(5),
   affluence enum ("Vide", "10%", "20%", "30%", "40%", "50%", "60", "70%", "80%", "90%", "Pleine"),
   type enum ("Service à Table", "Restauration à Emporter"),
   capacite int(5),
   url varchar(50),
   iduser int(3) NOT NULL,
   PRIMARY KEY(idRestaurant),
   FOREIGN KEY(iduser) REFERENCES restaurateur(iduser)
);

CREATE TABLE commande(
   idCommande int(3) not null auto_increment,
   prix int(5),
   iduser int(3) NOT NULL,
   PRIMARY KEY(idCommande),
   FOREIGN KEY(iduser) REFERENCES client(iduser)
);

CREATE TABLE Reserver1(
   idAttraction int(3),
   idCommande int(3),
   dateResa DATE,
   heure TIME,
   PRIMARY KEY(idAttraction, idCommande, heure),
   FOREIGN KEY(idAttraction) REFERENCES attraction(idAttraction),
   FOREIGN KEY(idCommande) REFERENCES commande(idCommande)
);

CREATE TABLE Reserver2(
   idCommande int(3),
   idRestaurant int(3),
   PRIMARY KEY(idCommande, idRestaurant),
   FOREIGN KEY(idCommande) REFERENCES commande(idCommande),
   FOREIGN KEY(idRestaurant) REFERENCES restaurant(idRestaurant)
);

CREATE TABLE Reserver3(
   idTransport int(3),
   idCommande int(3),
   dateResa DATE,
   heure TIME,
   PRIMARY KEY(idTransport, idCommande, heure),
   FOREIGN KEY(idTransport) REFERENCES transport(idTransport),
   FOREIGN KEY(idCommande) REFERENCES commande(idCommande)
);

-- les VUES --

drop view if exists vueTechniciens;
create view vueTechniciens as (
select u.iduser,  u.nom, u.prenom, u.adresse, u.email, u.mdp, u.tel, t.qualification, t.dateentree, t.roleBis from user u, technicien t where u.iduser = t.iduser);

drop view if exists vueRestaurateurs;
create view vueRestaurateurs as ( 
select u.iduser,  u.nom, u.prenom, u.adresse, u.email, u.mdp, u.tel, r.qualification, r.anciennete from user u, restaurateur r where u.iduser = r.iduser);

drop view if exists vueClients;
create view vueClients as (
select u.*, c.fidelite, c.dateNaissance, c.promotion from user u, client c where u.iduser = c.iduser);


-- les PROCEDURES STOCKEES --
delimiter $
drop procedure if exists insertTechnicien ;
create procedure insertTechnicien (IN p_nom varchar(50), IN p_prenom varchar(50), IN p_adresse varchar(50), IN p_email varchar(50), IN p_mdp varchar(50),  IN p_tel varchar(50),IN p_role varchar(50), IN p_qualification varchar(50), IN p_dateentree date, IN p_roleBis varchar(50))
begin
	declare p_iduser int (3);

	insert into user values(null, p_nom, p_prenom, p_adresse, p_email, p_mdp, p_tel, p_role);
	select iduser into p_iduser
	from user
	where nom = p_nom and prenom = p_prenom and email = p_email and mdp=p_mdp;
	insert into technicien values(p_iduser, p_qualification, p_dateentree, p_roleBis);
end $

drop procedure if exists deleteTechnicien ;
create procedure deleteTechnicien(IN p_iduser int(3))
begin
   delete from technicien where iduser = p_iduser;
    delete from user where iduser = p_iduser;
end$

drop procedure if exists updateTechnicien  ;
create procedure updateTechnicien (IN p_nom varchar(50), IN p_prenom varchar(50), IN p_adresse varchar(50), IN p_email varchar(50), IN p_mdp varchar(50),  IN p_tel varchar(50),IN p_role varchar(50), IN p_qualification varchar(50), IN p_dateentree date, IN p_roleBis varchar(50), IN p_iduser int(3))
begin 
   update user set nom = p_nom, prenom = p_prenom, adresse = p_adresse, email = p_email, mdp = p_mdp, tel = p_tel
   where iduser = p_iduser;
   update technicien set qualification = p_qualification, dateentree = p_dateentree, roleBis = p_roleBis
   where iduser = p_iduser ;
end $
delimiter ;

delimiter $
create procedure deleteTechnicien(IN p_iduser int(3))
begin
   delete from technicien where iduser = p_iduser;
	delete from user where iduser = p_iduser;
end$
delimiter ;
-- ________________________________________________________________________________ --
delimiter $
create procedure insertRestaurateur (IN p_nom varchar(50), IN p_prenom varchar(50), IN p_adresse varchar(50), IN p_email varchar(50), IN p_mdp varchar(50),  IN p_tel varchar(50),IN p_role varchar(50), IN p_qualification varchar(50), IN p_anciennete varchar(30))
begin
	declare p_iduser int (3);

	insert into user values(null, p_nom, p_prenom, p_adresse, p_email, p_mdp, p_tel, p_role);
	select iduser into p_iduser
	from user
	where nom = p_nom and prenom = p_prenom and email = p_email and mdp=p_mdp;
	insert into restaurateur values(p_iduser, p_qualification, p_anciennete);
end $
delimiter ;

delimiter $
create procedure deleteRestaurateur(IN p_iduser int(3))
begin
   delete from restaurateur where iduser = p_iduser;
	delete from user where iduser = p_iduser;
end$
delimiter ;


delimiter $
create procedure updateRestaurateur (IN p_nom varchar(50), IN p_prenom varchar(50), IN p_adresse varchar(50), IN p_email varchar(50),
IN p_mdp varchar(50),  IN p_tel varchar(50), IN p_qualification varchar(50), IN p_anciennete varchar(30), IN p_iduser int(3))
begin
	update user set nom=p_nom, prenom=p_prenom, adresse=p_adresse, email=p_email, mdp=p_mdp, tel=p_tel where iduser = p_iduser;
	update restaurateur set qualification=p_qualification, anciennete=p_anciennete where iduser=p_iduser;
end $
delimiter ;
-- ________________________________________________________________________________ --
delimiter $
create procedure insertClient (IN p_nom varchar(50), IN p_prenom varchar(50), IN p_adresse varchar(50), IN p_email varchar(50),
 IN p_mdp varchar(50),  IN p_tel varchar(50),IN p_role varchar(50), IN p_fidelite int(3), IN p_dateNaissance date,
  IN p_promotion float)
begin
	declare p_iduser int (3);

	insert into user values(null, p_nom, p_prenom, p_adresse, p_email, p_mdp, p_tel, p_role);
	select iduser into p_iduser
	from user
	where nom = p_nom and prenom = p_prenom and email = p_email and mdp=p_mdp;
	insert into client values(p_iduser, p_fidelite, p_dateNaissance, p_promotion);
end $

delimiter ;

delimiter $
create procedure deleteClient(IN p_iduser int(3))
begin
   delete from client where iduser = p_iduser;
	delete from user where iduser = p_iduser;
end$
delimiter ;


call insertClient ("Ben Ahmed", "Okacha", "12 rue de Cléry", "O.ben-ahmed@cfa-insta.fr", "123", "0123456789", "client", 0, "1990-01-01", 1);


call insertTechnicien ("Morisseau", "Julien", "8 rue du CSS", "jm@gmail.com", "123", "0606060606",
"technicien",  "Ingénieur son", "2000-12-12", "admin");

call insertTechnicien ("Zeboudj", "Mouhamed", "10 rue de Disney", "mz@gmail.com", "456", "0607070707", "technicien ",
 "technicien plateau", "2004-07-04", "admin");

call insertTechnicien ("Da Costa", "Lucas", "9 rue du repas", "ld@gmail.com", "789" ,"0707070707","technicien",
 "Technicien lumiere", "1994-02-08", "admin");


call insertRestaurateur ("Da Costa", "Lucas", "9 rue du repas", "ldacosta7797@gmail.com", "cuistot", "0707070707", "restaurateur",
 "Chef cuisinier", "8 ans");

call insertRestaurateur ("Zeboudj", "Mouhamed", "10 rue de Disney", "mohamedzeboudj@gmail.com", "commis", "0607070707", "restaurateur",
 "Commis de cuisine", "2 ans");

call insertRestaurateur ("Morisseau", "Julien", "8 rue du CSS", "julienmorisseau@gmail.com", "commis", "0606060606","restaurateur",
 "Commis de cuisine" , "6 mois");


insert into parc values (null, "Parc Disneyland", 28000, 34, 14);

insert into parc values (null, "Parc Walt Disney Studio", 12000, 15, 10);

insert into user values (null, "admin", "admin", "admin", "admin", "admin", "admin", "admin");


insert into attraction values(null, "Big Thunder Moutain", "Ouverte", "Montagne Russe", 2400, "70%", 15, "09:00", "19:00", "images/big_thunder_mountain.jpeg",1, 2);

insert into attraction values(null, "Space Moutain", "Ouverte", "Montagne Russe", 1800, "40%", 20, "09:00", "19:00","images/space_mountain.jpeg", 1, 4);

insert into attraction values(null, "It's a Small World", "Ouverte", "Dark Ride", 2400, "70%", 15, "09:00", "19:00","images/it's_a_small_world.jpeg", 1, 2);

insert into attraction values(null, "Peter Pan", "En Travaux", "Dark Ride", 1500, "Vide", 10, "09:00", "19:00","images/peter_pan.jpeg", 1, 2);

insert into attraction values(null, "Indiana Jones et le temple du peril", "Fermée", "Montagne Russe", 1444, "Vide", 10, "09:00", "19:00","images/indiana_jones.jpeg", 1, 2);

insert into attraction values(null, "Star Tour", "En Travaux", "Simulateur de vol", 1444, "Vide", 10, "09:00", "19:00","images/star_tour.jpeg", 1, 4);

insert into attraction values (null, "Crush Coaster", "Ouverte", "Montagne Russe", 895, "Pleine", 25, "09:00", "19:00","images/crush_coaster.jpeg", 2, 3 );

insert into attraction values (null, "Ratatouille : L'aventure Totalement Toquée de Remy", "Ouverte", "Dark Ride", 1500, "50%", 15, "09:00", "19:00","images/ratatouille.jpeg", 2, 2);

insert into attraction values (null, "Toy Soldiers Parachute Drop", "En Travaux", "Chute dans le vide", 800, "20%", 5, "09:00", "19:00","images/toy_soldier.jpeg", 2, 2);


insert into attraction values (null, "Tower of Terror", "Ouverte", "Chute dans le vide", 1200, "90%", 25, "09:00", "19:00","images/tower_of_terror.jpeg", 2, 3 );


insert into restaurant values (null, "Chez Rémy", "Ratatouille", 25, "40%", "Service à table", 200, "images/Chez_Remi.png", 5 );

insert into restaurant values (null, "Au Chalet de la Marionnette", "Pinocchio", 7, "30%", "Restauration à Emporter", 0, "images/au_chalet_de_la_marionnette.png", 6 );

insert into restaurant values (null, "Restaurant en coulisse", "Hollywood", 15, "50%", "Service à table", 60, "images/en_coulisse.jpeg", 7 );

insert into restaurant values (null, "Speciality Ice Cream", "Glaces", 7, "70%", "Restauration à Emporter", 0, "images/speciality_ice_cream.jpg", 5 );




insert into transport values (null, "RER A", "Transport externe", 2600, "40%", "00:15", 5.20);

insert into transport values (null, "Interparc", "Navette interne", 150, "40%", "00:05", 0.00);


select * from vueClients;
select * from vueTechniciens;
select * from vueRestaurateurs;
select * from attraction; 

