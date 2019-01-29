--  Destruction des tables existantes
DROP TABLE IF EXISTS INGREDIENT_RECETTE;
DROP TABLE IF EXISTS BURN;
DROP TABLE IF EXISTS ETAPE_RECETTE;
DROP TABLE IF EXISTS FAVORI;
DROP TABLE IF EXISTS INGREDIENT;
DROP TABLE IF EXISTS RECETTE;
DROP TABLE IF EXISTS RECUPERATION;
DROP TABLE IF EXISTS UTILISATEUR;




-- Création de la table UTILISATEUR
CREATE TABLE UTILISATEUR
(ID_UT INT AUTO_INCREMENT,
 NOM_UT VARCHAR(20) UNIQUE,
 MDP VARCHAR(60),
 MAIL VARCHAR(100) UNIQUE,
 STATUT VARCHAR(1),
 AVATAR VARCHAR(2000) NOT NULL DEFAULT 'https://i.imgur.com/yfOIsYM.png',
 CONSTRAINT PK_UTILISATEUR PRIMARY KEY (ID_UT)
 );

-- Création de la table RECETTE
CREATE TABLE RECETTE
(ID INT AUTO_INCREMENT,
 ID_UT INT,
 NOM_RE VARCHAR(50), 
 NB_CONV INT,
 DESC_C VARCHAR(300),
 DESC_L TEXT,
 STATUT DECIMAL(1,0) NOT NULL DEFAULT 0,      -- 0 = brouillon (visible uniquement par le créateur) | 1 = privé (visible par tous les utilisateurs qui sont loger) | 2 = public (visible par tous les utilisateurs)
 URL_IMAGE TEXT  NOT NULL DEFAULT 'https://i.imgur.com/PNkKcoy.png',
 CONSTRAINT PK_RECETTE PRIMARY KEY(ID),
 CONSTRAINT FK_UT FOREIGN KEY (ID_UT) REFERENCES UTILISATEUR(ID_UT)
);


-- Création de la table INGREDIENT
CREATE TABLE INGREDIENT
(ID_INGR INT AUTO_INCREMENT,
 NOM_INGR VARCHAR(50) UNIQUE,
 DESCR_INGR TEXT,
 URL_IMAGE TEXT NOT NULL DEFAULT 'https://i.imgur.com/4xCDGuS.png',
 ID_UT INT,
 CONSTRAINT PK_INGREDIENT PRIMARY KEY(ID_INGR),
 CONSTRAINT FK_INGREDIENT_UT FOREIGN KEY (ID_UT) REFERENCES UTILISATEUR(ID_UT)
 );
 
 -- Création de le table INGREDIENT_RECETTE
 CREATE TABLE INGREDIENT_RECETTE
 (ID_INGR INT,
  ID INT,
  QUANTITE INT,
  UNITE VARCHAR(20),
  CONSTRAINT PK_INGREDIENTRECETTE PRIMARY KEY(ID, ID_INGR),
  CONSTRAINT FK_RECETTE FOREIGN KEY (ID) REFERENCES RECETTE(ID),
  CONSTRAINT FK_INGR FOREIGN KEY (ID_INGR) REFERENCES INGREDIENT(ID_INGR)
  );

-- Création de la table BURN
CREATE TABLE BURN
(ID_UT INT,
 ID INT,
 CONSTRAINT PK_BURN PRIMARY KEY(ID,ID_UT),
 CONSTRAINT FK_BURN_U FOREIGN KEY (ID_UT) REFERENCES UTILISATEUR(ID_UT),
 CONSTRAINT FK_BURN_RECETTE FOREIGN KEY (ID) REFERENCES RECETTE(ID)
 );
 
 -- Création de la table ETAPE_RECETTE
 CREATE TABLE ETAPE_RECETTE
 (ETAPE TEXT,
  ID INT,
  NUMERO INT,
  CONSTRAINT PK_ETAPE_RECETTE PRIMARY KEY(ID,NUMERO),
  CONSTRAINT FK_ETAPE_RECETTE_RECETTE FOREIGN KEY (ID) REFERENCES RECETTE(ID)
 );
 
 -- Création de la table FAVORI
 CREATE TABLE FAVORI
 (ID_UT INT,
  ID INT,
  CONSTRAINT PK_FAVORI PRIMARY KEY(ID_UT,ID),
  CONSTRAINT FK_FAVORI_U FOREIGN KEY (ID_UT) REFERENCES UTILISATEUR(ID_UT),
  CONSTRAINT FK_FAVORI_RECETTE FOREIGN KEY (ID) REFERENCES RECETTE(ID)
  );
  
  -- Creation de la table RECUPERATION
  CREATE TABLE RECUPERATION
  (ID_UT INT,
   CODE VARCHAR(30),
   CONSTRAINT FK_RECUPERATION_U FOREIGN KEY (ID_UT) REFERENCES UTILISATEUR(ID_UT),
   CONSTRAINT PK_RECUPERATION PRIMARY KEY(ID_UT)
   );


