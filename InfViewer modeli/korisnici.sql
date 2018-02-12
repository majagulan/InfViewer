/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2005                    */
/* Created on:     12/19/2017 10:42:42 AM                       */
/*==============================================================*/


if exists (select 1
            from  sysobjects
           where  id = object_id('KORISNICI')
            and   type = 'U')
   drop table KORISNICI
go

/*==============================================================*/
/* Table: KORISNICI                                             */
/*==============================================================*/
create table KORISNICI (
   USERNAME             varchar(256)         not null,
   PASSWORD             varchar(256)         not null,
   constraint PK_KORISNICI primary key (USERNAME)
)
go

