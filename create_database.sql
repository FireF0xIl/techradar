/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     26.10.2024 18:55:51                          */
/*==============================================================*/


/*==============================================================*/
/* Table: categories                                            */
/*==============================================================*/
create table categories (
   cat_id               BIGINT               not null,
   cat_name             VARCHAR(255)         not null,
   constraint categories_pk primary key (cat_id),
   constraint categories_uk1 unique (cat_name)
);

comment on column categories.cat_id is
'Уникальный номер категории';

comment on column categories.cat_name is
'Наименование категории';

/*==============================================================*/
/* Table: functions                                             */
/*==============================================================*/
create table functions (
   fun_id               BIGINT               not null,
   fun_name             VARCHAR(255)         not null,
   constraint functions_pk primary key (fun_id),
   constraint functions_uk1 unique (fun_name)
);

comment on column functions.fun_id is
'Уникальный номер функции';

comment on column functions.fun_name is
'Описание функции';

/*==============================================================*/
/* Table: roles                                                 */
/*==============================================================*/
create table roles (
   role_id              BIGINT               not null,
   role_name            VARCHAR(255)         not null,
   constraint roles_pk primary key (role_id),
   constraint roles_uk1 unique (role_name)
);

comment on column roles.role_id is
'Уникальный номер роли';

comment on column roles.role_name is
'Наименование роли';

/*==============================================================*/
/* Table: mapping                                               */
/*==============================================================*/
create table mapping (
   id                   SERIAL               not null,
   role_id              BIGINT               not null,
   fun_id               BIGINT               not null,
   constraint mapping_pk primary key (id),
   constraint mapping_roles_fk foreign key (role_id)
      references roles (role_id)
      on delete restrict on update restrict,
   constraint mapping_functions_fk foreign key (fun_id)
      references functions (fun_id)
      on delete restrict on update restrict
);

comment on column mapping.id is
'Уникальный идентификатор технологии';

comment on column mapping.role_id is
'Уникальный идентификатор роли';

comment on column mapping.fun_id is
'Уникальный идентификатор секции';

/*==============================================================*/
/* Index: mapping_roles_i                                       */
/*==============================================================*/
create  index mapping_roles_i on mapping (
role_id
);

/*==============================================================*/
/* Index: mapping_functions_i                                   */
/*==============================================================*/
create  index mapping_functions_i on mapping (
fun_id
);

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users (
   user_id              BIGINT               not null,
   role_id              BIGINT               not null,
   login                VARCHAR(255)         not null,
   password             VARCHAR(255)         not null,
   refresh_token        VARCHAR(255)         not null,
   constraint users_pk primary key (user_id),
   constraint users_uk1 unique (login),
   constraint users_roles_fk foreign key (role_id)
      references roles (role_id)
      on delete restrict on update restrict
);

comment on column users.user_id is
'Уникальный идентификатор пользователя';

comment on column users.role_id is
'Уникальный идентификатор роли';

comment on column users.login is
'Уникальный логин пользователя для аутентификации';

comment on column users.password is
'Пароль пользователя в захэшированном виду';

comment on column users.refresh_token is
'Токен для обновления доступа пользователя к ресурсам';

/*==============================================================*/
/* Table: sections                                              */
/*==============================================================*/
create table sections (
   sec_id               BIGINT               not null,
   sec_name             VARCHAR(255)         not null,
   constraint sections_pk primary key (sec_id),
   constraint sections_uk1 unique (sec_name)
);

comment on column sections.sec_id is
'Уникальный номер секции';

comment on column sections.sec_name is
'Наименование секции';

/*==============================================================*/
/* Table: rings                                                 */
/*==============================================================*/
create table rings (
   ring_id              BIGINT               not null,
   ring_name            VARCHAR(255)         not null,
   constraint rings_pk primary key (ring_id),
   constraint rings_uk1 unique (ring_name)
);

comment on column rings.ring_id is
'Уникальный номер кольца';

comment on column rings.ring_name is
'Наименование кольца';

