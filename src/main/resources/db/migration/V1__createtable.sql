create table feedback_user (id binary not null, email varchar(255), manager_id binary, name varchar(255), password varchar(255), primary key (id));

create table user_feedback (feedback_id binary not null, creation_date timestamp, feedback varchar(255), feedback_description varchar(255), feedback_status boolean, from_user_id binary, to_user_id binary, primary key (feedback_id));


alter table user_feedback add constraint FKfc6iv73l11bt3ebf7e7mabo01 foreign key (from_user_id) references feedback_user;

alter table user_feedback add constraint FKdocspthocsctt07sdmlqa2iyj foreign key (to_user_id) references feedback_user;