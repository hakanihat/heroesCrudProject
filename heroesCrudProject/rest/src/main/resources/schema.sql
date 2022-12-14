
create table if not exists hero_types(
                          id_hero_types bigint not null ,
                          type_name varchar(255) null,
                          primary key (id_hero_types)
);

create table if not exists hero_attributes(
                               id_attribute bigint not null ,
                               combat int ,
                               durability int ,
                               intelligence int ,
                               power int ,
                               speed int ,
                               strength int ,
                               primary key (id_attribute)
);

create table if not exists heroes(
                       id_hero bigint not null ,
                       alignment varchar(255) null ,
                       hero_age varchar(255) null ,
                       hero_gender varchar(255) null ,
                       hero_name varchar(255) null ,
                       id_attribute bigint not null ,
                       id_hero_types bigint not null ,
                       primary key (id_hero),
                       foreign key (id_attribute) references hero_attributes,
                       foreign key (id_hero_types) references hero_types
);


create  table if not exists movies(
                        id_movie bigint not null ,
                        movie_name varchar(255) null ,
                        release_date date null ,
                        primary key (id_movie)
);

create table if not exists movies_of_the_heroes(
                                  id_movies_of_the_hero bigint not null ,
                                  id_hero bigint not null ,
                                  id_movie bigint not null ,
                                  primary key (id_movies_of_the_hero),
                                  foreign key (id_hero) references heroes,
                                  foreign key (id_movie) references movies

);