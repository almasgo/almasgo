alter table content drop foreign key FK2i381qaxxpsil3xu3qv6hljjb
alter table engine drop foreign key FKhja78e82sp6bo6sktns3k4uw8
drop table if exists content
drop table if exists engine
drop table if exists hibernate_sequence
drop table if exists user
create table content (id bigint not null, attributes text, description text, external_unique_id varchar(48), tags text, title varchar(255), visibility integer, engine_id bigint, primary key (id)) engine=InnoDB
create table engine (id bigint not null, created_at datetime(6), updated_at datetime(6), active integer, api_key varchar(64), name varchar(48), type varchar(255), user_id bigint, primary key (id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table user (id bigint not null auto_increment, email varchar(64), name varchar(64), password varchar(64), primary key (id)) engine=InnoDB
alter table user add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table content add constraint FK2i381qaxxpsil3xu3qv6hljjb foreign key (engine_id) references engine (id)
alter table engine add constraint FKhja78e82sp6bo6sktns3k4uw8 foreign key (user_id) references user (id)