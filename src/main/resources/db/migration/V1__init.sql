create table author_data
(
	id bigint auto_increment
		primary key,
	date datetime null,
	email varchar(255) null,
	name varchar(255) null
)
;
create table owner_data
(
  id bigint auto_increment
    primary key,
  login varchar(255) null,
  site_admin bit null,
  author_data_id bigint null,
  constraint FKo0ajdwqcn8rncictbxnicclul
  foreign key (author_data_id) references author_data (id)
)
;

create index FKo0ajdwqcn8rncictbxnicclul
  on owner_data (author_data_id)
;
create table single_commit
(
  id bigint auto_increment
    primary key,
  message varchar(255) null,
  author_id bigint null,
  committer_id bigint null,
  constraint FK49fkhegs01i2pb4aupo7u91kv
  foreign key (author_id) references author_data (id),
  constraint FKfj70obof820xc4698qhvcccmj
  foreign key (committer_id) references author_data (id)
)
;

create index FK49fkhegs01i2pb4aupo7u91kv
  on single_commit (author_id)
;

create index FKfj70obof820xc4698qhvcccmj
  on single_commit (committer_id)
;
create table commit_data
(
	id bigint auto_increment
		primary key,
	url varchar(255) null,
	commit_id bigint null,
	constraint FKt9o90ivuiwuv9a58e70vei57n
		foreign key (commit_id) references single_commit (id)
)
;

create index FKt9o90ivuiwuv9a58e70vei57n
	on commit_data (commit_id)
;

create table github_data
(
	id bigint auto_increment
		primary key,
	commits_url varchar(255) null,
	description varchar(255) null,
	error varchar(255) null,
	full_name varchar(255) null,
	url varchar(255) null,
	watchers_count int null,
	owner_id bigint null,
	constraint FK2uykgt8xg23xuryqskhnuv020
		foreign key (owner_id) references owner_data (id)
)
;

create index FK2uykgt8xg23xuryqskhnuv020
	on github_data (owner_id)
;





