create table users
(
    id         bigint primary key not null auto_increment,
    name       varchar(40)        not null,
    created_at timestamp,
    updated_at timestamp
);

create table sample_memo
(
    id        bigint primary key not null auto_increment,
    user_id   bigint not null,
    user_name varchar(40) not null,
    subject   varchar(40)        not null,
    memo      text               not null,
    created_at timestamp,
    updated_at timestamp
);

create table sample_category
(
    id bigint primary key not null  auto_increment,
    name varchar(40) not null,
    register_count bigint,
    created_at timestamp,
    updated_at timestamp
);
