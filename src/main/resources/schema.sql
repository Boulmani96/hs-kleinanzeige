drop table if exists AD;

drop table if exists CATEGORY;

drop table if exists "USER";

create table if not exists CATEGORY
(
    ID                  int not null AUTO_INCREMENT comment '',
    PARENT_ID           int null comment '',
    NAME                varchar(60) comment '',
    primary key (ID),
    FOREIGN key (PARENT_ID) REFERENCES CATEGORY(ID)
);

create table if not exists "USER"
(
    ID         int AUTO_INCREMENT ,
    EMAIL      VARCHAR(60) not null ,
    PASSWORD   VARCHAR(60) ,
    FIRST_NAME varchar(60) comment '',
    LAST_NAME  varchar(60) comment '',
    PHONE      varchar(60) comment '',
    LOCATION   varchar(60) comment '',
    CREATED    TIMESTAMP comment '' ,
    primary key (ID)
);

create table if not exists AD
(
    ID                   int AUTO_INCREMENT comment '',
    CATEGORY_ID          int not null DEFAULT '0' comment '',
    USER_ID              int not null DEFAULT '0' comment '',
    TYPE                 ENUM('OFFER', 'REQUEST') not null comment '',
    TITLE                varchar(60) not null comment '',
    DESCRIPTION          varchar(60) not null comment '',
    PRICE                int  comment '',
    LOCATION             varchar(60) comment '',
    CREATED              TIMESTAMP comment '',
    primary key (ID),
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID),
    FOREIGN KEY (USER_ID) REFERENCES USER(ID)
);