set schema 'public';

create table product_image (
   id  serial not null,
    active boolean default true,
    register_date timestamp,
    updated_date timestamp,
    description varchar(255),
    image text,
    name varchar(255),
    uuid uuid,
    id_product int4 not null,
    primary key (id)
);

alter table product_image
   add constraint FKctqn46eat9xvm9qq5wvtwc13d
   foreign key (id_product)
   references product;