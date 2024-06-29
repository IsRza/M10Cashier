
create table if not exists totp_session
(
    id bigserial primary key,
    user_id bigint references m10_user(id),
    secret text not null,
    updated_at timestamp without time zone not null default now(),
    created_at timestamp without time zone not null default now()
);
