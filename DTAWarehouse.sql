Create database dtaWarehouse
go
USE dtaWarehouse
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accounts](
	[code] [varchar](255) NOT NULL,
	[name] [nvarchar](255) NULL,
	[password] [nvarchar](255) NULL,
	[email] [nvarchar](255) NULL,
	[phone] [varchar](255) NULL,
	[isActive] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[allocate_order]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[allocate_order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[location_code] [nvarchar](255) NULL,
	[item_master_id] [int] NULL,
	[quantity] [float] NULL,
	[created_date] [datetime] NULL,
	[confirm] [bit] NULL,
 CONSTRAINT [PK__allocate__3213E83F8D02AA48] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customers]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customers](
	[CustomerID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](100) NULL,
	[Email] [varchar](100) NULL,
	[Phone] [varchar](20) NULL,
	[Fax] [varchar](20) NULL,
	[Address] [varchar](150) NULL,
	[Disable] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Groups]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Groups](
	[GroupID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[GroupID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[importorders]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[importorders](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[driver] [nvarchar](255) NULL,
	[drivers_phone] [nvarchar](255) NULL,
	[sup_id] [varchar](20) NULL,
	[date_import] [nvarchar](255) NULL,
	[note] [nvarchar](255) NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK__importor__3213E83FB3E364AB] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[issue_orders]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[issue_orders](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[issue_dated] [datetime] NULL,
	[issue_reason] [nvarchar](255) NULL,
	[submit_by] [nvarchar](255) NULL,
	[issue_active] [bit] NULL,
	[item_code] [nvarchar](255) NULL,
	[qty_export] [float] NULL,
	[qty_actual_export] [float] NULL,
	[itemmaster_id] [int] NOT NULL,
 CONSTRAINT [PK__issue_or__3213E83F93B8DAAC] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[itemdatas]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[itemdatas](
	[code] [varchar](50) NOT NULL,
	[name] [nvarchar](255) NULL,
	[color] [nvarchar](255) NULL,
	[type] [nvarchar](255) NULL,
	[active] [bit] NULL,
	[image] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[itemmasters]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[itemmasters](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[location_code] [nvarchar](255) NULL,
	[quantity] [float] NULL,
	[recieve_no] [nvarchar](255) NULL,
	[date_import] [datetime2](7) NULL,
	[note] [nvarchar](255) NULL,
	[qc_accept_quantity] [float] NULL,
	[qc_by] [nvarchar](255) NULL,
	[id_import] [int] NULL,
	[code_itemdata] [varchar](50) NULL,
	[sup_id] [varchar](20) NULL,
	[book_qty] [float] NULL,
	[disable] [bit] NULL,
	[pass] [bit] NULL,
	[qc_inject_quantity] [float] NULL,
 CONSTRAINT [PK__itemmast__3213E83F0433DDFF] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[locations]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[locations](
	[code] [varchar](50) NOT NULL,
	[name] [varchar](100) NULL,
	[warehouse_code] [nvarchar](50) NULL,
	[capacity] [float] NULL,
	[active] [bit] NULL,
	[remain] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[log]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[log](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[location_name] [varchar](250) NULL,
	[itemmaster_id] [int] NULL,
	[save_date] [datetime2](7) NULL,
	[quantity] [float] NULL,
	[method] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[Order_Code] [varchar](20) NOT NULL,
	[itemname] [varchar](200) NULL,
	[Description] [varchar](max) NULL,
	[GroupID] [int] NULL,
	[UnitID] [int] NULL,
	[CustomerID] [int] NULL,
	[CREATED_DATE] [datetime] NULL,
	[Amount] [float] NULL,
	[status] [varchar](50) NULL,
	[Disabled] [bit] NULL,
	[booked_qty] [float] NULL,
	[shipped_qty] [float] NULL,
 CONSTRAINT [PK__Orders__BA6778CC761389B7] PRIMARY KEY CLUSTERED 
(
	[Order_Code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[supplier]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[supplier](
	[sup_id] [varchar](20) NOT NULL,
	[sup_name] [varchar](max) NULL,
	[sup_address] [varchar](max) NULL,
	[sup_email] [varchar](max) NULL,
	[city] [varchar](50) NULL,
	[tax_code] [varchar](max) NULL,
	[active] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[sup_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tb_SYS_SEQUENCE]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tb_SYS_SEQUENCE](
	[SEQNAME] [varchar](50) NOT NULL,
	[SEQVALUE] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[SEQNAME] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Unit]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Unit](
	[UnitID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[UnitID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[warehouses]    Script Date: 9/18/2023 11:03:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[warehouses](
	[code] [nvarchar](50) NOT NULL,
	[name] [nvarchar](255) NULL,
	[active] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[itemmasters] ADD  CONSTRAINT [DF_itemmasters_disable]  DEFAULT ((0)) FOR [disable]
GO
ALTER TABLE [dbo].[itemmasters] ADD  CONSTRAINT [DF_itemmasters_pass]  DEFAULT ((1)) FOR [pass]
GO
ALTER TABLE [dbo].[itemmasters] ADD  CONSTRAINT [DF_itemmasters_qc_inject_quantity]  DEFAULT ((0)) FOR [qc_inject_quantity]
GO
ALTER TABLE [dbo].[importorders]  WITH CHECK ADD  CONSTRAINT [FK__importord__sup_i__3D5E1FD2] FOREIGN KEY([sup_id])
REFERENCES [dbo].[supplier] ([sup_id])
GO
ALTER TABLE [dbo].[importorders] CHECK CONSTRAINT [FK__importord__sup_i__3D5E1FD2]
GO
ALTER TABLE [dbo].[issue_orders]  WITH CHECK ADD  CONSTRAINT [FK__issue_ord__itemm__75A278F5] FOREIGN KEY([itemmaster_id])
REFERENCES [dbo].[itemmasters] ([id])
GO
ALTER TABLE [dbo].[issue_orders] CHECK CONSTRAINT [FK__issue_ord__itemm__75A278F5]
GO
ALTER TABLE [dbo].[itemmasters]  WITH CHECK ADD  CONSTRAINT [FK__itemmaste__code___33D4B598] FOREIGN KEY([code_itemdata])
REFERENCES [dbo].[itemdatas] ([code])
GO
ALTER TABLE [dbo].[itemmasters] CHECK CONSTRAINT [FK__itemmaste__code___33D4B598]
GO
ALTER TABLE [dbo].[itemmasters]  WITH CHECK ADD  CONSTRAINT [FK__itemmaste__id_im__34C8D9D1] FOREIGN KEY([id_import])
REFERENCES [dbo].[importorders] ([id])
GO
ALTER TABLE [dbo].[itemmasters] CHECK CONSTRAINT [FK__itemmaste__id_im__34C8D9D1]
GO
ALTER TABLE [dbo].[itemmasters]  WITH CHECK ADD  CONSTRAINT [FK__itemmaste__sup_i__3E52440B] FOREIGN KEY([sup_id])
REFERENCES [dbo].[supplier] ([sup_id])
GO
ALTER TABLE [dbo].[itemmasters] CHECK CONSTRAINT [FK__itemmaste__sup_i__3E52440B]
GO
ALTER TABLE [dbo].[locations]  WITH CHECK ADD FOREIGN KEY([warehouse_code])
REFERENCES [dbo].[warehouses] ([code])
GO
ALTER TABLE [dbo].[log]  WITH CHECK ADD  CONSTRAINT [FK__log__itemmaster___6E01572D] FOREIGN KEY([itemmaster_id])
REFERENCES [dbo].[itemmasters] ([id])
GO
ALTER TABLE [dbo].[log] CHECK CONSTRAINT [FK__log__itemmaster___6E01572D]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK__Orders__Customer__628FA481] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customers] ([CustomerID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK__Orders__Customer__628FA481]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK__Orders__GroupID__60A75C0F] FOREIGN KEY([GroupID])
REFERENCES [dbo].[Groups] ([GroupID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK__Orders__GroupID__60A75C0F]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK__Orders__UnitID__5FB337D6] FOREIGN KEY([UnitID])
REFERENCES [dbo].[Unit] ([UnitID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK__Orders__UnitID__5FB337D6]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK__Orders__UnitID__6383C8BA] FOREIGN KEY([UnitID])
REFERENCES [dbo].[Unit] ([UnitID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK__Orders__UnitID__6383C8BA]
GO
