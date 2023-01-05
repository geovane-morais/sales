CREATE DATABASE sales;
USE sales;

CREATE TABLE [dbo].[users](
	[id] [int] IDENTITY(0,1) NOT NULL,
	[name] [varchar](150) NULL,
	[is_admin] [bit] NULL,
	[email] [varchar](150) NULL,
	[phone] [varchar](15) NULL,
	[address] [varchar](max) NULL,
	[city] [varchar](100) NULL,
	[state] [varchar](2) NULL,
	[password] [varchar](max) NULL,
	[temp_password] [varchar](max) NULL,
 CONSTRAINT [pk_id_users] PRIMARY KEY ([id])
);

CREATE TABLE [dbo].[projects](
	[id] [int] IDENTITY(0,1) NOT NULL,
	[name] [varchar](100) NULL,
	[description] [varchar](max) NULL,
	[photo] [varchar](100) NULL,
	[gallerry] [varchar](max) NULL,
 CONSTRAINT [pk_id_projects] PRIMARY KEY ([id]) 
);

CREATE TABLE [dbo].[products](
	[id] [int] IDENTITY(0,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
	[price_per_meter] [float] NULL,
	[path_image] [varchar](250) NOT NULL,
	[is_available] [bit] NULL,
 CONSTRAINT [pk_id_produto] PRIMARY KEY ([id])  
);

CREATE TABLE [dbo].[logs](
	[id] [int] IDENTITY(0,1) NOT NULL,
	[id_user] [int] NOT NULL,
	[action] [varchar](max) NOT NULL,
 CONSTRAINT [pk_id_log] PRIMARY KEY ([id])  
);
ALTER TABLE [dbo].[logs]  WITH CHECK ADD  CONSTRAINT [fk_id_user] FOREIGN KEY([id]) REFERENCES [dbo].[users] ([id]);


USE [sales]
CREATE LOGIN sales_user
WITH PASSWORD='sales_password', CHECK_POLICY=OFF,CHECK_EXPIRATION=OFF;
CREATE USER Manager FOR LOGIN sales_user;
GRANT ALTER, CONTROL, CREATE SEQUENCE, DELETE, EXECUTE, INSERT, REFERENCES, SELECT, TAKE OWNERSHIP, UPDATE, VIEW CHANGE TRACKING, VIEW DEFINITION ON SCHEMA :: dbo  TO Manager ;
  
