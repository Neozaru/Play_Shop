# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cart (
  id                        bigint not null,
  constraint pk_cart primary key (id))
;

create table customer (
  id                        bigint not null,
  login                     varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  registration_date         timestamp,
  first_name                varchar(255),
  last_name                 varchar(255),
  constraint pk_customer primary key (id))
;

create table product_info (
  id                        bigint not null,
  label                     varchar(255),
  description               varchar(255),
  category                  varchar(255),
  constraint pk_product_info primary key (id))
;

create table product_purchased (
  id                        bigint not null,
  price                     float,
  quantity                  integer,
  constraint pk_product_purchased primary key (id))
;

create table product_sale (
  id                        bigint not null,
  price                     float,
  stock                     integer,
  constraint pk_product_sale primary key (id))
;

create sequence cart_seq;

create sequence customer_seq;

create sequence product_info_seq;

create sequence product_purchased_seq;

create sequence product_sale_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists cart;

drop table if exists customer;

drop table if exists product_info;

drop table if exists product_purchased;

drop table if exists product_sale;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists cart_seq;

drop sequence if exists customer_seq;

drop sequence if exists product_info_seq;

drop sequence if exists product_purchased_seq;

drop sequence if exists product_sale_seq;

