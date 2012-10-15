# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table customer_model (
  id                        bigint not null,
  login                     varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  due_date                  timestamp,
  constraint pk_customer_model primary key (id))
;

create sequence customer_model_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists customer_model;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists customer_model_seq;

