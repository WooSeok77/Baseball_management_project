create table stadium(
     id int auto_increment primary key,
     name varchar(20) unique not null,
     created_at timestamp not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


create table team(
    id int auto_increment primary key,
    stadium_id int not null,
    name varchar(20) unique not null,
    created_at timestamp not null,
    foreign key (stadium_id) references stadium(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- 총 3개의 팀
create table player(
    id int auto_increment primary key,
    team_id int not null,
    name varchar(20) unique not null,
    position varchar(50) not null,
    created_at timestamp not null,
    constraint team_id_position_UK unique (team_id, position),
    foreign key (team_id) references team(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- 9명의 선수
-- 팀당 9명, 총 27명의 선수
create table out_player(
    id int auto_increment primary key,
    layer_id int not null,
    reason varchar(50) not null,
    created_at timestamp not null,
    foreign key (player_id) references player(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;