-- INSERTION tuple

  -- $2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG     =     12345
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT,AVATAR) VALUES (1,'loazo','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','emma.tarfi@sfr.fr','A','https://i.imgur.com/HV0ST2m.jpg');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (2,'MrQuentinProut','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test2@mail.com','A');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (3,'SkyDuriel','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test3@mail.com','A');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (4,'Algpo','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','emma.tarfi9@gmail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (5,'TopChefdu13','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test5@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (6,'LaBouffe','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test6@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (7,'hey011','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test7@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (8,'crash','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test8@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (9,'Catherine','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test9@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (10,'zedzed','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test10@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (11,'Cubitoine','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test11@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (12,'Pisanul','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test12@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (13,'MangezBougez','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test13@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (14,'BBQ1999','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test14@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (15,'Ogmog','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test15@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (16,'Kilonly','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test16@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (17,'Raety','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test17@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (18,'Yondion','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test18@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (19,'Rayra','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test19@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (20,'Oggar','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test20@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (21,'Edgar','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test21@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (22,'Simpson22','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test22@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (23,'Toka_katsu','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test23@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (24,'BOOOM','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test24@mail.com','M');
INSERT INTO UTILISATEUR (ID_UT,NOM_UT,MDP,MAIL,STATUT) VALUES (25,'Merguezzz','$2y$10$qAZ8vRm6A2eoEUYrUXHkMO6RLn0zwt/fzysT.G3ZgiYo9tvGa4zPG','test25@mail.com','M');





-- INSERTION Ingrédients
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (1,'Courgette','C\'est un légume vert bon pour la santé','https://i.imgur.com/GKDfzkv.jpg',1);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (2,'Salade batavia','La Batavia est une variété de Laitue cultivée. Les Laitues font partie de la famille des Astéracées.','https://i.imgur.com/d1aZmjC.jpg',1);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (3,'Haricot vert','Les haricots verts sont des gousses immatures du haricot commun ce sont des légumes-fruits consommés comme légumes. Selon les variétés, leur couleur peut varier entre le jaune, le vert et le violet.','https://i.imgur.com/yLKN1AJ.jpg',2);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (4,'Aubergine','C\'est un légume originaire d\'Asie','https://i.imgur.com/fK6FCyb.jpg',2);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (5,'Poivron rouge','C\'est une variété de piments doux de l\'espèce Capsicum annuum à très gros fruits.','https://i.imgur.com/wxzEs3J.jpg',3);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (6,'Olive','L’olive est le fruit de l\'olivier, un arbre fruitier caractéristique des régions méditerranéennes. Sur le plan botanique, il s\'agit d\'une drupe, à peau lisse, à mésocarpe charnu riche en matière grasse, renfermant un noyau ligneux, qui contient une graine. Sa forme ovoïde est typique.','https://i.imgur.com/HVhnyS8.jpg',3);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (7,'Tomate','Le terme désigne aussi ce fruit charnu. La tomate se consomme comme un légume-fruit, crue ou cuite.','https://i.imgur.com/3xkoZly.jpg',1);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (8,'Tomate cerise','La tomate cerise est un type de variété de tomate, cultivée comme cette dernière pour ses fruits - mais de taille réduite - consommés comme légumes.','https://i.imgur.com/tvdcFWx.jpg',4);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (9,'Coulis de tomate','Un coulis de tomate est un suc obtenu à froid après avoir écrasé des tomates qui sont ensuite passées dans un tamis. Il sert généralement de sauce ou d\'accompagnement.','https://i.imgur.com/Qi9J8CK.jpg',5);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (10,'Pomme de terre','Elle peut être une source importante de glucides, qui se présentent principalement sous forme de fécule, mais aussi de protéines et de vitamines. Ses qualités nutritives et sa facilité de culture font qu\’elle est devenue l\’un des aliments de base de l\’humanité : elle figure parmi les légumes et féculents ainsi que les fruits les plus consommés et la principale denrée alimentaire non céréalière du monde. Cultivée et consommée localement, relativement peu commercialisée sur le marché mondial, elle est recommandée par l\’ONU pour atteindre la sécurité alimentaire.','https://i.imgur.com/kKtfdlg.jpg',10);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (11,'Oignon','C\'est un légume qui pique les yeux','https://i.imgur.com/CfiCPfD.jpg',11);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (12,'Ail','C\'est fort au goût et à l\'odeur !','https://i.imgur.com/xKpkxVy.jpg',20);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (13,'Échalote','C\'est super bon dans une vinaigrette en salade !','https://i.imgur.com/hlZ54oJ.jpg',17);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (14,'Laurier','Espèce d\'arbustes à feuillage persistant de la famille des Lauracées. Il est originaire du bassin méditerranéen. Il est parfois appelé Laurier d\'Apollon ou Laurier noble.','https://i.imgur.com/rtjkugT.jpg',1);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (15,'Romarin','Fraîche ou séchée, cette herbe condimentaire se retrouve dans la cuisine méditerranéenne, et une variété domestiquée se cultive dans les jardins!','https://i.imgur.com/OqoLBmO.jpg',9);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (16,'Thym',' Ces plantes sont riches en huiles essentielles et à ce titre font partie des plantes aromatiques.','https://i.imgur.com/gd4jgMH.jpg',5);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (17,'Curry','Variété d\'épices que l\'on retrouve principalement dans les cuisines indiennes ou influencées par l\'Inde ou en Asie du Sud-Est. Selon sa composition, un curry peut être très doux ou bien très épicé, et il est généralement très parfumé.','https://i.imgur.com/FYaPnlp.png',6);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (18,'Cumin','Variété d\'épices originaire du Proche-Orient. Il y est apparu comme épice, à fumer ou pour apprêter des mets, puisque son goût est terreux lorsque non apprêté. Il est de la même famille que le persil.','https://i.imgur.com/wtpjzEw.jpg',7);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (19,'Piment de Cayenne','Le piment de Cayenne est un type de Capsicum annuum. C\'est généralement un piment moyennement chaud utilisé pour aromatiser les plats.','https://i.imgur.com/0lFFKYa.png',8);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (20,'Persil','Le persil est couramment utilisé en cuisine pour ses feuilles très divisées, et en Europe centrale pour sa racine pivot. C\'est également une plante médicinale.','https://i.imgur.com/tZt58e6.jpg',9);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (21,'Huile d\'olive','Matière grasse extraite des olives lors de la trituration dans un moulin à huile. Elle est un des fondements de la cuisine méditerranéenne et peut être, sous certaines conditions, bénéfique pour la santé.','https://i.imgur.com/AqwDcYL.jpg',10);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (22,'Huile de tournesol','L\'huile de tournesol est une huile végétale obtenue à partir des graines de tournesol.','https://i.imgur.com/IJ8Zl5J.jpg',11);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (23,'Vinaigre balsamique','Le vinaigre balsamique désigne un ensemble de produits comprenant une part de moût de raisin concentré et d\'acide acétique.','https://i.imgur.com/bzlMm4I.jpg',12);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (24,'Sel','Le sel de table, sel alimentaire ou sel de cuisine, est composé essentiellement de chlorure de sodium. Il se présente sous différentes formes : gros sel, sel fin, fleur de sel.','https://i.imgur.com/DRo5yoz.jpg',13);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (25,'Poivre','Le poivre est une épice obtenue à partir des baies de différentes espèces de poivriers, des plantes de la famille des pipéracées.','https://i.imgur.com/bSoFPzd.jpg',15);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (26,'Vinaigre de Xérès','Issu de la fermentation de vins doux provenant uniquement des cépages Palomino, Pedro Ximénez et Moscatel (Muscat), le vinaigre de Xérès est originaire de la région de Jerez, dans le sud de l\'Andalousie. Il s\'agit d\'une Appellation d\'Origine Contrôlée, ce qui garanti une haute qualité du vinaigre par des contrôles tant sur le terroir, les cépages, les vins, et l\’élaboration que sur le produit fini.','https://i.imgur.com/vGrBM3K.jpg',16);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (27,'Citron','Agrume très acide !','https://i.imgur.com/jgiJ3s7.jpg',17);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (28,'Citron vert','Agrume encore plus acide que le citron jaune !','https://i.imgur.com/w0qf4rB.jpg',18);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (29,'Oeuf','L\’œuf est un produit agricole issu d\'élevages divers et utilisé comme aliment humain simple ou servant d\'ingrédient dans la composition de nombreux plats dans la plupart des cultures du monde. Le plus utilisé est l\’œuf de poule, mais les œufs d\’autres oiseaux sont aussi consommés : caille, cane, oie, autruche, etc...','https://i.imgur.com/sbQ2ouk.jpg',19);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (30,'Jaune d\'oeuf','Le jaune d\’œuf, cas particulier de vitellus, est la partie de l\’œuf qui sert de source de nourriture pour le développement de l\’embryon. Le jaune et le disque germinal forment une seule cellule. Le jaune d\’œuf est maintenu en suspension dans le blanc d\’œuf par des filaments torsadés de tissus cellulaires appelés chalazes. Le jaune d\’œuf est l’un des rares produits naturels à contenir nativement de la vitamine D.','https://i.imgur.com/8dcrcO4.jpg',20);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (31,'Blanc de poulet','Masse pectorale de part et d\'autre du bréchet. Ces parties ont parfois tendance à se dessécher lors des cuissons rôties.','https://i.imgur.com/Vwn9gqx.jpg',8);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (32,'Lard','Graisse épaisse, située sous la peau du porc','https://i.imgur.com/RMJPnYj.jpg',15);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (33,'Knacki','La knack ou saucisse de Strasbourg est une spécialité culinaire alsacienne, et plus particulièrement strasbourgeoise, dont les origines remontent au XVIᵉ siècle. Il s\'agit d\'une saucisse longue et fine, légèrement incurvée, à base de viande de bœuf et de porc.','https://i.imgur.com/Jz9BRg9.jpg',16);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (34,'Saucisse','Une saucisse est un produit de charcuterie composée principalement de viande hachée mélangée à d\'autres ingrédients tels que des épices et des condiments, la préparation est ensuite déposée dans un boyau, d\'origine intestinal ou synthétique, en forme de tube et refermé aux extrémités.','https://i.imgur.com/pwPfw98.jpg',17);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (35,'Merguez','La merguez est une petite saucisse rouge épicée d\'une vingtaine de centimètres de longueur, à base d\'un hachis de viande introduit dans de l\'intestin grêle de mouton. La viande peut être de la viande de bœuf et/ou de mouton. Elle a été introduite en France par les pieds-noirs.','https://i.imgur.com/gPx6hE8.png',21);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (36,'Coeur de mouton','Organe interne du mouton qui lui permet de vivre. Le mouton est une viande très forte !','https://i.imgur.com/FVaGVQm.jpg',22);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (37,'Rognon d\'agneau','Il s\'agit d\'un rein. La viande d\'agneau est une production agricole résultante de l\'élevage du mouton.','https://i.imgur.com/RvQNxPJ.jpg',23);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (38,'Foie d\'agneau','Organe interne. La viande d\'agneau est une production agricole résultante de l\'élevage du mouton.','https://i.imgur.com/LCPNt2a.jpg',19);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (39,'Viande de boeuf','La viande bovine est la viande issue des animaux de l\'espèce Bos taurus, qu\'il s\'agisse de vache, taureau, veau, broutard, taurillon, génisse ou bœuf. C\'est un produit agricole destiné quasi exclusivement à l\'alimentation humaine.','https://i.imgur.com/DQi86be.jpg',11);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (40,'Sardine fraiche','Espèce de poissons de la famille des Clupeidae.','https://i.imgur.com/BvwgsIS.jpg',10);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (41,'Thon','Les thons sont des poissons océaniques de la famille des scombridés : thons rouges, thon blanc – ou germon –, thon albacore, thon patudo et thon listao. Ces trois derniers sont des thons tropicaux.','https://i.imgur.com/E4zRC0n.jpg',1);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (42,'Anchois frais','Un anchois est un petit poisson fourrager commun de la famille des Engraulidae. La plupart des espèces se trouvent dans les eaux marines, mais plusieurs vont entrer dans les eaux saumâtres et certaines en Amérique du Sud sont limitées à l\’eau douce.','https://i.imgur.com/LlrBTf1.jpg',7);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (43,'Chapelure','La chapelure, ou panure, est un produit culinaire constitué de miettes plus ou moins grosses, de pain ou de biscotte.','https://i.imgur.com/RduDbfW.jpg',8);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (44,'Camembert','L\'appellation générique camembert désigne généralement un fromage à pâte molle et à croûte fleurie.','https://i.imgur.com/KQglDWY.jpg',9);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (45,'Baguette','Variété de pain à farine blanche, reconnaissable à sa forme allongée.','https://i.imgur.com/RkTn9Ur.jpg',5);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (46,'Tortilla','Une tortilla est une galette préparée à base de maïs, dans la cuisine mexicaine et d\'Amérique centrale, où elle est un aliment de base depuis l\'Antiquité.','https://i.imgur.com/cd6srR4.jpg',10);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (47,'Ketchup','Le ketchup est un condiment populaire, habituellement élaboré à partir de sauce tomate, de vinaigre et de sucre.','https://i.imgur.com/ROLWw5d.jpg',3);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (48,'Moutarde','La moutarde est un condiment préparé à partir des graines d\'une plante de la famille des Brassicaceae, appelée aussi moutarde. Elle peut être douce ou forte.','https://i.imgur.com/2O8IUgO.jpg',2);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (49,'Mayonnaise','La mayonnaise est une sauce froide à base d\'huile émulsionnée dans un mélange de jaune d\'œuf et de vinaigre, ou de jus de citron.','https://i.imgur.com/ko6Kloi.jpg',1);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (50,'Miel','Le miel est une substance sucrée élaborée par les abeilles à miel à partir de nectar ou de miellat. Elles l\'entreposent dans la ruche et s\'en nourrissent tout au long de l\'année, en particulier lors de périodes climatiques défavorables.','https://i.imgur.com/BSmBOYn.jpg',3);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (51,'Riz','Le riz est une céréale de la famille des poacées, cultivée dans les régions tropicales, subtropicales et tempérées chaudes pour son fruit, ou caryopse, riche en amidon.','https://i.imgur.com/VEmarDQ.png',5);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (52,'Pâte','Les pâtes alimentaires sont des aliments fabriqués à partir d\'un mélange pétri de farine, de semoule de blé dur, d\'épeautre, de blé noir, de riz ou d\'autres types de céréales, d\'eau et parfois d\'œuf et de sel.','https://i.imgur.com/TcEdggJ.jpg',9);
INSERT INTO INGREDIENT (ID_INGR,NOM_INGR,DESCR_INGR,URL_IMAGE,ID_UT) VALUES (53,'Maïs','Le maïs, appelé blé d\’Inde au Canada, est une plante herbacée tropicale annuelle de la famille des Poacées, largement cultivée comme céréale pour ses grains riches en amidon, mais aussi comme plante fourragère.','https://i.imgur.com/3VNZQeQ.jpg',8);

INSERT INTO `INGREDIENT` (`ID_INGR`, `NOM_INGR`, `DESCR_INGR`, `URL_IMAGE`, `ID_UT`) VALUES
(54, 'Pain burger', 'Pains pour burgers.', 'https://i.imgur.com/kgIxjPR.jpg', 2),
(55, 'Steak haché', 'Steak haché de porc.', 'https://i.imgur.com/PcXjBJH.jpg', 2);




-- RECETTE Brochette de légumes grillées au barbecue  ->  burn : 0 -> 1 /10
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (1,1,'Brochettes de légumes grillées',6,'Venez décourvrir les brochettes rapides à faire au barbecue sans viande !','Pour ceux qui ne mangent pas de viande ou qui font une cure de viande, cette recette est faite pour vous ! Des brochettes aux légumes où l\'on retrouve des courgettes, de l\'aubergine et du poivron grillées sur une brochette.',1.0,'https://i.imgur.com/FmhYzjJ.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Lavez et coupez la courgette et l\'aubergines en fines rondelles.',1,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Pelez et coupez l\'oignon en morceaux.',1,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Lavez, épépinez et coupez les poivrons rouges en cubes.',1,3);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Mélangez dans un bol l\'huile d\'olive, le persil ciselé, du sel et du poivre.',1,4);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Placez sur des pics en bois les légumes en les alternant.',1,5);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Badigeonnez les brochettes avec la préparation à l\'huile d\'olive, à l\'aide d\'un pinceau.',1,6);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Faites cuire au barbecue pendant 10 à 15 minutes.',1,7);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Servez bien chaud.',1,8);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (1,1,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (4,1,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (5,1,2,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (21,1,4,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (20,1,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,1,1,'pincée');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (25,1,1,'pincée');



-- RECETTE Aubergines grillées au barbecue  ->  burn : 0  -> 2/10
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (2,2,'Aubergines grillées',12,'Recette hyper rapide et bon pour la santé !','Brochette d\aubergines grillées et marinées.',1.0,'https://i.imgur.com/2ol82sd.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Laver les aubergines et les tailler en tranches de 1 cm.',2,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Écraser l\'ail avec la paume de la main. Effeuiller le thym et casser les feuilles de laurier.',2,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Dans une casserole, faire bouillir le vinaigre balsamique puis stopper la source de chaleur.',2,3);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Ajouter la garniture aromatique, le vinaigre de Xeres et laisser infuser pendant 5 min.',2,4);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Disposer les tranches d\'aubergines dans un plat à gratin, les saler et les poivrer, puis les arroser d\'huile d\'olive.',2,5);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Verser ensuite la marinade et laisser infuser au frais durant 25 min',2,6);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Égoutter les tranches d\'aubergines et les disposer sur la grille du barbecue.',2,7);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Les griller pendant 3 min sur chaque face et déguster aussitôt.',2,8);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (4,2,3,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (23,2,20,'cl');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (26,2,5,'cl');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (21,2,10,'cl');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (12,2,2,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (16,2,2,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (14,2,2,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,2,1,'pincée');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (25,2,1,'pincée');



-- RECETTE de frites  ->  burn : 0 -> 3 /10
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (3,10,'Frites au BBQ',5,'Un accompagnement que tout le monde aime !','Des patates coupés en frite à faire grillé au barbecue.',1.0,'https://i.imgur.com/ZKVnX9n.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Nettoyer les pommes de terre sous de l\'eau froide et les sécher avec des papiers absorbants. Couper les pommes de terre en deux dans le sens de la longueur, puis couper chaque moitié de pomme de terre dans le sens de la longueur en tranches de ½ po d\'épaisseur.',3,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Placer dans un bol et ajouter l\'huile, 2 c. à thé de sel et 1 c. à thé de poivre. Remuer pour enrober les morceaux.',3,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Faire griller les tranches de pommes de terre à feu moyen direct avec le couvercle fermé et en tournant de temps à autre, jusqu\'à ce qu\'elles soient tendres et marquées par le gril, 15 à 17 minutes...',3,3);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (10,3,7,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (22,3,2,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,3,1,'cuc');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (25,3,1,'pincée');



-- RECETTE de tacos saucisse  ->  burn : 0 -> 4 /10
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (4,3,'Tacos saucisse',1,'Un repas étudiant peu cher qui fait rêver','Un knacki et une galette de maïs avec éventuellement de la mayonnaise et de la ketchup...Et voilà un tacos économique.',1.0,'https://i.imgur.com/c4Jjlmi.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Mettez la saucisse au milieu de la galette et faire chauffer le tout au micro-onde pendant 30 secondes.',4,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Ajoutez la mayonnaise et la ketchup au centre, puis enroulez la saucisse et dégustez ce repas de ouf.',4,2);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (46,4,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (33,4,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (49,4,1,'cuc');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (47,4,1,'cuc');



-- RECETTE de chips maison  ->  burn : 0 -> 5 /10
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (5,19,'Chips maison',15,'Apéros maison et très simple','Pommes de terre coupées en lamelles grillées au barbecue.',1.0,'https://i.imgur.com/km4BZUB.jpg');
    -- Etapes de la recette4
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Nettoyer les pommes de terres et les coupez en lamelles. Dans un saladier, préparer l\'huile et y mettre les patates. Les griller au barbecue.',5,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Dans un autre saladier, ajoutez les patates grillées puis ajoutez le sel et le piment si vous le souhaitez. Mélangez puis servir à table.',5,2);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (10,5,8,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (22,5,50,'cl');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (19,5,1,'cuc');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,5,2,'cuc');



-- RECETTE de salade de riz  ->  burn : 0 -> 6 /10
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (6,23,'Salade de riz',6,'Accompagnement pour un barbecue','Salade de riz, thon, olives, tomates cerises, maïs avec un filet d\'huile d\'olive et de jus de citron.',1.0,'https://i.imgur.com/DdyUjTt.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Nettoyez le riz puis le faire cuir 10 min dans une casserole.',6,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('En attendant, préparez le thon, le maïs, les tomates crises et les olives.',6,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Une fois le riz cuit, le laisser refroidir puis mettre les aliments. Versez un filet d\'huile d\'olive, du jus de citron et une pincée de sel. Servez la salade !',6,3);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (51,6,200,'g');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (41,6,1,'boite');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (6,6,2,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (53,6,1/2,'boite');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (8,6,100,'g');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (21,6,1,'filet');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (27,6,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,6,2,'pincées');



-- RECETTE de salade de pâtes  ->  burn : 0 -> 7 /10
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (7,22,'Salade de pâtes',6,'Accompagnement pour un barbecue','Salade de pâtes, thon, olives, tomates cerises, maïs avec un filet d\'huile d\'olive et de jus de citron.',1.0,'https://i.imgur.com/XglvGAV.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Faire cuir les pâtes pendant 10-12 min dans une casserole.',7,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('En attendant, préparez le thon, le maïs, les tomates crises et les olives.',7,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Une fois les pâtes cuites, les laisser refroidir puis mettre les aliments. Versez un filet d\'huile d\'olive, du jus de citron et une pincée de sel. Servez la salade !',7,3);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (51,7,200,'g');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (41,7,1,'boite');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (6,7,2,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (53,7,1/2,'boite');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (8,7,100,'g');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (21,7,1,'filet');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (27,7,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,7,2,'pincées');



-- RECETTE de salade de tomates ->  burn : 0 -> 8 /10
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (8,4,'Salade de tomates',10,'Accompagnement pour un barbecue','Salade de tomates et d\'échalotes avec de la vinaigrette et du jus de citron.',1.0,'https://i.imgur.com/WIP5ATh.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Couper les tomates et l\'échalote.',8,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Dans un saladier, mélangez l\'huile d\'olive, le vinaigre balsamique, le sel et le jus de citron.',8,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Puis y mettre les tomates et les échalotes. Ajoutez éventuellement du basilic ou de la ciboulette. Mélangez et servez la salade !',8,3);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (7,8,8,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (13,8,2,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (23,8,2,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (21,8,4,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (27,8,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,8,2,'pincées');



-- RECETTE de salade d'haricots verts ->  burn : 0 -> 9 /10
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (9,1,'Salade d\'haricots',7,'Accompagnement pour un barbecue','Salade d\'haricots verts aux oeufs et échalotes avec de la vinaigrette',1.0,'https://i.imgur.com/fkIs42l.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Faire bouillir l\'eau dans une casserole. Ajoutez les oeufs dans la casserole une fois l\'eau bouillie pendant 8 min.',9,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Laver les haricots verts et égouttez-les. Puis couper les échalotes.',9,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Dans un saladier, mélangez l\'huile d\'olive, le vinaigre balsamique et le sel.',9,3);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Puis y mettre les oeufs coupés en deux, les haricots verts et les échalotes. Mélangez et servez la salade !',9,4);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (3,9,750,'g égouttés');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (29,9,5,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (13,9,2,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (23,9,2,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (21,9,4,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,9,2,'pincées');



-- RECETTE de salade verte ->  burn : 0 -> 10 /10
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (10,13,'Salade verte',4,'Accompagnement pour un barbecue','Salade verte à la vinaigrette',1.0,'https://i.imgur.com/t6ebsYo.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Laver la salade et l\'égoutter.',10,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Dans un saladier, mélangez l\'huile d\'olive, le vinaigre balsamique et le sel.',10,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Puis y mettre la salade coupée et égouttée. Mélangez et servez la salade !',10,3);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (2,10,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (23,10,2,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (21,10,4,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,10,2,'pincées');



-- RECETTE Camembert en boîte au barbecue -> burn : 15
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (11,5,'Camembert fondu',4,'Venez décourvrir le délicieux camembert fondu !','Le camembert au barbecue, tout un poème ! C\'est la touche finale, l\'apothéose, le clou du spectacle d\'un bon barbecue convivial. Rien de bien sorcier, juste un camembert au lait cru, du papier alu, et du bon pain frais... Quel bonheur de plonger son morceau de baguette dans ce fromage fondu et coulant, savoureux et généreux. Un vrai délice dont vous auriez tort de vous priver ! Cette recette est toute simple, mais vous pouvez aussi l\'enrichir en ajoutant des petits aromates... Hmmmm...',2.0,'https://i.imgur.com/m5ffHDq.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Sortez le camembert de son emballage papier, déposez-le dans sa boîte en bois et piquez-le à l\'aide d\'une fourchette. Puis emballez le tout dans la feuille d\'alu (au moins deux tours) et déposez-le dans les braises encore chaudes mais pas brûlantes du barbecue...',11,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Laissez chauffer pendant 10 min. Sortez-le des braises, déballez-le, ôtez le couvercle, cassez la croûte du camembert chaud et trempez vos morceaux de pain directement dedans.',11,2);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (44,11,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (45,11,1,'entière');
    -- Burn
    INSERT INTO BURN (ID_UT,ID) VALUES (1,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (4,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (3,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (10,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (11,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (22,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (8,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (15,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (14,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (16,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (17,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (18,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (24,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (12,11);
    INSERT INTO BURN (ID_UT,ID) VALUES (6,11);


-- RECETTE Hot-dog express -> burn : 15
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (12,12,'Hot-dog express',1,'Vous en avez marre de manger vos saucisses grillées au BBQ seules ? Alors venez découvrir le hot-dog express !','Rien d\'extraordinaire : du pain, une saucisse et de la sauce !',2.0,'https://i.imgur.com/qQBv4Ma.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Faire cuire la saucisse au BBQ.',12,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Couper le pain en deux, oter un peu de mie.',12,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Mettre de la moutarde et du ketchup à l\'intérieur du pain puis y disposer la saucisse.',12,3);
   -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (34,12,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (45,12,1,'moitiée');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (48,12,1,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (47,12,1,'cup');
    -- Burn
    INSERT INTO BURN (ID_UT,ID) VALUES (1,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (21,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (3,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (10,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (11,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (22,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (8,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (15,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (14,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (16,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (17,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (18,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (24,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (25,12);
    INSERT INTO BURN (ID_UT,ID) VALUES (6,12);



-- RECETTE de mini-brochette de bœuf et merguez au barbecue -> burn : 10
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (13,25,'Mini-brochettes',2,'Je ne sais pas vous, mais avec ce temps j\'ai envie de grillade, j\'ai donc fouiné dans le congel et je suis tombée sur des morceaux de boeuf et quelques merguez. Voilà donc ce que j\'ai préparé...','Mini-brochette de viande de boeuf et de merguez à la sauce tomate, piment et miel.',2.0,'https://i.imgur.com/LTM5HE5.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Chauffer le barbecue.',13,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Combiner le coulis de tomate, le miel, le ketchup, les oignons et le piment de Cayenne dans un bol.',13,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Assaisonner le mélange. Placer les morceaux de viande et de merguez dans un sac de congélation.',13,3);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Verser la sauce dans le sac et l\asperger d\'huile d\'olive. Refermer le sac puis le secouer. Laisser la préparation macérer durant 1 h sans oublier de la remuer régulièrement.',13,4);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Ouvrir le sac. Superposer la viande avec les merguez et les tomates cerise sur des brochettes en bois.',13,5);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Saler et poivrer le tout. Griller les brochettes de chaque côté sur le barbecue durant 5 min.',13,6);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Ajuster l\'assaisonnement et parsemer de basilic.',13,7);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (39,13,150,'g');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (35,13,2,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (8,13,8,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (47,13,80,'g');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (9,13,15,'cl');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (11,13,1,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (21,13,2,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (50,13,2,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (19,13,1,'pincée');
    -- Burn
    INSERT INTO BURN (ID_UT,ID) VALUES (1,13);
    INSERT INTO BURN (ID_UT,ID) VALUES (21,13);
    INSERT INTO BURN (ID_UT,ID) VALUES (3,13);
    INSERT INTO BURN (ID_UT,ID) VALUES (2,13);
    INSERT INTO BURN (ID_UT,ID) VALUES (11,13);
    INSERT INTO BURN (ID_UT,ID) VALUES (22,13);
    INSERT INTO BURN (ID_UT,ID) VALUES (8,13);
    INSERT INTO BURN (ID_UT,ID) VALUES (15,13);
    INSERT INTO BURN (ID_UT,ID) VALUES (14,13);
    INSERT INTO BURN (ID_UT,ID) VALUES (23,13);



-- RECETTE de légumes au barbecue -> burn : 10
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (14,23,'Légumes grillées',6,'J\avais envie de légumes grillées, n\'étant pas fan de viande...','4 légumes grillés au barbecue aux herbes et moutarde.',2.0,'https://i.imgur.com/R4Tyv6Y.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Pelez les oignons. Lavez les légumes et coupez-les en fines tranches dans le sens de la longueur.',14,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Ecrasez l\’ail dans un bol, versez de l\’huile d\’olive, de la moutarde et les feuilles broyés.',14,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Salez et poivrez. Mélangez avec une fourchette. Badigeonnez les légumes sur les deux faces et posez-les sur la grille du barbecue le plus haut possible.',14,3);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Au bout de 10 mn, remettez une couche d\’huile puis retournez.',14,4);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Badigeonnez si les légumes s\’assèchent. Ils doivent rester tendres et fondant. Servez chaud.',14,5);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (4,14,3,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (1,14,3,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (7,14,6,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (11,14,4,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (12,14,2,'gousses');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (21,14,2,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (48,14,1,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (16,14,1,'feuille');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (15,14,1,'feuille');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (14,14,1,'feuille');
    -- Burn
    INSERT INTO BURN (ID_UT,ID) VALUES (1,14);
    INSERT INTO BURN (ID_UT,ID) VALUES (21,14);
    INSERT INTO BURN (ID_UT,ID) VALUES (3,14);
    INSERT INTO BURN (ID_UT,ID) VALUES (2,14);
    INSERT INTO BURN (ID_UT,ID) VALUES (11,14);
    INSERT INTO BURN (ID_UT,ID) VALUES (22,14);
    INSERT INTO BURN (ID_UT,ID) VALUES (8,14);
    INSERT INTO BURN (ID_UT,ID) VALUES (15,14);
    INSERT INTO BURN (ID_UT,ID) VALUES (14,14);
    INSERT INTO BURN (ID_UT,ID) VALUES (5,14);



-- RECETTE Anchois grillés -> burn : 9
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (15,11,'Anchois grillés',20,'Des anchois au barbecue !','Avec cette chaleur, vient l\'envie d\'y aller mollo, traîner dans le jardin et peut-être aller à la plage, mais vos vacances d\'été, ont-ils déjà commencé ou devriez-vous attendre encore un peu ? Pourquoi ne pas faire comme si vous étiez déjà en vacances et préparer quelques petites brochettes de poisson grillés à la maison, que ce soit avec de petites sardines ou des grands anchois. Vous pourriez les faire griller sur une plaque chauffante ou plancha ou même au barbecue ou au four si vous préférez. Nettoyez les en avance et laissez-les reposer quelques heures dans une marinade aux herbes aromatiques, quelques épices et du jus de citron jaune et vert ! Un bon moyen facile et agréable pour vous préparer pour les vacances à venir ... ;).',1.0,'https://i.imgur.com/m1Xxtb2.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Mélanger ensemble le jus des citrons jaune et vert et de mettre de côté.',15,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Nettoyer vos anchois en faisant une incision derrière les branchies et la tête et tirez doucement pour enlever les entrailles.',15,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Enfiler les anchois nettoyés sur les bâtonnets ou brochettes.',15,3);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Dans un récipient peu profond, faire un lit de quelques feuilles de laurier et de romarin frais et placer les brochettes d\'anchois par dessus.',15,4);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Enfiler les anchois nettoyés sur les bâtonnets ou brochettes.',15,5);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Saupoudrer les brochettes d\’anchois avec un peu de sel de mer et d\’épices.',15,6);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Ajouter encore quelques feuilles de laurier et de romarin par dessus et ajouter une autre rangée de brochettes anchois par dessus et saupoudrer de sel et d\’épices et des feuilles de laurier et de romarin.',15,7);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Continuer jusqu\'à ce que tous les brochettes anchois sont disposés, puis versez le jus des citron par dessus et de fermer votre récipient et laisser mariner au réfrigérateur pendant au moins 2 heures (mais pas toute la nuit car les anchois pourraient trop se ramollir',15,8);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Lorsque vous êtes prêt, badigeonner les brochettes d\'anchois avec un peu d\'huile d\'olive et faire cuire au four pendant 5-7 minutes à 200°C ou au barbecue pendant 2 minutes de chaque côté !',15,9);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (42,15,750,'grammes');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (27,15,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (28,15,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (14,15,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (15,15,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (18,15,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,15,2,'cup');
    -- Burn
    INSERT INTO BURN (ID_UT,ID) VALUES (1,15);
    INSERT INTO BURN (ID_UT,ID) VALUES (21,15);
    INSERT INTO BURN (ID_UT,ID) VALUES (3,15);
    INSERT INTO BURN (ID_UT,ID) VALUES (13,15);
    INSERT INTO BURN (ID_UT,ID) VALUES (11,15);
    INSERT INTO BURN (ID_UT,ID) VALUES (22,15);
    INSERT INTO BURN (ID_UT,ID) VALUES (8,15);
    INSERT INTO BURN (ID_UT,ID) VALUES (9,15);
    INSERT INTO BURN (ID_UT,ID) VALUES (14,15);


-- RECETTE Brochettes au curry -> burn : 9
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (16,4,'Brochettes au curry',2,'Brochettes simples et efficaces !','De bonnes brochettes de poulet aux épices de curry et de cumin à griller au BBQ !',1.0,'https://i.imgur.com/1ZNKPiL.jpg');
    -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Coupez les blancs de poulet en gros dés.',16,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Hachez l\'échalote et la gousse d\'ail. Dans un bol, mettez le yaourt nature, les épices, l\'échalote et l\'ail. / Mélangez bien tous les ingrédients puis ajoutez les dés de poulet, mélangez encore une fois le tout.',16,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Couvrez avec un film alimentaire et laissez au réfrigérateur pendant 2 heures.',16,3);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Enfilez les dés de poulet dans des piques à brochette jusqu\'à épuisement de la viande.',16,4);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Faites griller au barbecue et dégustez !',16,5);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (31,16,2,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (13,16,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (12,16,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (17,16,1,'cuc');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (18,16,1,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,16,1,'pincée');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (25,16,1,'pincée');
    -- Burn
    INSERT INTO BURN (ID_UT,ID) VALUES (1,16);
    INSERT INTO BURN (ID_UT,ID) VALUES (21,16);
    INSERT INTO BURN (ID_UT,ID) VALUES (3,16);
    INSERT INTO BURN (ID_UT,ID) VALUES (13,16);
    INSERT INTO BURN (ID_UT,ID) VALUES (11,16);
    INSERT INTO BURN (ID_UT,ID) VALUES (6,16);
    INSERT INTO BURN (ID_UT,ID) VALUES (18,16);
    INSERT INTO BURN (ID_UT,ID) VALUES (10,16);
    INSERT INTO BURN (ID_UT,ID) VALUES (14,16);



-- RECETTE de Barbecue de sardines au lard -> burn : 0
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (17,3,'Barbecue de sardines au lard',8,'Recette particulière à faire au barbecue...','Petites sardines entourées de fines lamelles de poitrine de porc fumée puis cuites au barbecue.',1.0,'https://i.imgur.com/pVY7OJU.jpg');
 -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Allumer le barbecue. Une fois la braise prête, mettre la grille aux ¾ de la hauteur maximale pour la faire chauffer.',17,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Gratter et vider les sardines, puis les rincer rapidement à l\'eau claire.',17,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Mettre du poivre dans le ventre des sardines, puis envelopper chaque pièce dans des tranches de lard en laissant dépasser la queue et la tête.',17,3);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Poser les sardines sur la grille du barbecue et les cuire pendant 4 min de chaque côté.',17,4);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Servez et dégustez !',17,5);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (40,17,12,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (32,17,24,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (25,17,6,'pincées');


-- RECETTE Brochettes d\'abats au barbecue -> burn : 0
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (18,8,'Brochettes d\'abats',6,'Recette très très particulière à faire au barbecue...','Brochettes d\’abats qui comprends brochettes du foie d\'agneau, du cœur de mouton ou d\'agneau, du rognons d\'agneau. Il faut être prêt mentalement et résistant pour manger ce genre de plat qui est assez fort en odeur mais aussi en goût !',1.0,'https://i.imgur.com/yCasp3p.jpg');
 -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Coupez le foie, le cœur, les rognons et le lard en petits dés.',18,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Embrochez dés de foie, de cœur, de rognons et carrés de lard en les alternant.',18,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Versez l’huile dans un plat long.',18,3);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Passez les brochettes dedans, assaisonnez-les puis roulez-les dans la chapelure.',18,4);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Faites-les griller de 6 à 8 minutes de chaque côté. Puis dégustez !',18,5);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (38,18,2,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (36,18,1,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (37,18,3,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (32,18,6,'tranches');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (43,18,100,'g');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (21,18,2,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,18,2,'pincées');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (25,18,2,'pincées');


-- RECETTE enregistrée mais non soumise (brouillon utilisateur)
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (19,21,'Blanc de poulet grillé',4,'Mangez du poulet sans aucune goutte d\'huile','Blanc de poulet aux herbes grillé au barbecue.',0.0,'https://i.imgur.com/6czk3Ib.jpg');
 -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Préparer les tranches de poulet et les placer sur le barbecue.',19,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Mettre des herbes aux choix, puis salez.',19,2);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (31,19,4,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (16,19,1,'pincée');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (21,19,1,'pincée');



-- RECETTE 
INSERT INTO RECETTE (ID,ID_UT,NOM_RE,NB_CONV,DESC_C,DESC_L,STATUT,URL_IMAGE) VALUES (20,7,'Mayonnaise maison',4,'Faire de la mayonnaise maison quand on en a plus au moment de passer à table.','Mayonnaise traditionnelle à base d\'huile, de jaune d\'oeuf, de moutarde et de citron.',1.0,'https://i.imgur.com/Rsw55Vv.jpg');
 -- Etapes de la recette
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Dans un bol, mélangez le jaune d\'œuf, un peu de sel, poivre, la moutarde et quelques gouttes de jus de citron.',20,1);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Fouettez en versant peu à peu l\'huile de façon à faire épaissir la mayonnaise.',20,2);
    INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES ('Ajoutez éventuellement des herbes fraîches ciselées ou un jus de citron. Réservez au frais jusqu\’au moment de servir.',20,3);
    -- Ingredient-recette
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (30,20,1,'pièces');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (48,20,1,'cuc');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (22,20,3,'cup');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (27,20,1/2,'pièce');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (24,20,2,'pincées');
    INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (25,20,2,'pincées');
   
   -- RECETTE Hamburger
   INSERT INTO `RECETTE` (`ID`, `ID_UT`, `NOM_RE`, `NB_CONV`, `DESC_C`, `DESC_L`, `STATUT`, `URL_IMAGE`) VALUES (21, 2, 'Hamburger', 4, 'La recette classique mais efficace du fameux Hamburger.', 'DÃ©couvrez la recette d\'Hamburger qui plaira Ã  tout le monde ! Un classique mais tellement bon...', '1', 'https://i.imgur.com/Rd50Wkm.jpg');
       INSERT INTO `INGREDIENT_RECETTE` (`ID_INGR`, `ID`, `QUANTITE`, `UNITE`) VALUES
       (2, 21, 2, 'feuilles'),
       (7, 21, 1, NULL),
       (11, 21, 1, NULL),
       (47, 21, 2, 'c. à  s.'),
       (55, 21, 1, NULL),
       (54, 21, 1, NULL);
       
       INSERT INTO `ETAPE_RECETTE` (`ETAPE`, `ID`, `NUMERO`) VALUES
('Faire cuire le steak sur le barbecue.', 21, 1),
('Une fois les steak cuits, placer une tranche d\'emmental sur le steak.', 21, 2),
('Attendre que le fromage ai fondu puis retirer le steak de la cuisson.', 21, 3),
('Positionner les pains sur le barbecue pour les faire griller.', 21, 4),
('Sortir les pains du barbecue puis étaler la sauce.', 21, 5),
('Ajouter quelques tranches de tomate.', 21, 6),
('Ajouter des tranches d\'oignon.', 21, 7),
('Placer le steak par dessus.', 21, 8),
('Ajouter des tranches de tomate.', 21, 9),
('Placer quelques feuilles de salade.', 21, 10),
('Refermer l\'hamburger avec le pain du dessus.', 21, 11);


    
 -- Favori
    INSERT INTO FAVORI (ID_UT,ID) VALUES (1,8);
    INSERT INTO FAVORI (ID_UT,ID) VALUES (1,16);



