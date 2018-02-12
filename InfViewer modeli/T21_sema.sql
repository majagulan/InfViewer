/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2005                    */
/* Created on:     1/16/2018 4:35:40 PM                         */
/*==============================================================*/


if exists (select 1
            from  sysobjects
           where  id = object_id('KATEGORIJA')
            and   type = 'U')
   drop table KATEGORIJA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POPRAVKA')
            and   type = 'U')
   drop table POPRAVKA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('RADNIK')
            and   type = 'U')
   drop table RADNIK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('VLASNIK')
            and   type = 'U')
   drop table VLASNIK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('VOZILO')
            and   type = 'U')
   drop table VOZILO
go

/*==============================================================*/
/* Table: KATEGORIJA                                            */
/*==============================================================*/
create table KATEGORIJA (
   IDKATEGORIJE         int                  not null,
   OZNAKAKATEGORIJE     varchar(254)         null,
   NAZIVKATEGORIJE      varchar(254)         null,
   OPIS                 varchar(254)         null,
   constraint PK_KATEGORIJA primary key nonclustered (IDKATEGORIJE)
)
go

/*==============================================================*/
/* Table: POPRAVKA                                              */
/*==============================================================*/
create table POPRAVKA (
   IDRADNIK             int                  not null,
   IDPOPRAVKE           int                  not null,
   IDVOZILO             int                  not null,
   VTO_OZNAKA           char(2)              not null,
   TPO_OZNAKA           varchar(4)           not null,
   PS_IDENT             int                  not null,
   DATUM                datetime             null,
   VREMEPOCETKAPOPRAVKE datetime             null,
   VREMEZAVRSETKAPOPRAVKE datetime             null,
   VRSTAPOPRAVKE        varchar(254)         null,
   CENAPOPRAVKE         int                  null,
   constraint PK_POPRAVKA primary key nonclustered (IDRADNIK, IDPOPRAVKE)
)
go

/*==============================================================*/
/* Table: RADNIK                                                */
/*==============================================================*/
create table RADNIK (
   IDRADNIK             int                  not null,
   IME                  varchar(254)         null,
   PREZIME              varchar(254)         null,
   ADRESA               varchar(254)         null,
   MESTO                varchar(254)         null,
   BROJTELEFONA         int                  null,
   constraint PK_RADNIK primary key nonclustered (IDRADNIK)
)
go

/*==============================================================*/
/* Table: VLASNIK                                               */
/*==============================================================*/
create table VLASNIK (
   IDVLASNIK            int                  not null,
   IME                  varchar(254)         null,
   PREZIME              varchar(254)         null,
   MESTO                varchar(254)         null,
   BROJTELEFONA         int                  null,
   constraint PK_VLASNIK primary key nonclustered (IDVLASNIK)
)
go

/*==============================================================*/
/* Table: VOZILO                                                */
/*==============================================================*/
create table VOZILO (
   IDVOZILO             int                  not null,
   IDKATEGORIJE         int                  not null,
   IDVLASNIK            int                  not null,
   MARKA                varchar(254)         null,
   MODEL                varchar(254)         null,
   GODINAPROIZVODNJE    int                  null,
   DRZAVAPROIZVODNJE    varchar(254)         null,
   GORIVO               varchar(254)         null,
   POGON                varchar(254)         null,
   KUBIKAZA             int                  null,
   constraint PK_VOZILO primary key nonclustered (IDVOZILO)
)
go

