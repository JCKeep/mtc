drop table if exists t_all_user;

/*==============================================================*/
/* Table: t_all_user                                            */
/*==============================================================*/
create table t_all_user
(
   user_id              bigint(5) not null,
   user_name            varchar(20) not null,
   user_email           varchar(32) not null,
   user_portrait        blob not null,
   user_sex             char(1) not null,
   user_password        varchar(20) not null,
   user_bloodtype       char(2) not null,
   user_birthday        date not null,
   user_height          int not null,
   user_type            char(2) not null,
   user_permission      varchar(20) not null,
   user_keyword         varchar(100) not null,
   primary key (user_id)
);


drop table if exists t_background_administrator;

/*==============================================================*/
/* Table: t_background_administrator                            */
/*==============================================================*/
create table t_background_administrator
(
   admin_id             bigint(5) not null,
   admin_password       varchar(20) not null,
   primary key (admin_id)
);


drop table if exists t_log_login;

/*==============================================================*/
/* Table: t_log_login                                           */
/*==============================================================*/
create table t_log_login
(
   login_id             bigint(5) not null,
   user_id              bigint(5) not null,
   login_time           time not null,
   primary key (login_id)
);

alter table t_log_login add constraint FK_fk_user_login foreign key (user_id)
      references t_all_user (user_id) on delete restrict on update restrict;


drop table if exists t_personal_message;

/*==============================================================*/
/* Table: t_personal_message                                    */
/*==============================================================*/
create table t_personal_message
(
   message_id           bigint(5) not null,
   user_id              bigint(5) not null,
   message_from         varchar(20) not null,
   message_content      text not null,
   message_type         varchar(10) not null,
   primary key (message_id)
);

alter table t_personal_message add constraint FK_pk_user_message foreign key (user_id)
      references t_all_user (user_id) on delete restrict on update restrict;


drop table if exists t_community_drug;

/*==============================================================*/
/* Table: t_community_drug                                      */
/*==============================================================*/
create table t_community_drug
(
   drug_id              bigint(5) not null,
   drug_name            varchar(15) not null,
   drug_image           blob,
   drug_detail          text not null,
   primary key (drug_id)
);


drop table if exists t_community_food;

/*==============================================================*/
/* Table: t_community_food                                      */
/*==============================================================*/
create table t_community_food
(
   food_id              bigint(5) not null,
   food_name            varchar(15) not null,
   food_image           blob,
   food_suger           float not null,
   food_protein         float not null,
   food_fat             float not null,
   food_energy          float not null,
   food_introduction    text not null,
   primary key (food_id)
);


drop table if exists t_community_doctorverify;

/*==============================================================*/
/* Table: t_community_doctorverify                              */
/*==============================================================*/
create table t_community_doctorverify
(
   doctor_id            bigint(5) not null,
   user_id              bigint(5) not null,
   doctor_idnumber      char(18) not null,
   doctor_hospital      varchar(20) not null,
   doctor_qualification blob not null,
   verify_state         varchar(10) not null,
   admin_id             bigint(5) not null,
   primary key (doctor_id)
);

alter table t_community_doctorverify add constraint FK_fk_admin_doctorverigy foreign key (admin_id)
      references t_background_administrator (admin_id) on delete restrict on update restrict;

alter table t_community_doctorverify add constraint FK_fk_user_doctorverify foreign key (user_id)
      references t_all_user (user_id) on delete restrict on update restrict;


drop table if exists t_community_relation;

/*==============================================================*/
/* Table: t_community_relation                                  */
/*==============================================================*/
create table t_community_relation
(
   relation_id          bigint(5) not null,
   user_id              bigint(5) not null,
   doctor_id            bigint(5) not null,
   primary key (relation_id)
);

alter table t_community_relation add constraint FK_fk_doctor_relation foreign key (doctor_id)
      references t_community_doctorverify (doctor_id) on delete restrict on update restrict;

alter table t_community_relation add constraint FK_fk_user_relation foreign key (user_id)
      references t_all_user (user_id) on delete restrict on update restrict;


drop table if exists t_community_chat;

