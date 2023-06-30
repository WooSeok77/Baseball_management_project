insert into stadium(name, created_at) values('잠실' , now());
insert into stadium(name, created_at) values('기아챔피언스필드' , now());
insert into stadium(name, created_at) values('서울종합운동장' , now());

insert into team(stadium_id, name,created_at) values(1, '한화이글스', now());
insert into team(stadium_id, name, created_at) values(2, '두산베어스', now());
insert into team(stadium_id, name, created_at) values(3, 'LG트윈스', now());

insert into player(team_id, name, position, created_at ) values(1, '박정권', '1루수', now());
insert into player(team_id, name, position, created_at ) values(1, '이용규', '2루수', now());
insert into player(team_id, name, position, created_at ) values(1, '김민식', '3루수', now());
insert into player(team_id, name, position, created_at ) values(1, '정근우', '포수', now());
insert into player(team_id, name, position, created_at ) values(1, '양현종', '투수', now());
insert into player(team_id, name, position, created_at ) values(1, '이성열', '유격수', now());
insert into player(team_id, name, position, created_at ) values(1, '정근우', '좌익수', now());
insert into player(team_id, name, position, created_at ) values(1, '김태균', '중견수', now());
insert into player(team_id, name, position, created_at ) values(1, '최형우', '우익수', now());

insert into player(team_id, name, position, created_at ) values(2, '오재일', '1루수', now());
insert into player(team_id, name, position, created_at ) values(2, '박건우', '2루수', now());
insert into player(team_id, name, position, created_at ) values(2, '허경민', '3루수', now());
insert into player(team_id, name, position, created_at ) values(2, '최재훈', '포수', now());
insert into player(team_id, name, position, created_at ) values(2, '노경은', '투수', now());
insert into player(team_id, name, position, created_at ) values(2, '박해민', '유격수', now());
insert into player(team_id, name, position, created_at ) values(2, '박건우', '좌익수', now());
insert into player(team_id, name, position, created_at ) values(2, '김재환', '중견수', now());
insert into player(team_id, name, position, created_at ) values(2, '허경민', '우익수', now());

insert into player(team_id, name, position, created_at ) values(3, '이천웅', '1루수', now());
insert into player(team_id, name, position, created_at ) values(3, '이형종', '2루수', now());
insert into player(team_id, name, position, created_at ) values(3, '김현수', '3루수', now());
insert into player(team_id, name, position, created_at ) values(3, '라모스', '포수', now());
insert into player(team_id, name, position, created_at ) values(3, '이승현', '투수', now());
insert into player(team_id, name, position, created_at ) values(3, '오지환', '유격수', now());
insert into player(team_id, name, position, created_at ) values(3, '김현수', '좌익수', now());
insert into player(team_id, name, position, created_at ) values(3, '안치홍', '중견수', now());
insert into player(team_id, name, position, created_at ) values(3, '오지환', '우익수', now());