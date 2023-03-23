create table users_roles
(
    user_id  bigint not null,
    roles_id bigint not null,
    primary key (user_id, roles_id)
)
    engine = MyISAM;

create index FKa62j07k5mhgifpp955h37ponj
    on users_roles (roles_id);

