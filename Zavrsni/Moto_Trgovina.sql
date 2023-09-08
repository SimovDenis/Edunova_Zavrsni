drop database if exists moto_trgovina;
create database moto_trgovina;
use moto_trgovina;

create table djelatnik(
    sifra int not null primary key auto_increment,
    ime varchar(20),
    prezime varchar(20),
    broj_ugovora varchar(30),
    iban varchar(30),
    kontakt varchar(100)
);

create table kupac(
    sifra int not null primary key auto_increment,
    ime varchar(20),
    prezime varchar(20),
    kontakt varchar(100)
);

create table racun(
    sifra int not null primary key auto_increment,
    vrijeme_kupovine datetime, 
    broj_racuna varchar(50),
    nacin_placanja varchar(15),
    kupac int not null,
    djelatnik int not null
);

create table stavka(
    racun int,
    proizvod int,
    kolicina int
);

create table proizvod(
    sifra int not null primary key auto_increment,
    naziv varchar(50),
    cijena decimal(8,2),
    garancija decimal(3,1)
);

create table operater(
    sifra int not null primary key auto_increment,
    email varchar(100),
    ime varchar(20),
    lozinka varchar(50),
    oib char(11),
    prezime varchar(20)
);

alter table racun add foreign key (kupac) references kupac(sifra);
alter table racun add foreign key (djelatnik) references djelatnik(sifra);
alter table stavka add foreign key (racun) references racun(sifra);
alter table stavka add foreign key (proizvod) references proizvod(sifra);

insert into kupac(sifra, ime, prezime, kontakt) values
(null, 'Mirko', 'Fodor', 'mirko.fodor@gmail.com'),
(null, 'Denis', 'Simov', null),
(null, 'Ines', 'Ivić', '+385 91-345-8754');

insert into djelatnik(sifra, ime, prezime, broj_ugovora, iban, kontakt) values
(null, 'Kristijan', 'Lukas', '1545-16032020', 'HR1723400096642539844', 'lukask1@gmail.com'),
(null, 'Ana', 'Mikulić', '2288-08032014', 'HR7123400093332186213', 'mikulic.anci@yahoo.com'),
(null, 'Lidija', 'Sharbini', '5868-20082004', 'HR2424020068726579667', 'lilisharbini@gmail.com');

insert into racun(sifra, vrijeme_kupovine, broj_racuna, nacin_placanja, kupac, djelatnik) values
(null, '2022-04-06 09:32:47', '61902/1320/6', 'Gotovina', 1, 1),
(null, '2023-06-11 14:18:21', '73569/9867/4', 'Kartica', 2, 3),
(null, '2023-03-30 09:32:47', '12872/9952/6', 'Gotovina', 3, 2);

insert into proizvod(sifra, naziv, cijena, garancija) values
(null, 'Dainese kožne rukavice', 69.99, 2),
(null, 'Kawasaki Vulcan 900 Classic', 10429.99, 3),
(null, 'BMW R 1250 GS', 20832.16, 10);

insert into stavka(racun, proizvod, kolicina) values
(2, 2, 1),
(1, 1, 2),
(3, 3, 1);

insert into operater(sifra, email, ime, lozinka, oib, prezime) values
(null, 'adrian122@gmail.com', 'Adrian', '27*ipW5', '01940408028', 'Haludek'),
(null, 'stehpanyP@gmail.com', 'Stephany', 'wf86V3%', '37379019389', 'Podvorac'),
(null, 'alenko88@gmail.com', 'Alen', '3w%X99T', '26888060314', 'Čerenko');

/*
# koji djelatnik je posluzio koje kupce
select concat(a.ime, ' ', a.prezime) as djelatnik, concat(c.ime, ' ', c.prezime) as kupac  
from djelatnik a 
inner join racun b on a.sifra = b.djelatnik
inner join kupac c on b.kupac = c.sifra;

# sto je koji kupac kupio i kada
select a.naziv as proizvod, concat(d.ime, ' ', d.prezime) as kupac, c.vrijeme_kupovine 
from proizvod a
inner join stavka b on a.sifra = b.proizvod
inner join racun c on b.racun = c.sifra
inner join kupac d on c.kupac = d.sifra;

# koji je djelatnik prodao bmw
select concat(a.ime, ' ', a.prezime) as djelatnik, d.naziv 
from djelatnik a
inner join racun b on a.sifra = b.djelatnik 
inner join stavka c on b.sifra = c.racun 
inner join proizvod d on c.proizvod = d.sifra
where d.naziv like '%BMW%';
*/


