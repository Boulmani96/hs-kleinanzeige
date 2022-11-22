drop table if exists AD;

drop table if exists CATEGORY;

create table if not exists CATEGORY
(
    ID            INTEGER AUTO_INCREMENT comment '',
    PARENT_ID     INTEGER null comment '',
    NAME          varchar(60) comment '',
    primary key (ID),
    FOREIGN key (PARENT_ID) REFERENCES CATEGORY(ID)
);

create table if not exists AD
(
    ID                 INTEGER AUTO_INCREMENT comment '',
    CATEGORY_ID        INTEGER not null DEFAULT '0' comment '',
    TYPE               ENUM('OFFER', 'REQUEST') not null comment '',
    TITLE              varchar(60) not null comment '',
    DESCRIPTION        varchar(60) not null comment '',
    PRICE              INTEGER  comment '',
    LOCATION           varchar(60) comment '',
    CREATED            TIMESTAMP comment '',
    primary key (ID),
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID)
);