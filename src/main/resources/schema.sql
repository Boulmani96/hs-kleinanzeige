#drop table if exists AD;

#drop table if exists CATEGORY;

#drop table if exists USER;

create table if not exists CATEGORY
(
    ID            INTEGER AUTO_INCREMENT comment '',
    PARENT_ID     INTEGER comment '',
    NAME          varchar(60) comment '',
    primary key (ID),
    FOREIGN key (PARENT_ID) REFERENCES CATEGORY(ID)
);

create table if not exists USER
(
    ID         INTEGER AUTO_INCREMENT ,
    EMAIL      VARCHAR(60) not null ,
    PASSWORD   VARCHAR(60) not null comment '' ,
    FIRST_NAME varchar(255) comment '',
    LAST_NAME  varchar(255) comment '',
    PHONE      varchar(60) comment '',
    LOCATION   varchar(255) comment '',
    CREATED    TIMESTAMP not null comment '',
    primary key (ID)
);

create table if not exists AD
(
    ID                 INTEGER AUTO_INCREMENT comment '',
    CATEGORY_ID        INTEGER not null DEFAULT '0' comment '',
    USER_ID            INTEGER not null DEFAULT '0' comment '',
    TYPE               ENUM('OFFER', 'REQUEST') not null comment '',
    TITLE              varchar(255) not null comment '',
    DESCRIPTION        varchar(255) not null comment '',
    PRICE              INTEGER comment '',
    LOCATION           varchar(255) comment '',
    CREATED            TIMESTAMP not null comment '',
    primary key (ID),
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID),
    FOREIGN KEY (USER_ID) REFERENCES USER(ID)
);