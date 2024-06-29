
create table if not exists m10_user
(
    id bigserial primary key,
    phone varchar(16) unique not null,
    name varchar(128) not null,
    balance decimal(13, 2) not null,
    password text not null,
    updated_at timestamp without time zone not null default now(),
    created_at timestamp without time zone not null default now()
);
