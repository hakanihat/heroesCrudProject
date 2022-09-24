INSERT INTO HeroTypes (id_hero_types,type_name) values (1,'melee');
INSERT INTO HeroTypes (id_hero_types,type_name) values (2,'ranged');

INSERT INTO HeroAttributes(id_attribute,combat,durability,intelligence,power,speed,strength) values (1,64,80,38,24,17,100 );
INSERT INTO HeroAttributes(id_attribute,combat,durability,intelligence,power,speed,strength) values (687,84,84,75,86,65,57 );

INSERT INTO Heroes(id_hero, alignment,hero_age,hero_gender,hero_name,id_attribute,id_hero_types) values (1,'good','unknown','male','A-Bomb',1,1);
INSERT INTO Heroes(id_hero, alignment,hero_age,hero_gender,hero_name,id_attribute,id_hero_types) values (687,'bad','unknown','male','Venom',687,1);

INSERT INTO Movies(id_movie,movie_name,release_date) values (1,'Avengers: Infinity War','2018-04-23');
INSERT INTO Movies(id_movie,movie_name,release_date) values (2,'Avengers: Endgame','2019-04-26');
INSERT INTO Movies(id_movie,movie_name,release_date) values (3,'Captain Marvel','2019-03-07');

INSERT INTO MoviesOfTheHeroes(id_movies_of_the_hero,id_hero,id_movie) values (1,1,1);
INSERT INTO MoviesOfTheHeroes(id_movies_of_the_hero,id_hero,id_movie) values (2,1,2);
INSERT INTO MoviesOfTheHeroes(id_movies_of_the_hero,id_hero,id_movie) values (3,1,3);
