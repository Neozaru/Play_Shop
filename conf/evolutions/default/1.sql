# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cart_model (
  id                        bigint not null,
  customer_id               bigint,
  product_id                bigint,
  quantity                  integer,
  constraint pk_cart_model primary key (id))
;

create table customer_model (
  id                        bigint not null,
  login                     varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  due_date                  timestamp,
  constraint pk_customer_model primary key (id))
;

create table product_model (
  id                        bigint not null,
  label                     varchar(255),
  quantity                  integer,
  category                  varchar(255),
  constraint pk_product_model primary key (id))
;

create sequence cart_model_seq;

create sequence customer_model_seq;

create sequence product_model_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists cart_model;

drop table if exists customer_model;

drop table if exists product_model;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists cart_model_seq;

drop sequence if exists customer_model_seq;

drop sequence if exists product_model_seq;

