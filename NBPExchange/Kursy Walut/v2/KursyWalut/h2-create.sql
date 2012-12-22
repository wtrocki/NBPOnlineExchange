create table ENTITY_SELECTIONS (
  id                        integer not null,
  name                      varchar(255),
  constraint pk_ENTITY_SELECTIONS primary key (id))
;

create table CURRENCY (
  id                        integer not null,
  exchange_table_publication_date timestamp not null,
  conversion_rate           integer,
  country_name              varchar(255),
  currency_code             varchar(255),
  currency_name             varchar(255),
  exchange_rate             varchar(255),
  constraint pk_CURRENCY primary key (id))
;

create table EXCHANGES (
  publication_date          timestamp not null,
  constraint pk_EXCHANGES primary key (publication_date))
;

create sequence ENTITY_SELECTIONS_seq;

create sequence CURRENCY_seq;

create sequence EXCHANGES_seq;

alter table CURRENCY add constraint fk_CURRENCY_EXCHANGES_1 foreign key (exchange_table_publication_date) references EXCHANGES (publication_date) on delete restrict on update restrict;
create index ix_CURRENCY_EXCHANGES_1 on CURRENCY (exchange_table_publication_date);


