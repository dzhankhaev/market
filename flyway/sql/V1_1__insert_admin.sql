INSERT INTO public.t_role(id, "name")
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

INSERT INTO public.t_user("password", username)
VALUES ('{bcrypt}$2a$10$p7dbUtK9d2Zf0fXbFAmlNe0KUqdpw4aEt.Dcji/B3f8RMgMpcEYUO', 'admin');

INSERT INTO public.t_user_role(user_id, role_id)
VALUES (1, 1);