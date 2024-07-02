--if db personDB is not yet existing:
--to create DB beforehand, via terminal: sudo -u postgres createdb personDB

CREATE SCHEMA eventstore;
--drop table eventstore.person;
CREATE TABLE eventstore.person (
    p_event_id  NUMERIC(5)       NOT NULL,
    pid         NUMERIC(5)       NOT NULL,
    event_type  VARCHAR(50)      NOT NULL,
	event_data  VARCHAR(500)     NOT NULL,
    pe_version  NUMERIC(5)       NOT NULL
);

alter table eventstore.person
add constraint pk_person_event
primary key(p_event_id);

delete from eventstore.person;

insert into eventstore.person (p_event_id,pid,event_type,event_data,pe_version) 
	values (1, 1, 'create', '', 1);
insert into eventstore.person (p_event_id,pid,event_type,event_data,pe_version) 
	values (2, 1, 'update_name', '("Ute", "Mustermann")', 2);
insert into eventstore.person (p_event_id,pid,event_type,event_data,pe_version) 
	values (3, 1, 'update_channel_list', '[("mobile phone", "0160/1111111"),("e-mail", "ute.mustermann@web.de")]', 3);
insert into eventstore.person (p_event_id,pid,event_type,event_data,pe_version) 
	values (4, 2, 'create', '', 1);
insert into eventstore.person (p_event_id,pid,event_type,event_data,pe_version) 
	values (5, 2, 'update_name', '("Max", "Beispiel")', 2);
insert into eventstore.person (p_event_id,pid,event_type,event_data,pe_version) 
	values (6, 2, 'update_channel_list', '[("mobile phone", "0160/2222222")]', 3);

	