 --use master;
 --IF EXISTS (SELECT name FROM sys.databases WHERE name = 'Warehouse') 
 --THEN 
 --DROP DATABASE Warehouse
--END IF; 
Create DATABASE Warehouse
go 
USE Warehouse
GO

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
  [code_itemdata] varchar(50),
  sup_id varchar(20),
  bookQty float
)
GO
CREATE TABLE allocate_order (
  [id] int PRIMARY KEY,
  [location_code] nvarchar(255),
  ItemMasterId int,
  quantity float, 
  created_date datetime, 
  confirm bit
)
GO
CREATE TABLE [importorders] (
  [id] int PRIMARY KEY,
  [driver] nvarchar(255),
  [drivers_phone] nvarchar(255),
  sup_id varchar(20),
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
  remain float 
)
GO

CREATE TABLE [issue_orders] (
  [id] int PRIMARY KEY,
  [issue_dated] datetime,
  [issue_reason] nvarchar(255),
  [submitBy] nvarchar(255),
  [issue_active] bit, 
  item_code nvarchar(255),
  qtyExport float,
  qtyActualExport float,
  itemmaster_id int
)
GO
Drop table Customers(
	CustomerID int primary key identity,
	[Name] varchar(100),
	[Email] varchar(100),
	[Phone] varchar(20),
	[Fax] varchar(20),
	[Address] varchar(150),
	[Disable] bit
)
GO

Drop table Groups(
	GroupID int primary key identity,
	[Name] varchar(50)
)
GO
Drop table Unit(
	UnitID int primary key identity,
	[Name] varchar(50)
	
)
GO
Drop table Orders(
	Order_Code varchar(20) primary key,
	[Name] varchar(100),
	[Description] varchar(max),
	GroupID int,
	UnitID int,
	CustomerID int,
	CREATED_DATE datetime,
	Amount int,
	status varchar(50),
	[Disable] bit
)
GO
Create table tb_SYS_SEQUENCE(
SEQNAME varchar(50) primary key,
SEQVALUE int
)
Go
CREATE TABLE supplier (
	sup_id varchar(20) primary key,
	sup_name varchar(max),
	sup_address varchar(max),
	sup_email varchar(max),
	city varchar(50),
	tax_code varchar(max),
	active bit
)
go

CREATE TABLE [log] (
	id int identity primary key,
	locationName varchar(250),
	itemmaster_id int, 
	save_date datetime2(7),
	quantity float,
	method varchar(100)
)
go

ALTER TABLE [locations] ADD FOREIGN KEY ([warehouse_code]) REFERENCES [warehouses] ([code])
GO

ALTER TABLE [itemmasters]  ADD FOREIGN KEY ([code_itemdata]) REFERENCES [itemdatas] ([code])
GO

ALTER TABLE Orders ADD FOREIGN KEY (UnitID) REFERENCES Unit (UnitID)
GO
ALTER TABLE Orders ADD FOREIGN KEY (GroupID) REFERENCES Groups (GroupID)
GO
ALTER TABLE Orders ADD FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID)
GO
ALTER TABLE [log] ADD FOREIGN KEY (itemmaster_id) REFERENCES [itemmasters] ([id])
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
ALTER TABLE [importorders] ADD FOREIGN KEY (sup_id) REFERENCES  [supplier] (sup_id)
GO
ALTER TABLE [itemmasters] ADD FOREIGN KEY (sup_id) REFERENCES  [supplier] (sup_id)
GO
ALTER TABLE [issue_orders] ADD FOREIGN KEY (itemmaster_id) REFERENCES  [itemmasters] ([id])
GO