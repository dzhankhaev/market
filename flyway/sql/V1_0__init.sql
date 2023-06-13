CREATE TABLE public.t_role (
    id bigint NOT NULL,
    "name" varchar(255) NULL,
    CONSTRAINT t_role_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE public.seq_user_id;

CREATE TABLE public.t_user (
    id bigint NOT NULL DEFAULT (nextval('seq_user_id')),
    "password" varchar(255) NULL,
    username varchar(255) NULL,
    CONSTRAINT t_user_pkey PRIMARY KEY (id)
);

CREATE TABLE public.t_user_role (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT t_user_roles_pkey PRIMARY KEY (user_id, role_id)
);

ALTER TABLE public.t_user_role ADD CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES public.t_role(id);
ALTER TABLE public.t_user_role ADD CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES public.t_user(id);