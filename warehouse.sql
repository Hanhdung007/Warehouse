CREATE DATABASE Warehouse
go 
USE Warehouse

CREATE TABLE [itemdatas] (
  [code] varchar(50) PRIMARY KEY,
  [name] nvarchar(255),
  [color] nvarchar(255),
  [type] nvarchar(255),
  [active] bit,
  [image] nvarchar(255)
)
GO

CREATE TABLE [itemmasters] (
  [id] int PRIMARY KEY,
  [location_code] nvarchar(255),
  [quantity] float,
  [recieve_no] nvarchar(255),
  [date_import] datetime,
  [note] nvarchar(255),
  [qc_accept_quantity] float,
  [qc_by] nvarchar(255),
  [id_import] int, 
  [code_itemdata] varchar(50)
)
GO

CREATE TABLE [importorders] (
  [id] int PRIMARY KEY,
  [driver] nvarchar(255),
  [drivers_phone] nvarchar(255),
  [factory] nvarchar(255),
  [date_import] nvarchar(255),
  [note] nvarchar(255),
  [status] bit
)
GO

CREATE TABLE [warehouses] (
  [code] nvarchar(50) PRIMARY KEY,
  [name] nvarchar(255),
  [active] bit
)
GO

CREATE TABLE [locations] (
  [code] varchar(50) PRIMARY KEY,
  [name] varchar(100),
  [warehouse_code] nvarchar(50),
  [capacity] float,
  [active] bit
)
GO

CREATE TABLE [issue_orders] (
  [id] int PRIMARY KEY,
  [issue_dated] datetime,
  [issue_reason] nvarchar(255),
  [submitBy] nvarchar(255),
  [issue_active] bit
)
GO

CREATE TABLE [issue_order_details] (
  [id] int PRIMARY KEY,
  [itemCode] nvarchar(255),
  [id_itemmaster] int,
  [idissue_order] int,
  [quantityExport] int,
  [quantityActualexport] int
)
GO

ALTER TABLE [issue_order_details] ADD FOREIGN KEY ([idissue_order]) REFERENCES [issue_orders] ([id])
GO

ALTER TABLE [locations] ADD FOREIGN KEY ([warehouse_code]) REFERENCES [warehouses] ([code])
GO

ALTER TABLE [itemmasters]  ADD FOREIGN KEY ([code_itemdata]) REFERENCES [itemdatas] ([code])
GO

ALTER TABLE [itemmasters] ADD FOREIGN KEY ([id_import]) REFERENCES [importorders] ([id])
GO
CREATE TABLE [roles] (
	[id] int primary key identity,
	[role_name] varchar(100)
)
CREATE TABLE [accounts_roles](
	[id] int primary key identity,
	[role_id] int, 
	[account_code] varchar(255)
)
CREATE TABLE [Accounts] (
  [code] varchar(255) PRIMARY KEY,
  [name] nvarchar(255),
  [password] nvarchar(255),
)
GO
ALTER TABLE [accounts_roles] ADD FOREIGN KEY ([role_id]) REFERENCES  [roles] ([id])
GO
ALTER TABLE [accounts_roles] ADD FOREIGN KEY ([account_code]) REFERENCES  [Accounts] ([code])
GO