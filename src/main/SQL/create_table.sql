create table creditcards
(
	id varchar(20) not null
		primary key,
	firstName varchar(50) not null,
	lastName varchar(50) not null,
	expiration date not null,
	constraint creditcards_id_uindex
		unique (id)
)
engine=InnoDB
;

create table cuisine
(
	id int auto_increment
		primary key,
	type varchar(32) not null,
	rating float not null,
	constraint cuisine_cid_uindex
		unique (id)
)
engine=InnoDB
;

create table cuisine_in_restau
(
	cid int not null,
	rid varchar(10) not null,
	constraint FK1mx08g9j3fpydu1ln1kryt4cp
		foreign key (cid) references cuisine (id),
	constraint cuisine_in_restau_cuisine_cid_fk
		foreign key (cid) references cuisine (id)
)
engine=InnoDB
;

create index cuisine_in_restau_cuisine_cid_fk
	on cuisine_in_restau (cid)
;

create index cuisine_in_restau_restuarants_rid_fk
	on cuisine_in_restau (rid)
;

create table customers
(
	id int auto_increment
		primary key,
	firstName varchar(50) not null,
	lastName varchar(50) not null,
	ccId varchar(20) not null,
	address varchar(200) not null,
	email varchar(50) not null,
	password varchar(20) not null,
	constraint customers_id_uindex
		unique (id)
)
engine=InnoDB
;

create table dishes
(
	id varchar(10) not null
		primary key,
	name varchar(100) not null,
	cid int not null,
	constraint dishes_id_uindex
		unique (id)
)
engine=InnoDB
;

create table dishes_in_restau
(
	did varchar(10) not null,
	rid varchar(10) not null,
	constraint FKr8sn543arh2mrehfsm7qayixr
		foreign key (did) references dishes (id),
	constraint dishes_in_restau_dishes_did_fk
		foreign key (did) references Dishes (id)
)
engine=InnoDB
;

create index dishes_in_restau_dishes_did_fk
	on dishes_in_restau (did)
;

create index dishes_in_restau_restuarants_rid_fk
	on dishes_in_restau (rid)
;

create table ratings
(
	rid varchar(10) not null,
	rating float not null,
	numVotes int not null,
	id varchar(255) not null,
	num_votes int not null
)
engine=InnoDB
;

create index ratings_restuarants_rid_fk
	on ratings (rid)
;

create table restaurants
(
	id varchar(10) not null
		primary key,
	name varchar(50) not null,
	addr varchar(100) not null,
	image varchar(200) null,
	constraint restuarants_id_uindex
		unique (id)
)
engine=InnoDB
;

alter table cuisine_in_restau
	add constraint FK4jpkj6pd1wm5iw8ugpwsmb8m2
		foreign key (rid) references restaurants (id)
;

alter table cuisine_in_restau
	add constraint cuisine_in_restau_restuarants_rid_fk
		foreign key (rid) references restaurants (id)
;

alter table dishes_in_restau
	add constraint FKjqsql0u6lue5x24a9f7m5pp24
		foreign key (rid) references restaurants (id)
;

alter table dishes_in_restau
	add constraint dishes_in_restau_Restuarants_rid_fk
		foreign key (rid) references restaurants (id)
;

alter table ratings
	add constraint ratings_restuarants_rid_fk
		foreign key (rid) references restaurants (id)
;

create table role
(
	role_id int auto_increment
		primary key,
	role varchar(255) null
)
engine=InnoDB
;

create table sales
(
	id int auto_increment
		primary key,
	customerId int not null,
	rid varchar(10) not null,
	saleDate date not null,
	constraint sales_id_uindex
		unique (id),
	constraint sales_customers_id_fk
		foreign key (customerId) references customers (id),
	constraint sales_restuarants_rid_fk
		foreign key (rid) references restaurants (id)
)
engine=InnoDB
;

create index sales_customers_id_fk
	on sales (customerId)
;

create index sales_restuarants_rid_fk
	on sales (rid)
;

create table user
(
	user_id int auto_increment
		primary key,
	active int null,
	email varchar(255) not null,
	last_name varchar(255) not null,
	name varchar(255) not null,
	password varchar(255) not null
)
engine=InnoDB
;

create table user_role
(
	user_id int not null,
	role_id int not null,
	primary key (user_id, role_id),
	constraint FK859n2jvi8ivhui0rl0esws6o
		foreign key (user_id) references user (user_id),
	constraint FKa68196081fvovjhkek5m97n3y
		foreign key (role_id) references role (role_id)
)
engine=InnoDB
;

create index FKa68196081fvovjhkek5m97n3y
	on user_role (role_id)
;

