CREATE SEQUENCE user_id_seq
	START WITH 1
	INCREMENT 1
	NO MINVALUE
	NO MAXVALUE
	CACHE 1;

CREATE SEQUENCE doc_id_seq
	START WITH 1
	INCREMENT 1
	NO MINVALUE
	NO MAXVALUE
	CACHE 1;

CREATE SEQUENCE pat_id_seq
	START WITH 1
	INCREMENT 1
	NO MINVALUE
	NO MAXVALUE
	CACHE 1;

CREATE SEQUENCE cln_id_seq
	START WITH 1
	INCREMENT 1
	NO MINVALUE
	NO MAXVALUE
	CACHE 1;

CREATE SEQUENCE sch_id_seq
	START WITH 1
	INCREMENT 1
	NO MINVALUE
	NO MAXVALUE
	CACHE 1;

CREATE SEQUENCE vds_id_seq
	START WITH 1
	INCREMENT 1
	NO MINVALUE
	NO MAXVALUE
	CACHE 1;
	
CREATE SEQUENCE role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
CREATE TABLE role(
	rol_id bigint DEFAULT nextval('role_id_seq'::regclass) NOT NULL,
	rol_name character varying(50) NOT NULL,
	rol_created timestamp without time zone NOT NULL,
	rol_version integer NOT NULL,
	CONSTRAINT role_pkey PRIMARY KEY(rol_id)
);

CREATE TABLE user_data
(
	usr_id bigint DEFAULT nextval('user_id_seq'::regclass) NOT NULL,
	usr_firstname character varying(25) NOT NULL,
	usr_lastname character varying(25) NOT NULL,
	usr_birthdate date NOT NULL,
	usr_phone integer NOT NULL,
	usr_address character varying(100) NOT NULL,
	usr_email character varying(50) NOT NULL,
	usr_pesel integer NOT NULL,
	usr_password character varying(255) NOT NULL,
	CONSTRAINT user_pkey PRIMARY KEY(usr_id)
);

CREATE TABLE patient
(
	pat_id bigint DEFAULT nextval('pat_id_seq'::regclass) NOT NULL,
	pat_status character varying(20) NOT NULL,
	usr_id bigint NOT NULL,
	CONSTRAINT pat_pkey PRIMARY KEY(pat_id),
	CONSTRAINT pat_usr_fkey
		FOREIGN KEY(usr_id) references user_data(usr_id)
		MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE doctor
(
	doc_id bigint DEFAULT nextval('doc_id_seq'::regclass) NOT NULL,
	doc_status character varying(20) NOT NULL,
	usr_id bigint NOT NULL,
	CONSTRAINT doc_pkey PRIMARY KEY(doc_id),
	CONSTRAINT doc_usr_fkey
		FOREIGN KEY(usr_id) references user_data(usr_id)
		MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE schedule
(
	sch_id bigint DEFAULT nextval('sch_id_seq'::regclass) NOT NULL,
	sch_day date NOT NULL,
	sch_hour_start time NOT NULL,
	sch_hour_end time NOT NULL,
	doc_id bigint NOT NULL,
	CONSTRAINT sch_pkey PRIMARY KEY(sch_id),
	CONSTRAINT sch_doc_fkey
		FOREIGN KEY(doc_id) references doctor(doc_id)
		MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE calendar
(
	cln_id bigint DEFAULT nextval('cln_id_seq'::regclass) NOT NULL,
	cln_date timestamp NOT NULL,
	sch_id bigint NOT NULL,
	pat_id bigint NOT NULL,
	CONSTRAINT cln_pkey PRIMARY KEY(cln_id),
	CONSTRAINT cln_sch_fkey
		FOREIGN KEY(sch_id) references schedule(sch_id)
		MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT cln_pat_fkey
		FOREIGN KEY(pat_id) references patient(pat_id)
		MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
	
);

CREATE TABLE visits_discription
(
	vds_id bigint DEFAULT nextval('vds_id_seq'::regclass) NOT NULL,
	vds_doc_comment character varying(200) NOT NULL,
	vds_pat_comment character varying(200) NOT NULL,
	cln_id bigint NOT NULL,
	CONSTRAINT vds_pkey PRIMARY KEY(vds_id),
	CONSTRAINT vds_cln_fkey
		FOREIGN KEY(cln_id) references calendar(cln_id)
		MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
);