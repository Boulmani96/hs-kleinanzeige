drop table if exists AD;

drop table if exists CATEGORY;

create table if not exists CATEGORY
(
    ID                  int not null AUTO_INCREMENT comment '',
    PARENT_ID           int null comment '',
    NAME                varchar(60) comment '',
    primary key (ID),
    FOREIGN key (PARENT_ID) REFERENCES CATEGORY(ID)
);
CREATE TABLE USER
(
    ID         int AUTO_INCREMENT ,
    EMAIL      VARCHAR(60)          NOT NULL,
    PASSWORD   VARCHAR(60)          NOT NULL,
    FIRST_NAME VARCHAR(60)          ,
    LAST_NAME  VARCHAR(60)          ,
    PHONE      VARCHAR(60)          ,
    LOCATION   VARCHAR(60)          ,
    CREATED    TIMESTAMP  NOT NULL             ,
    primary key (ID)
);
create table if not exists AD
(
    ID                   int AUTO_INCREMENT comment '',
    CATEGORY_ID          int not null DEFAULT '0' comment '',
    TYPE                 ENUM('OFFER', 'REQUEST') not null comment '',
    TITLE                varchar(60) not null comment '',
    DESCRIPTION          varchar(60) not null comment '',
    PRICE                int  comment '',
    LOCATION             varchar(60) comment '',
    CREATED              TIMESTAMP comment '',
    primary key (ID),
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID)
);