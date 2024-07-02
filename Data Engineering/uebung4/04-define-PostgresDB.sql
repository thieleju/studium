--to create DB beforehand, via terminal: sudo -u postgres createdb personDB

CREATE SCHEMA coredata;

CREATE TABLE coredata.person (
    pid        NUMERIC(5)        NOT NULL,
	first_name VARCHAR (25)      NOT NULL,
    last_name  VARCHAR (25)      NOT NULL
);

CREATE TABLE coredata.communication_channel_type (
    cctid      NUMERIC(5)        NOT NULL,
	type       VARCHAR (50)      NOT NULL
);

CREATE TABLE coredata.person_communication_channel (
    pccid      NUMERIC(5)        NOT NULL,
	pid        NUMERIC(5)        NOT NULL,
    cctid      NUMERIC(5)        NOT NULL,
    identifier VARCHAR (50)      NOT NULL
);

alter table coredata.person
add constraint pk_person
primary key(pid);

alter table coredata.communication_channel_type
add constraint pk_communication_channel_type 
primary key(cctid);

alter table coredata.person_communication_channel 
add constraint pk_person_communication_channel
primary key(pccid);

alter table coredata.person_communication_channel
add constraint fk_person_communication_channel__person
foreign key(pid) references coredata.person(pid);

alter table coredata.person_communication_channel
add constraint fk_person_communication_channel__communication_channel_type
foreign key(cctid) references coredata.communication_channel_type(cctid);

delete from coredata.person_communication_channel;
delete from coredata.communication_channel_type;
delete from coredata.person;

insert into coredata.person (pid,first_name,last_name) 
	values (1, 'Ute', 'Mustermann');
insert into coredata.person(pid,first_name,last_name) 
	values (2, 'Max', 'Beispiel');
insert into coredata.communication_channel_type(cctid, type) 
	values (1, 'mobile phone');
insert into coredata.communication_channel_type(cctid, type) 
	values (2, 'e-mail');
insert into coredata.person_communication_channel(pccid, pid, cctid, identifier) 
	values (1, 1, 1, '0160/1111111');
insert into coredata.person_communication_channel(pccid, pid, cctid, identifier) 
	values (2, 1, 2, 'ute.mustermann@web.de');
insert into coredata.person_communication_channel(pccid, pid, cctid, identifier) 
	values (3, 2, 1, '0160/2222222');