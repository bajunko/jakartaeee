create table public.BENUTZER(


		ID serial PRIMARY KEY,
		NAME character varying(250) not null ,
		PASSWORT character varying(250) not null,
		PRIVILEGT boolean not null
	);