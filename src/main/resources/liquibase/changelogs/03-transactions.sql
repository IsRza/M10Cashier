
create table if not exists transaction
(
    id bigserial primary key,
    payer_user_id bigint not null references m10_user(id),
    amount decimal(13, 2) not null,
    entry_type varchar(32) not null,
    updated_at timestamp without time zone not null default now(),
    created_at timestamp without time zone not null default now()
);
