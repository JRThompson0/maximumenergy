create table roles (
    role_id integer not null auto_increment,
    role_name varchar(255) not null,
    primary key (role_id)
) engine=InnoDB;

create table user_roles (
    role_id integer not null,
    user_id integer not null
) engine=InnoDB;

create table users (
    enabled bit not null,
    user_id integer not null auto_increment,
    email varchar(60),
    name varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (user_id)
) engine=InnoDB;

alter table users 
    add constraint UK6dotkott2kjsp8vw4d0m25fb7 unique (email);

alter table user_roles 
    add constraint FKh8ciramu9cc9q3qcqiv4ue8a6 
    foreign key (role_id) 
    references roles (role_id);

alter table user_roles 
    add constraint FKhfh9dx7w3ubf1co1vdev94g3f 
    foreign key (user_id) 
    references users (user_id);