/*==============================================================*/
/* Table: t_community_chat                                      */
/*==============================================================*/
create table t_community_chat
(
   chat_id              bigint(5) not null,
   relation_id          bigint(5) not null,
   chat_time            time not null,
   chat_content         text not null,
   chat_from            char(2) not null,
   primary key (chat_id)
);

alter table t_community_chat add constraint FK_fk_relation_chat foreign key (relation_id)
      references t_community_relation (relation_id) on delete restrict on update restrict;


drop table if exists t_community_post;

/*==============================================================*/
/* Table: t_community_post                                      */
/*==============================================================*/
create table t_community_post
(
   post_id              bigint(10) not null,
   user_id              bigint(5) not null,
   user_name            varchar(20) not null,
   post_time            time not null,
   post_title           varchar(20) not null,
   post_content         text not null,
   post_image           blob,
   like_number          bigint(5) not null,
   favorite_number      bigint(5) not null,
   primary key (post_id)
);

alter table t_community_post add constraint FK_fk_user_post foreign key (user_id)
      references t_all_user (user_id) on delete restrict on update restrict;


drop table if exists t_community_interaction;

/*==============================================================*/
/* Table: t_community_interaction                               */
/*==============================================================*/
create table t_community_interaction
(
   interaction_id       bigint(5) not null,
   post_id              bigint(10) not null,
   user_id              bigint(5) not null,
   is_like              boolean not null,
   is_favorite          boolean not null,
   primary key (interaction_id)
);

alter table t_community_interaction add constraint FK_fk_post_interaction foreign key (post_id)
      references t_community_post (post_id) on delete restrict on update restrict;

alter table t_community_interaction add constraint FK_fk_user_interaction foreign key (user_id)
      references t_all_user (user_id) on delete restrict on update restrict;


drop table if exists t_diet_record;

/*==============================================================*/
/* Table: t_diet_record                                         */
/*==============================================================*/
create table t_diet_record
(
   diet_id              bigint(5) not null,
   food_id              bigint(5) not null,
   user_id              bigint(5) not null,
   diet_date            date not null,
   diet_type            char(2) not null,
   primary key (diet_id)
);

alter table t_diet_record add constraint FK_fk_food_diet foreign key (food_id)
      references t_community_food (food_id) on delete restrict on update restrict;

alter table t_diet_record add constraint FK_fk_user_diet foreign key (user_id)
      references t_all_user (user_id) on delete restrict on update restrict;


drop table if exists t_health_medication;

/*==============================================================*/
/* Table: t_health_medication                                   */
/*==============================================================*/
create table t_health_medication
(
   medication_id        bigint(5) not null,
   user_id              bigint(5) not null,
   drug_id              bigint(5) not null,
   medication_date      date not null,
   medication_type      char(2) not null,
   is_take              boolean not null,
   primary key (medication_id)
);

alter table t_health_medication add constraint FK_fk_drug_medication foreign key (drug_id)
      references t_community_drug (drug_id) on delete restrict on update restrict;

alter table t_health_medication add constraint FK_fk_user_medication foreign key (user_id)
      references t_all_user (user_id) on delete restrict on update restrict;


drop table if exists t_health_record;

/*==============================================================*/
/* Table: t_health_record                                       */
/*==============================================================*/
create table t_health_record
(
   health_record_id     bigint(5) not null,
   user_id              bigint(5) not null,
   record_date          date not null,
   user_bloodsugar      decimal(3,1) not null,
   user_low_bloodpressure int not null,
   user_high_bloodpressure int not null,
   user_heartrate       int not null,
   user_weight          int not null,
   user_health_condition varchar(5) not null,
   primary key (health_record_id)
);

alter table t_health_record add constraint FK_fk_user_health foreign key (user_id)
      references t_all_user (user_id) on delete restrict on update restrict;


drop table if exists t_health_suggest;

/*==============================================================*/
/* Table: t_health_suggest                                      */
/*==============================================================*/
create table t_health_suggest
(
   suggest_id           bigint(5) not null,
   health_data_type     char(2) not null,
   health_data_range    varchar(10) not null,
   suggest_content      text not null,
   primary key (suggest_id)
);
