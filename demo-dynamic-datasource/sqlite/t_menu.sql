create table t_menu
(
    id        integer not null
        constraint t_menu_pk
            primary key autoincrement,
    name      TEXT    not null,
    parent_id integer,
    url       TEXT
);