/*==============================================================*/
/* Table: statuses                                              */
/*==============================================================*/
create table statuses (
   stat_id              BIGINT               not null,
   stat_name            VARCHAR(255)         not null,
   constraint statuses_pk primary key (stat_id),
   constraint statuses_uk1 unique (stat_name)
);

comment on column statuses.stat_id is
'Уникальный номер статуса';

comment on column statuses.stat_name is
'Наименование ствтуса';

/*==============================================================*/
/* Table: technologies                                          */
/*==============================================================*/
create table technologies (
   tech_id              SERIAL               not null,
   cat_id               BIGINT               not null,
   sec_id               BIGINT               not null,
   ring_id              BIGINT               not null,
   name                 VARCHAR(255)         not null,
   description          VARCHAR(255)         not null,
   stat_id              BIGINT               not null,
   update_time          TIMESTAMP            not null,
   constraint technologies_pk primary key (tech_id),
   constraint technologies_categories_fk foreign key (cat_id)
      references categories (cat_id)
      on delete restrict on update restrict,
   constraint technologies_sections_fk foreign key (sec_id)
      references sections (sec_id)
      on delete restrict on update restrict,
   constraint technologies_rings_fk foreign key (ring_id)
      references rings (ring_id)
      on delete restrict on update restrict,
   constraint technologies_statuses_fk foreign key (stat_id)
      references statuses (stat_id)
      on delete restrict on update restrict
);

comment on column technologies.tech_id is
'Уникальный идентификатор технологии';

comment on column technologies.cat_id is
'Уникальный идентификатор категории';

comment on column technologies.sec_id is
'Уникальный идентификатор секции';

comment on column technologies.ring_id is
'Уникальный идентификатор кольца';

comment on column technologies.name is
'Наименование программного продукта';

comment on column technologies.description is
'Описаное программного продукта';

comment on column technologies.stat_id is
'Уникальный идентификатор статуса';

comment on column technologies.update_time is
'Время изменения ячейки ring_id';

/*==============================================================*/
/* Table: polls                                                 */
/*==============================================================*/
create table polls (
   poll_id              SERIAL               not null,
   user_id              BIGINT               not null,
   tech_id              BIGINT               not null,
   ring_id              BIGINT               not null,
   "time"               TIMESTAMP            not null,
   constraint polls_pk primary key (poll_id),
   constraint polls_users_fk foreign key (user_id)
      references users (user_id)
      on delete restrict on update restrict,
   constraint polls_technologies_fk foreign key (tech_id)
      references technologies (tech_id)
      on delete restrict on update restrict,
   constraint polls_rings_fk foreign key (ring_id)
      references rings (ring_id)
      on delete restrict on update restrict
);

comment on column polls.poll_id is
'Уникальный идентификатор опроса';

comment on column polls.user_id is
'Уникальный идентификатор пользователя';

comment on column polls.tech_id is
'Уникальный идентификатор технологии';

comment on column polls.ring_id is
'Уникальный идентификатор кольца';

comment on column polls."time" is
'Время проведения опроса';

/*==============================================================*/
/* Index: polls_users_i                                         */
/*==============================================================*/
create  index polls_users_i on polls (
user_id
);

/*==============================================================*/
/* Index: polls_technologies_i                                  */
/*==============================================================*/
create  index polls_technologies_i on polls (
tech_id
);

/*==============================================================*/
/* Index: polls_rings_i                                         */
/*==============================================================*/
create  index polls_rings_i on polls (
ring_id
);

/*==============================================================*/
/* Index: technologies_categories_i                             */
/*==============================================================*/
create  index technologies_categories_i on technologies (
cat_id
);

/*==============================================================*/
/* Index: technologies_sections_i                               */
/*==============================================================*/
create  index technologies_sections_i on technologies (
sec_id
);

/*==============================================================*/
/* Index: technologies_rings_i                                  */
/*==============================================================*/
create  index technologies_rings_i on technologies (
ring_id
);

/*==============================================================*/
/* Index: technologies_statuses_i                               */
/*==============================================================*/
create  index technologies_statuses_i on technologies (
stat_id
);

/*==============================================================*/
/* Index: users_roles_i                                         */
/*==============================================================*/
create  index users_roles_i on users (
role_id
);
