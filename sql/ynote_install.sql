# -----------------create databse ----------------------
create database if not exists ynote default charset = utf8;

# -----------------create table ----------------------
use ynote;

create table if not exists notebook -- No dbo.tablename in MySQL
(
	id int not null auto_increment,
	name nvarchar(100) not null,
	category_id int not null,
	created_date timestamp,
	primary key (id)
)engine=innodb auto_increment=1; -- MyISAM does not support foreign key

create table if not exists category
(
	id int not null auto_increment,
	name nvarchar(100) not null,
	primary key (id)
)engine=innodb auto_increment=1;

# -----------------create constraint ----------------------
alter table notebook add constraint foreign key ynote(category_id) references category(id); 

# -----------------create index ----------------------
alter table notebook add index inx_notebook_name(name);
alter table notebook add index inx_notebook_cat_name(category_id, name);

# -----------------create user ----------------------
create user 'u.ynote'@'%' identified by password '*6261203B1FE4E2741D55025F46349211FF83DCB3';
-- to avoid plaintext: IDENTIFIED BY PASSWORD '*90E462C37378CED12064BB3388827D2BA3A9B689';
-- select password('u.ynote');

# -----------------grant access ----------------------
grant select, update, insert, delete, execute on ynote.* to 'u.ynote'@'%';  -- 'No role in MySQL'

# -----------------meta data -------------------------
insert into category (id, name)
select 1, 'default' union all # union all does no do distinct which is done by union 
select 2, 'news' union all
select 3, 'sports' union all
select 4, 'technology'
on duplicate key update name = name;