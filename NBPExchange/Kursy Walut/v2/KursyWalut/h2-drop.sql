SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists ENTITY_SELECTIONS;

drop table if exists CURRENCY;

drop table if exists EXCHANGES;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists ENTITY_SELECTIONS_seq;

drop sequence if exists CURRENCY_seq;

drop sequence if exists EXCHANGES_seq;

