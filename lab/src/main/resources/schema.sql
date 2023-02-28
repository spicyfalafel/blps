drop table if exists review;
drop table if exists "review";
drop table if exists medication;
drop table if exists "medication";
create table if not exists medication (
	medication_id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	title text,
	description text
);

create table if not exists review (
	review_id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	medication_id int references medication(medication_id),
	approved boolean,
	mark int,
	name text,
	email text,
	review text
);