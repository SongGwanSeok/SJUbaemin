insert into "MEMBER" (MEMBER_ID, LOGIN_ID, LOGIN_PW, USERNAME, ACTIVATED)
values (0, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);

insert into "AUTHORITY" (authority_name) values ('ROLE_USER');
insert into "AUTHORITY" (authority_name) values ('ROLE_ADMIN');

insert into "MEMBER_AUTHORITY" (MEMBER_ID, authority_name) values (0, 'ROLE_USER');
insert into "MEMBER_AUTHORITY" (MEMBER_ID, authority_name) values (0, 'ROLE_ADMIN');
