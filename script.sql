
CREATE DATABASE [SE1883_Stupid];

USE [SE1883_Stupid]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 9/25/2024 11:48:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[eid] [int] IDENTITY(1,1) NOT NULL,
	[ename] [nvarchar](150) NOT NULL,
	[gender] [bit] NOT NULL,
	[dob] [date] NOT NULL,
	[address] [varchar](150) NOT NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[eid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Employee] ON 

INSERT [dbo].[Employee] ([eid], [ename], [gender], [dob], [address]) VALUES (1, N'Mr Ấy', 1, CAST(N'2004-01-18' AS Date), N'Me Linh, Hanoi')
INSERT [dbo].[Employee] ([eid], [ename], [gender], [dob], [address]) VALUES (2, N'Ms XXX', 0, CAST(N'1998-12-30' AS Date), N'London')
INSERT [dbo].[Employee] ([eid], [ename], [gender], [dob], [address]) VALUES (3, N'XXX', 0, CAST(N'1975-12-30' AS Date), N'YYYYY')
SET IDENTITY_INSERT [dbo].[Employee] OFF
GO
