create table if not exists project (
    id bigint not null auto_increment primary key,
    title varchar(30) not null default '',
    author varchar(30) not null default '',
    description varchar(100) not null default '',
    project_path varchar(200) not null default '',
    deleted tinyint not null default 0,
    add_time datetime not null default '1970-01-01 00:00:00',
    modify_time datetime not null default '1970-01-01 00:00:00' on update current_timestamp
);

create table if not exists project_db (
    id bigint not null auto_increment primary key,
    host varchar(30) not null default '',
    port int not null default 0,
    user varchar(30) not null default '',
    password varchar(30) not null default '',
    dbname varchar(30) not null default '',
    project_id bigint not null,
    deleted tinyint not null default 0,
    add_time datetime not null default '1970-01-01 00:00:00',
    modify_time datetime not null default '1970-01-01 00:00:00' on update current_timestamp
);

create table if not exists project_db_table (
    id bigint not null auto_increment primary key,
    project_db_id bigint not null,
    table_name varchar(50) not null default '',
    deleted tinyint not null default 0,
    add_time datetime not null default '1970-01-01 00:00:00',
    modify_time datetime not null default '1970-01-01 00:00:00' on update current_timestamp
);