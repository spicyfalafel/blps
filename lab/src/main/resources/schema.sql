create table if not exists medication (
	medication_id serial primary key,
	title text,
	description text,
    dosage text,
    reason_to_use text,
    side_effects text,
    by_recipe boolean,
    storage_conditions text
);

create table if not exists review (
	review_id serial primary key,
	approved boolean,
	mark int,
	name text,
	email text,
	review text,
    medication_id int references medication(medication_id)
);
