USE [master]
GO
/****** Object:  Database [Forum]    Script Date: 5/10/2022 8:36:52 PM ******/
CREATE DATABASE [Forum]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Forum', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Forum.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Forum_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Forum_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Forum] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Forum].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Forum] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Forum] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Forum] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Forum] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Forum] SET ARITHABORT OFF 
GO
ALTER DATABASE [Forum] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Forum] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Forum] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Forum] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Forum] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Forum] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Forum] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Forum] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Forum] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Forum] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Forum] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Forum] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Forum] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Forum] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Forum] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Forum] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Forum] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Forum] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Forum] SET  MULTI_USER 
GO
ALTER DATABASE [Forum] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Forum] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Forum] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Forum] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Forum] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Forum] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Forum] SET QUERY_STORE = OFF
GO
USE [Forum]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 5/10/2022 8:36:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[parentId] [bigint] NULL,
	[title] [nvarchar](255) NOT NULL,
	[slug] [nvarchar](255) NOT NULL,
	[content] [nvarchar](max) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Chat]    Script Date: 5/10/2022 8:36:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Chat](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[fromId] [bigint] NOT NULL,
	[toId] [bigint] NOT NULL,
	[message] [ntext] NOT NULL,
 CONSTRAINT [PK_Chat] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CommentLike]    Script Date: 5/10/2022 8:36:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CommentLike](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[authorId] [bigint] NOT NULL,
	[commentId] [bigint] NOT NULL,
	[isLike] [tinyint] NOT NULL,
 CONSTRAINT [PK_CommentLike] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Config]    Script Date: 5/10/2022 8:36:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Config](
	[allowLogin] [bit] NOT NULL,
	[allowRegistry] [bit] NOT NULL,
	[allowPost] [bit] NOT NULL,
	[allowChat] [bit] NOT NULL,
	[Forum] [bit] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Post]    Script Date: 5/10/2022 8:36:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Post](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[authorId] [bigint] NOT NULL,
	[parentId] [bigint] NULL,
	[title] [nvarchar](255) NOT NULL,
	[slug] [nvarchar](255) NOT NULL,
	[summary] [nvarchar](max) NULL,
	[solved] [bit] NOT NULL,
	[createdAt] [datetime] NOT NULL,
	[updatedAt] [datetime] NULL,
	[content] [nvarchar](max) NULL,
	[slView] [bigint] NULL,
	[type] [bit] NOT NULL,
	[show] [bit] NOT NULL,
 CONSTRAINT [PK_Post] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Post_Category]    Script Date: 5/10/2022 8:36:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Post_Category](
	[postId] [bigint] NOT NULL,
	[categoryId] [bigint] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Post_Comment]    Script Date: 5/10/2022 8:36:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Post_Comment](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[postId] [bigint] NOT NULL,
	[parentId] [bigint] NULL,
	[authorId] [bigint] NOT NULL,
	[createdAt] [datetime] NOT NULL,
	[updateAt] [datetime] NULL,
	[content] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Post_Comment] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Post_Report]    Script Date: 5/10/2022 8:36:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Post_Report](
	[postId] [bigint] NOT NULL,
	[content] [nvarchar](max) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PostLike]    Script Date: 5/10/2022 8:36:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PostLike](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[authorId] [bigint] NOT NULL,
	[postId] [bigint] NOT NULL,
	[isLike] [tinyint] NOT NULL,
 CONSTRAINT [PK_Like] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 5/10/2022 8:36:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[firstName] [nvarchar](100) NULL,
	[middleName] [nvarchar](100) NULL,
	[lastName] [nvarchar](100) NULL,
	[mobile] [nvarchar](15) NULL,
	[email] [nvarchar](255) NOT NULL,
	[passwordHash] [nvarchar](max) NOT NULL,
	[registeredAt] [datetime] NOT NULL,
	[lastLogin] [datetime] NULL,
	[intro] [nvarchar](max) NULL,
	[profile] [nvarchar](max) NULL,
	[image] [nvarchar](max) NULL,
	[type] [tinyint] NOT NULL,
	[uniqueId] [bigint] NULL,
	[status] [nvarchar](255) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([id], [parentId], [title], [slug], [content]) VALUES (1, NULL, N'Web Developer', N'web-developer', N'Nhà phát triển web là một lập trình viên chuyên môn hóa hoặc đặc biệt tham gia vào việc phát triển các ứng dụng World Wide Web bằng cách sử dụng mô hình client server. Các ứng dụng này thường sử dụng HTML, CSS và JavaScript trong máy khách, PHP, ASP.NET (C #) hoặc Java trong máy chủ và http để liên lạc giữa máy khách và máy chủ. Một hệ thống quản lý nội dung web thường được sử dụng để phát triển và duy trì các ứng dụng web.')
INSERT [dbo].[Category] ([id], [parentId], [title], [slug], [content]) VALUES (3, NULL, N'Android Developer', N'android-developer', N'Phát triển phần mềm Android là quy trình tạo ra các ứng dụng cho hệ điều hành Android. Các ứng dụng chủ yếu được phát triển bằng ngôn ngữ lập trình Java, sử dụng bộ phát triển phần mềm Android, tuy vậy các môi trường phát triển khác cũng có thể được sử dụng. Wikipedia')
INSERT [dbo].[Category] ([id], [parentId], [title], [slug], [content]) VALUES (4, NULL, N'Graphic Developer', N'graphic-developer', N'Thiết kế đồ họa là cụm từ để chỉ một chuyên ngành thuộc về mỹ thuật. Trong đó danh từ "đồ họa" để chỉ những bản vẽ được hiển thị trên một mặt phẳng (đa chất liệu), và động từ "thiết kế" bao hàm ý nghĩa kiến thiết, sáng tạo. Từ đó có thể hiểu, "thiết kế đồ họa" là kiến tạo một hình ảnh, một tác phẩm lên một bề mặt chất liệu nào đó, mang ý nghĩa nghệ thuật nhằm mục đích trang trí, làm đẹp, phục vụ nhu cầu con người')
INSERT [dbo].[Category] ([id], [parentId], [title], [slug], [content]) VALUES (16, NULL, N'Graphic AI', N'graphic-ai', N'Graphic AI')
INSERT [dbo].[Category] ([id], [parentId], [title], [slug], [content]) VALUES (17, NULL, N'Lập trình Java', N'lập-trình-java', N'Java là ngôn ngữ lập trình tĩnh, hướng đối tượng, hoạt động trên nhiều nền tảng. JavaScript là ngôn ngữ lập trình động (hay ngôn ngữ kịch bản – scripted language) được sử dụng để làm cho các trang web và ứng dụng trở nên sinh động. Java dựa trên lớp (class), còn JavaScript thì động. Java là một ngôn ngữ độc lập.')
INSERT [dbo].[Category] ([id], [parentId], [title], [slug], [content]) VALUES (18, NULL, N'Lập trình C#', N'lập-trình-c#', N'C# là một ngôn ngữ lập trình được xây dựng riêng cho .NET Framework – nền tảng phát triển ứng dụng chủ đạo hiện nay của Microsoft. Hiện nay, nhu cầu tuyển dụng và đào tạo nhân lực ')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Chat] ON 

INSERT [dbo].[Chat] ([id], [fromId], [toId], [message]) VALUES (44, 552931, 347541, N'hello mạnh')
INSERT [dbo].[Chat] ([id], [fromId], [toId], [message]) VALUES (45, 347541, 552931, N'hello bin')
SET IDENTITY_INSERT [dbo].[Chat] OFF
GO
SET IDENTITY_INSERT [dbo].[CommentLike] ON 

INSERT [dbo].[CommentLike] ([id], [authorId], [commentId], [isLike]) VALUES (12, 7, 92, 2)
INSERT [dbo].[CommentLike] ([id], [authorId], [commentId], [isLike]) VALUES (14, 7, 90, 2)
INSERT [dbo].[CommentLike] ([id], [authorId], [commentId], [isLike]) VALUES (15, 7, 85, 2)
INSERT [dbo].[CommentLike] ([id], [authorId], [commentId], [isLike]) VALUES (16, 7, 89, 2)
SET IDENTITY_INSERT [dbo].[CommentLike] OFF
GO
INSERT [dbo].[Config] ([allowLogin], [allowRegistry], [allowPost], [allowChat], [Forum]) VALUES (1, 1, 1, 1, 1)
GO
SET IDENTITY_INSERT [dbo].[Post] ON 

INSERT [dbo].[Post] ([id], [authorId], [parentId], [title], [slug], [summary], [solved], [createdAt], [updatedAt], [content], [slView], [type], [show]) VALUES (3, 6, NULL, N'How Did You Hear About This Position', N'how-did-you-hear-about-this-position', N'Wouldn’t it be great if you knew exactly what questions a hiring manager would be asking you in your next job interview? We can’t read minds, unfortunately, but we’ll give you the next best thing: a list of more than 40 of the most commonly asked interview questions, along with advice for answering them all', 0, CAST(N'2021-12-01T00:00:00.000' AS DateTime), NULL, N'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi adipiscing gravida odio, sit amet suscipit risus ultrices eu. Fusce viverra neque at purus laoreet consequat. Vivamus vulputate posuere nisl quis consequat. Donec congue commodo mi, sed commodo velit fringilla ac. Fusce placerat venenatis mi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras ornare, dolor a aliquet rutrum, dolor turpis condimentum leo, a semper lacus purus in felis. Quisque blandit posuere turpis, eget auctor felis pharetra eu .

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi adipiscing gravida odio, sit amet suscipit risus ultrices eu. Fusce viverra neque at purus laoreet consequat. Vivamus vulputate posuere nisl quis consequat. Donec congue commodo mi, sed commodo velit fringilla ac. Fusce placerat venenatis mi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras ornare, dolor a aliquet rutrum, dolor turpis condimentum leo, a semper lacus purus in felis. Quisque blandit posuere turpis, eget auctor felis pharetra eu .', 255, 0, 1)
INSERT [dbo].[Post] ([id], [authorId], [parentId], [title], [slug], [summary], [solved], [createdAt], [updatedAt], [content], [slView], [type], [show]) VALUES (6, 6, NULL, N'Why Do You Want to Work at This Company', N'why-do-you-want-to-work-at-this-company', N'Duis dapibus aliquam mi, eget euismod sem scelerisque ut. Vivamus at elit quis urna adipiscing iaculis.Duis dapibus aliquam mi, eget euismod sem scelerisque ut. Vivamus at elit quis urna adipiscing iaculis.', 0, CAST(N'2021-11-29T00:00:00.000' AS DateTime), NULL, N'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi adipiscing gravida odio, sit amet suscipit risus ultrices eu. Fusce viverra neque at purus laoreet consequat. Vivamus vulputate posuere nisl quis consequat. Donec congue commodo mi, sed commodo velit fringilla ac. Fusce placerat venenatis mi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras ornare, dolor a aliquet rutrum, dolor turpis condimentum leo, a semper lacus purus in felis. Quisque blandit posuere turpis, eget auctor felis pharetra eu .

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi adipiscing gravida odio, sit amet suscipit risus ultrices eu. Fusce viverra neque at purus laoreet consequat. Vivamus vulputate posuere nisl quis consequat. Donec congue commodo mi, sed commodo velit fringilla ac. Fusce placerat venenatis mi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras ornare, dolor a aliquet rutrum, dolor turpis condimentum leo, a semper lacus purus in felis. Quisque blandit posuere turpis, eget auctor felis pharetra eu .', 149, 0, 1)
INSERT [dbo].[Post] ([id], [authorId], [parentId], [title], [slug], [summary], [solved], [createdAt], [updatedAt], [content], [slView], [type], [show]) VALUES (28, 7, NULL, N'Android Hệ Điều Hành', N'android-he-dieu-hanh', N'Android là hệ điều hành', 0, CAST(N'2021-12-16T00:16:20.443' AS DateTime), CAST(N'2021-12-16T21:02:13.297' AS DateTime), N'<p>Android là một hệ điều hành dựa trên nền tảng Linux được thiết kế dành cho các thiết bị di động có màn hình cảm ứng như điện thoại thông minh và máy tính bảng. Ban đầu, Android được phát triển bởi Android, Inc. với sự hỗ trợ tài chính từ Google và sau này được chính Google mua lại vào năm 2005.&nbsp;<a href="https://vi.wikipedia.org/wiki/Android_(h%E1%BB%87_%C4%91i%E1%BB%81u_h%C3%A0nh)">Wikipedia</a></p>

<p><a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=android+ph%C6%B0%C6%A1ng+ph%C3%A1p+c%E1%BA%ADp+nh%E1%BA%ADt&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkv11LNKLfST87PyUlNLsnMz9Mvzk8rKU8sSrUqLUhJLElVyE0tychPWcSqnJiXUpSfmaJQkHFsw7GFeelAxuGFBQrJD3etLVDIywBSJQAwWvFZWwAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQ6BMoAHoECCAQAg">Phương pháp cập nhật</a>:&nbsp;<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=L%E1%BA%ADp+tr%C3%ACnh+qua+v%C3%B4+tuy%E1%BA%BFn&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvV-IEsY1TCnJMtFQzyq30k_NzclKTSzLz8_SL89NKyhOLUq1KC1ISS1IVclNLMvJTFrHK-DzctbZAoaTo8Jq8DIXC0kSFssNbFEpKKx_u2p-3g5URAAvIAxxiAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoAXoECCAQAw">Lập trình qua vô tuyến</a></p>

<p><a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=android+giao+di%E1%BB%87n+ng%C6%B0%E1%BB%9Di+d%C3%B9ng+m%E1%BA%B7c+%C4%91%E1%BB%8Bnh&amp;stick=H4sIAAAAAAAAAAFxAI7_CA4SCi9tLzAyd3h0Z3cqLmh3Oi9jb2xsZWN0aW9uL3NvZnR3YXJlOmRlZmF1bHQgdXNlciBpbnRlcmZhY2WiBTBhbmRyb2lkIGdpYW8gZGnhu4duIG5nxrDhu51pIGTDuW5nIG3hurdjIMSR4buLbmigFBA1cQAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQ6BMoAHoECCEQAg">Giao diện người dùng mặc định</a>:&nbsp;<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=Giao+di%E1%BB%87n+%C4%91%E1%BB%93+h%E1%BB%8Da+ng%C6%B0%E1%BB%9Di+d%C3%B9ng&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvV-IAsY3NTYu19DLKrfST83NyUpNLMvPz9Ivz00rKE4tSrVJS0xJLc0oUSotTixQy80pSi9ISk1MXsaq5ZybmK6RkPtzdnqdwZOLD3ZMVMh7u7k1UyEs_tuHh7rmZCimHd-al72BlBABxxNxSdAAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoAXoECCEQAw">Giao diện đồ họa người dùng</a>&nbsp;(<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=C%E1%BA%A3m+%E1%BB%A9ng+%C4%91a+%C4%91i%E1%BB%83m&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvV4KwzXOTKo219DLKrfST83NyUpNLMvPz9Ivz00rKE4tSrVJS0xJLc0oUSotTixQy80pSi9ISk1MXsYo7P9y1OFfh4e6VeekKRyYmAnHmw93NuTtYGQFvm7WIZwAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoAnoECCEQBA">Cảm ứng đa điểm</a>)</p>

<p><a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=android+nh%C3%B3m+h%E1%BB%87+%C4%91i%E1%BB%81u+h%C3%A0nh&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkv19LNKLfST87PyUlNLsnMz9Mvy0xJzY9PT8xNjS_ISSxJyy_KLbbKL1ZIS8zNzKlcxKqQmJdSlJ-ZopCXcXhzrkLGw93tCkcmZj7c3ViqkHF4QV4GADsJHcZgAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQ6BMoAHoECCIQAg">Nhóm hệ điều hành</a>:&nbsp;<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=T%C6%B0%C6%A1ng+t%E1%BB%B1+Unix&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvV-IAsc1L8gq0dDPKrfST83NyUpNLMvPz9MsyU1Lz49MTc1PjC3ISS9Lyi3KLrfKLFdISczNzKhexCoYc23BsYV66QsnD3RsVQvMyK3awMgIAiCS5UF4AAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoAXoECCIQAw">Tương tự Unix</a></p>

<p><a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=android+phi%C3%AAn+b%E1%BA%A3n+m%E1%BB%9Bi+nh%E1%BA%A5t&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQ6BMoAHoECC0QAg">Phiên bản mới nhất</a>:&nbsp;Android 12 / 18 tháng 2 năm 2021; 9 tháng trước</p>

<p><a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=android+ng%C3%A0y+ph%C3%A1t+h%C3%A0nh+%C4%91%E1%BA%A7u+ti%C3%AAn&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkv11LOKLfST87PyUlNLsnMz9Mvzk8rKU8sSrVKLChITSxSSEksSV3EqpaYl1KUn5mikJd-eEGlQkHG4YUlChmHF-RlKByZ-HDX8lKFkszDq_IAGkX7flwAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQ6BMoAHoECC8QAg">Ngày phát hành đầu tiên</a>:&nbsp;23 tháng 9, 2008</p>

<p><a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=android+ph%C3%A1t+h%C3%A0nh+l%E1%BA%A7n+%C4%91%E1%BA%A7u&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQ6BMoAHoECDIQAg">Phát hành lần đầu</a>:&nbsp;23 tháng 9 năm 2008; 13 năm trước</p>

<p><a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=android+vi%E1%BA%BFt+b%E1%BA%B1ng&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkv11LKKLfST87PyUlNLsnMz9Mvzk8rKU8sSrUqL8osKUnNU8jMW8QqmpiXUpSfmaJQlvlw1_4ShaSHuzbmpQMA5AmS0koAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQ6BMoAHoECCwQAg">Viết bằng</a>:&nbsp;<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=Java&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvVwKzzYuTstOStJQyyq30k_NzclKTSzLz8_SL89NKyhOLUq3KizJLSlLzFDLzFrGyeCWWJe5gZQQA_TbIA0gAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoAXoECCwQAw">Java</a>,&nbsp;<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=Ng%C3%B4n+ng%E1%BB%AF+C&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvV-IAsQ1LzJK0lDLKrfST83NyUpNLMvPz9Ivz00rKE4tSrcqLMktKUvMUMvMWsfL6pR_ekqeQl_5w93oF5x2sjACZG_-1TwAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoAnoECCwQBA">C</a>,&nbsp;<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=XML&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvV-IAsS3MTUy1lDLKrfST83NyUpNLMvPz9Ivz00rKE4tSrcqLMktKUvMUMvMWsTJH-PrsYGUEANROgh9FAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoA3oECCwQBQ">XML</a>,&nbsp;<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=H%E1%BB%A3p+ng%E1%BB%AF&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvV2IHsQss0rWUMsqt9JPzc3JSk0sy8_P0i_PTSsoTi1KtyosyS0pS8xQy8xaxcns83L24QCEv_eHu9TtYGQFfYkk9TAAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoBHoECCwQBg">Hợp ngữ</a>,&nbsp;<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=Python&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvV-IAsU2rDOO1lDLKrfST83NyUpNLMvPz9Ivz00rKE4tSrcqLMktKUvMUMvMWsbIFVJZk5OftYGUEAJgzL81IAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoBXoECCwQBw">Python</a>,&nbsp;<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=C%C3%A2u+l%E1%BB%87nh+shell&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvV-IAsc1Nk7K1lDLKrfST83NyUpNLMvPz9Ivz00rKE4tSrcqLMktKUvMUMvMWsQo6H15UqpDzcHd7XoZCcUZqTs4OVkYALmaQiVMAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoBnoECCwQCA">Câu lệnh shell</a>,&nbsp;<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=Go+(ng%C3%B4n+ng%E1%BB%AF+l%E1%BA%ADp+tr%C3%ACnh)&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvVwKzLdOTKrKKtJQyyq30k_NzclKTSzLz8_SL89NKyhOLUq3KizJLSlLzFDLzFrHKuucraOSlH96Sp5CX_nD3eoWch7vWFiiUFB1ek5ehuYOVEQCn9-GUYQAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoB3oECCwQCQ">Go</a>,&nbsp;<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=Make&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvV-IEsQ3Ny4tNtZQyyq30k_NzclKTSzLz8_SL89NKyhOLUq3KizJLSlLzFDLzFrGy-CZmp-5gZQQAmeYPb0cAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoCHoECCwQCg">Make</a>,&nbsp;<a href="https://www.google.com/search?sxsrf=AOaemvINyIwk2fmyRelteMzBCbA4JQ1YqQ:1639663323639&amp;q=D+(ng%C3%B4n+ng%E1%BB%AF+l%E1%BA%ADp+tr%C3%ACnh)&amp;stick=H4sIAAAAAAAAAOPgE-LSz9U3MCqvKEkvV-IEsQ2zk0rMtZQyyq30k_NzclKTSzLz8_SL89NKyhOLUq3KizJLSlLzFDLzFrHKuCho5KUf3pKnkJf-cPd6hZyHu9YWKJQUHV6Tl6G5g5URACaP6NxfAAAA&amp;sa=X&amp;ved=2ahUKEwjOmtPvvej0AhVYTWwGHZfIDicQmxMoCXoECCwQCw">D</a></p>
', 13, 1, 1)
INSERT [dbo].[Post] ([id], [authorId], [parentId], [title], [slug], [summary], [solved], [createdAt], [updatedAt], [content], [slView], [type], [show]) VALUES (44, 6, NULL, N'Tải video yotube', N'tai-video-yotube', N'Cách tải video youtube bằng tool python', 0, CAST(N'2022-03-02T18:02:03.837' AS DateTime), CAST(N'2022-03-07T14:50:57.883' AS DateTime), N'<p>Link tác giả:&nbsp;https://www.facebook.com/BVM672/</p>

<p>Link tải tool:&nbsp;https://github.com/BachBin/DownloadYotube.git</p>
', 14, 1, 1)
INSERT [dbo].[Post] ([id], [authorId], [parentId], [title], [slug], [summary], [solved], [createdAt], [updatedAt], [content], [slView], [type], [show]) VALUES (46, 7, NULL, N'Lập trình Java', N'lap-trinh-java', N'Ngôn ngữ lập trình Java là gì?', 0, CAST(N'2022-03-04T17:48:49.523' AS DateTime), CAST(N'2022-03-04T17:53:31.870' AS DateTime), N'<h2><strong>Ngôn ngữ lập trình Java là gì?</strong></h2>

<p>Java là một ngôn ngữ lập trình hướng đối tượng (OOP) và dựa trên các lớp (class), ban đầu được phát triển bởi Sun Microsystems do James Gosling khởi xướng và phát hành vào năm 1995. Khác với phần lớn ngôn ngữ lập trình thông thường, thay vì biên dịch mã nguồn thành mã máy hoặc thông dịch mã nguồn khi chạy, Java được thiết kế để biên dịch mã nguồn thành bytecode, bytecode sau đó sẽ được môi trường thực thi (runtime environment) chạy.</p>

<h3><strong>Ngôn ngữ lập trình Javascript và Java</strong></h3>

<p><strong><a href="https://magenest.com/wp-content/uploads/2021/10/ngon-ngu-lap-trinh-java-0.jpg"><img alt="" src="https://magenest.com/wp-content/uploads/2021/10/ngon-ngu-lap-trinh-java-0.jpg" style="height:281px; width:500px" /></a></strong></p>
', 212, 1, 1)
INSERT [dbo].[Post] ([id], [authorId], [parentId], [title], [slug], [summary], [solved], [createdAt], [updatedAt], [content], [slView], [type], [show]) VALUES (48, 7, NULL, N'Responsive Web Design là công nghệ tiên tiến nhất hiện nay?', N'responsive-web-design-la-cong-nghe-tien-tien-nhat-hien-nay?', N'Responsive Web Design là công nghệ tiên tiến nhất hiện nay?', 1, CAST(N'2022-03-07T14:57:41.300' AS DateTime), NULL, N'<p>Chào các bác. Em tìm hiểu trên mạng thì người ta giải thích như thế này: "Responsive Web là một bản web duy nhất có thể chạy được trên tất cả các thiết bị như máy tính cá nhân, smartphone, hoặc tablet. Responsive Web có khả năng tự động thay đổi kích thước nội dung và hình ảnh cho nhiều loại kích thước màn hình khác nhau để chắc chắn rằng website đó được truy cập hiệu quả và dễ dàng trên bất kỳ thiết bị nào."<br />
Có 2 điều em thắc mắc:<br />
1. Thực sự có thể "tự động" thay đổi kích thước nội dung và hình ảnh được không hay chỉ đơn giản là zoom ra zoom vào website?<br />
2. Nếu với kích thước màn hình quá bé như điện thoại di động thì nó sẽ hiển thị như thế nào?<br />
Bác nào biết về công nghệ này giải thích giúp em ạ.<br />
Em cảm ơn.</p>
', 177, 0, 1)
INSERT [dbo].[Post] ([id], [authorId], [parentId], [title], [slug], [summary], [solved], [createdAt], [updatedAt], [content], [slView], [type], [show]) VALUES (52, 7, NULL, N'Học C Sharp sao cho hiệu quả', N'hoc-c-sharp-sao-cho-hieu-qua', N'Học C Sharp sao cho hiệu quả', 0, CAST(N'2022-03-07T15:06:49.610' AS DateTime), NULL, N'<p>Học C Sharp sao cho hiệu quả????</p>
', 8, 0, 1)
INSERT [dbo].[Post] ([id], [authorId], [parentId], [title], [slug], [summary], [solved], [createdAt], [updatedAt], [content], [slView], [type], [show]) VALUES (56, 7, NULL, N'Hướng dẫn tự học lập trình C Sharp toàn tập', N'huong-dan-tu-hoc-lap-trinh-c-sharp-toan-tap', N'Hướng dẫn tự học lập trình C# toàn tập', 0, CAST(N'2022-03-07T15:38:49.397' AS DateTime), CAST(N'2022-03-07T15:41:41.517' AS DateTime), N'<p><iframe align="middle" frameborder="1" height="700" scrolling="yes" src="https://tuhocict.com/huong-dan-tu-hoc-lap-trinh-c-sharp/" tabindex="-1" width="700"></iframe></p>
', 12, 1, 1)
INSERT [dbo].[Post] ([id], [authorId], [parentId], [title], [slug], [summary], [solved], [createdAt], [updatedAt], [content], [slView], [type], [show]) VALUES (57, 7, NULL, N'Học REACTJS như thế nào', N'hoc-reactjs-nhu-the-nao', N'Học REACTJS như thế nào???', 1, CAST(N'2022-03-07T15:39:58.197' AS DateTime), NULL, N'<p>Học REACTJS như thế nào???</p>
', 134, 0, 1)
INSERT [dbo].[Post] ([id], [authorId], [parentId], [title], [slug], [summary], [solved], [createdAt], [updatedAt], [content], [slView], [type], [show]) VALUES (58, 7, NULL, N'Cách cài đặt ReactJS', N'cach-cai-dat-reactjs', N'Cách cài đặt ReactJS', 0, CAST(N'2022-03-07T15:40:41.253' AS DateTime), CAST(N'2022-03-07T15:41:09.693' AS DateTime), N'<p>Cách cài đặt ReactJS như thế nào ???</p>
', 269, 0, 1)
INSERT [dbo].[Post] ([id], [authorId], [parentId], [title], [slug], [summary], [solved], [createdAt], [updatedAt], [content], [slView], [type], [show]) VALUES (59, 7, NULL, N'Căn giữa đối tượng trong CSS', N'căn-giữa-đối-tượng-trong-css', N'Căn giữa đối tượng trong CSS', 1, CAST(N'2022-03-11T22:37:17.707' AS DateTime), CAST(N'2022-05-10T19:46:26.867' AS DateTime), N'<p style="text-align:center"><strong>Căn giữa đối tượng trong CSS</strong></p>

<p style="text-align:center"><strong>align: center;</strong></p>

<p style="text-align:center"><strong>text-align: center;</strong></p>

<p style="text-align:center"><strong>justify-content: center;</strong></p>

<div class="ddict_btn" style="left:765.6px; top:30px"><img src="chrome-extension://bpggmmljdiliancllaapiggllnkbjocb/logo/48.png" /></div>
', 43, 1, 1)
SET IDENTITY_INSERT [dbo].[Post] OFF
GO
INSERT [dbo].[Post_Category] ([postId], [categoryId]) VALUES (3, 1)
INSERT [dbo].[Post_Category] ([postId], [categoryId]) VALUES (6, 1)
INSERT [dbo].[Post_Category] ([postId], [categoryId]) VALUES (48, 1)
INSERT [dbo].[Post_Category] ([postId], [categoryId]) VALUES (28, 3)
INSERT [dbo].[Post_Category] ([postId], [categoryId]) VALUES (59, 1)
INSERT [dbo].[Post_Category] ([postId], [categoryId]) VALUES (56, 18)
INSERT [dbo].[Post_Category] ([postId], [categoryId]) VALUES (52, 18)
INSERT [dbo].[Post_Category] ([postId], [categoryId]) VALUES (58, 1)
INSERT [dbo].[Post_Category] ([postId], [categoryId]) VALUES (44, 1)
INSERT [dbo].[Post_Category] ([postId], [categoryId]) VALUES (46, 17)
INSERT [dbo].[Post_Category] ([postId], [categoryId]) VALUES (57, 1)
GO
SET IDENTITY_INSERT [dbo].[Post_Comment] ON 

INSERT [dbo].[Post_Comment] ([id], [postId], [parentId], [authorId], [createdAt], [updateAt], [content]) VALUES (82, 58, NULL, 7, CAST(N'2022-03-12T14:51:43.870' AS DateTime), NULL, N'npx create-react-app tenapp ')
INSERT [dbo].[Post_Comment] ([id], [postId], [parentId], [authorId], [createdAt], [updateAt], [content]) VALUES (85, 48, NULL, 7, CAST(N'2022-03-12T16:48:16.887' AS DateTime), NULL, N'1. zoom ra zoom vào website')
INSERT [dbo].[Post_Comment] ([id], [postId], [parentId], [authorId], [createdAt], [updateAt], [content]) VALUES (89, 48, NULL, 7, CAST(N'2022-03-12T18:15:07.307' AS DateTime), CAST(N'2022-03-12T18:28:17.447' AS DateTime), N'test update
')
INSERT [dbo].[Post_Comment] ([id], [postId], [parentId], [authorId], [createdAt], [updateAt], [content]) VALUES (90, 57, NULL, 7, CAST(N'2022-03-14T11:57:13.880' AS DateTime), NULL, N'https://www.w3schools.com/REACT/DEFAULT.ASP')
INSERT [dbo].[Post_Comment] ([id], [postId], [parentId], [authorId], [createdAt], [updateAt], [content]) VALUES (91, 6, NULL, 7, CAST(N'2022-03-14T11:57:58.930' AS DateTime), NULL, N'spam')
INSERT [dbo].[Post_Comment] ([id], [postId], [parentId], [authorId], [createdAt], [updateAt], [content]) VALUES (92, 59, NULL, 7, CAST(N'2022-03-14T11:58:34.453' AS DateTime), NULL, N'hữu ích')
INSERT [dbo].[Post_Comment] ([id], [postId], [parentId], [authorId], [createdAt], [updateAt], [content]) VALUES (94, 56, NULL, 7, CAST(N'2022-03-14T12:47:37.113' AS DateTime), NULL, N'hữu ích')
INSERT [dbo].[Post_Comment] ([id], [postId], [parentId], [authorId], [createdAt], [updateAt], [content]) VALUES (95, 3, NULL, 7, CAST(N'2022-03-14T12:51:22.787' AS DateTime), NULL, N'spam eng')
INSERT [dbo].[Post_Comment] ([id], [postId], [parentId], [authorId], [createdAt], [updateAt], [content]) VALUES (100, 52, NULL, 7, CAST(N'2022-04-02T19:15:36.420' AS DateTime), NULL, N'ok bạn')
SET IDENTITY_INSERT [dbo].[Post_Comment] OFF
GO
INSERT [dbo].[Post_Report] ([postId], [content]) VALUES (3, N'a')
INSERT [dbo].[Post_Report] ([postId], [content]) VALUES (57, N'report vì bài đăng là spam')
INSERT [dbo].[Post_Report] ([postId], [content]) VALUES (28, N'hay bin')
GO
SET IDENTITY_INSERT [dbo].[PostLike] ON 

INSERT [dbo].[PostLike] ([id], [authorId], [postId], [isLike]) VALUES (1, 7, 56, 2)
INSERT [dbo].[PostLike] ([id], [authorId], [postId], [isLike]) VALUES (2, 7, 46, 2)
INSERT [dbo].[PostLike] ([id], [authorId], [postId], [isLike]) VALUES (3, 7, 44, 2)
INSERT [dbo].[PostLike] ([id], [authorId], [postId], [isLike]) VALUES (8, 6, 58, 2)
INSERT [dbo].[PostLike] ([id], [authorId], [postId], [isLike]) VALUES (10, 7, 52, 1)
INSERT [dbo].[PostLike] ([id], [authorId], [postId], [isLike]) VALUES (12, 7, 48, 2)
INSERT [dbo].[PostLike] ([id], [authorId], [postId], [isLike]) VALUES (13, 7, 58, 2)
SET IDENTITY_INSERT [dbo].[PostLike] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([id], [firstName], [middleName], [lastName], [mobile], [email], [passwordHash], [registeredAt], [lastLogin], [intro], [profile], [image], [type], [uniqueId], [status]) VALUES (6, N'Bạch', N'', N'Bin', N'', N'binlienminh012@gmail.com', N'C1111BD512B29E821B120B86446026B8', CAST(N'2021-11-21T16:01:54.357' AS DateTime), CAST(N'2022-04-23T14:04:54.207' AS DateTime), N'', N'mạnh', N'about1.jpg', 1, 552931, N'Offline')
INSERT [dbo].[Users] ([id], [firstName], [middleName], [lastName], [mobile], [email], [passwordHash], [registeredAt], [lastLogin], [intro], [profile], [image], [type], [uniqueId], [status]) VALUES (7, N'Bạch', N'Văn', N'Mạnh', N'0827700854', N'bachvanmanh06072000@gmail.com', N'75F0817DA202DFFF6C1E8BF6EC5FAD8F', CAST(N'2021-11-21T16:06:11.653' AS DateTime), CAST(N'2022-05-10T20:15:29.780' AS DateTime), N'Bạch Văn Mạnh, 21 tuổi, học tại HUSC', N'Name: Bạch Văn Mạnh
Age: 21
POB: Huế
DOB: 06/07/2000', N'about1.jpg', 2, 347541, N'Online')
INSERT [dbo].[Users] ([id], [firstName], [middleName], [lastName], [mobile], [email], [passwordHash], [registeredAt], [lastLogin], [intro], [profile], [image], [type], [uniqueId], [status]) VALUES (8, N'Hoàng', N'Thế ', N'Huy', NULL, N'huy@gmail.com', N'11967D5E9ADDC5416EA9224EEE0E91FC', CAST(N'2021-11-21T16:06:50.537' AS DateTime), CAST(N'2022-04-23T13:50:55.993' AS DateTime), NULL, N'bạn của mạnh', N'm3.jpg', 0, 427963, N'Offline')
INSERT [dbo].[Users] ([id], [firstName], [middleName], [lastName], [mobile], [email], [passwordHash], [registeredAt], [lastLogin], [intro], [profile], [image], [type], [uniqueId], [status]) VALUES (9, N'Trần', N'Trung', N'Chính', NULL, N'chinh@gmail.com', N'FDF2896AB138499292E4AD879874F68A', CAST(N'2021-11-22T10:58:18.493' AS DateTime), CAST(N'2022-03-25T22:08:16.427' AS DateTime), NULL, NULL, N'm2.jpg', 0, 757943, N'Offline')
INSERT [dbo].[Users] ([id], [firstName], [middleName], [lastName], [mobile], [email], [passwordHash], [registeredAt], [lastLogin], [intro], [profile], [image], [type], [uniqueId], [status]) VALUES (10, NULL, N'Nhật', N'Tài', NULL, N'tai@gmail.com', N'A412BA79E6BCD018C48FAF00F057C0BB', CAST(N'2021-12-06T21:58:47.610' AS DateTime), CAST(N'2021-12-06T21:58:57.397' AS DateTime), NULL, NULL, NULL, 0, 847992, N'Offline')
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
ALTER TABLE [dbo].[Category] ADD  CONSTRAINT [DF_Category_parentId]  DEFAULT (NULL) FOR [parentId]
GO
ALTER TABLE [dbo].[Category] ADD  CONSTRAINT [DF_Category_content]  DEFAULT (NULL) FOR [content]
GO
ALTER TABLE [dbo].[Config] ADD  CONSTRAINT [DF_Config_allowLogin]  DEFAULT ((1)) FOR [allowLogin]
GO
ALTER TABLE [dbo].[Config] ADD  CONSTRAINT [DF_Config_allowRegistry]  DEFAULT ((1)) FOR [allowRegistry]
GO
ALTER TABLE [dbo].[Config] ADD  CONSTRAINT [DF_Config_allowPost]  DEFAULT ((1)) FOR [allowPost]
GO
ALTER TABLE [dbo].[Config] ADD  CONSTRAINT [DF_Config_allowChat]  DEFAULT ((1)) FOR [allowChat]
GO
ALTER TABLE [dbo].[Config] ADD  CONSTRAINT [DF_Config_Forum]  DEFAULT ((1)) FOR [Forum]
GO
ALTER TABLE [dbo].[Post] ADD  CONSTRAINT [DF_Post_parentId]  DEFAULT (NULL) FOR [parentId]
GO
ALTER TABLE [dbo].[Post] ADD  CONSTRAINT [DF_Post_published]  DEFAULT ((0)) FOR [solved]
GO
ALTER TABLE [dbo].[Post] ADD  CONSTRAINT [DF_Post_updatedAt]  DEFAULT (NULL) FOR [updatedAt]
GO
ALTER TABLE [dbo].[Post] ADD  CONSTRAINT [DF_Post_content]  DEFAULT (NULL) FOR [content]
GO
ALTER TABLE [dbo].[Post] ADD  CONSTRAINT [DF_Post_view]  DEFAULT ((0)) FOR [slView]
GO
ALTER TABLE [dbo].[Post] ADD  CONSTRAINT [DF_Post_show]  DEFAULT ((0)) FOR [show]
GO
ALTER TABLE [dbo].[Post_Comment] ADD  CONSTRAINT [DF_Post_Comment_parentId]  DEFAULT (NULL) FOR [parentId]
GO
ALTER TABLE [dbo].[Post_Comment] ADD  CONSTRAINT [DF_Post_Comment_content]  DEFAULT (NULL) FOR [content]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_User_firstName]  DEFAULT (NULL) FOR [firstName]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_User_middleName]  DEFAULT (NULL) FOR [middleName]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_User_lastName]  DEFAULT (NULL) FOR [lastName]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_User_lastLogin]  DEFAULT (NULL) FOR [lastLogin]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_User_intro]  DEFAULT (NULL) FOR [intro]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_User_profile]  DEFAULT (NULL) FOR [profile]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_User_type]  DEFAULT ((0)) FOR [type]
GO
ALTER TABLE [dbo].[CommentLike]  WITH CHECK ADD  CONSTRAINT [FK_CommentLike_Post_Comment] FOREIGN KEY([commentId])
REFERENCES [dbo].[Post_Comment] ([id])
GO
ALTER TABLE [dbo].[CommentLike] CHECK CONSTRAINT [FK_CommentLike_Post_Comment]
GO
ALTER TABLE [dbo].[CommentLike]  WITH CHECK ADD  CONSTRAINT [FK_CommentLike_Users] FOREIGN KEY([authorId])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[CommentLike] CHECK CONSTRAINT [FK_CommentLike_Users]
GO
ALTER TABLE [dbo].[Post]  WITH CHECK ADD  CONSTRAINT [FK_Post_User] FOREIGN KEY([authorId])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Post] CHECK CONSTRAINT [FK_Post_User]
GO
ALTER TABLE [dbo].[Post_Category]  WITH CHECK ADD  CONSTRAINT [FK_Post_Category_Category] FOREIGN KEY([categoryId])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Post_Category] CHECK CONSTRAINT [FK_Post_Category_Category]
GO
ALTER TABLE [dbo].[Post_Category]  WITH CHECK ADD  CONSTRAINT [FK_Post_Category_Post] FOREIGN KEY([postId])
REFERENCES [dbo].[Post] ([id])
GO
ALTER TABLE [dbo].[Post_Category] CHECK CONSTRAINT [FK_Post_Category_Post]
GO
ALTER TABLE [dbo].[Post_Comment]  WITH CHECK ADD  CONSTRAINT [FK_Post_Comment_Post] FOREIGN KEY([postId])
REFERENCES [dbo].[Post] ([id])
GO
ALTER TABLE [dbo].[Post_Comment] CHECK CONSTRAINT [FK_Post_Comment_Post]
GO
ALTER TABLE [dbo].[Post_Comment]  WITH CHECK ADD  CONSTRAINT [FK_Post_Comment_Users] FOREIGN KEY([authorId])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Post_Comment] CHECK CONSTRAINT [FK_Post_Comment_Users]
GO
ALTER TABLE [dbo].[Post_Report]  WITH CHECK ADD  CONSTRAINT [FK_Post_Report_Post] FOREIGN KEY([postId])
REFERENCES [dbo].[Post] ([id])
GO
ALTER TABLE [dbo].[Post_Report] CHECK CONSTRAINT [FK_Post_Report_Post]
GO
ALTER TABLE [dbo].[PostLike]  WITH CHECK ADD  CONSTRAINT [FK_PostLike_Post] FOREIGN KEY([postId])
REFERENCES [dbo].[Post] ([id])
GO
ALTER TABLE [dbo].[PostLike] CHECK CONSTRAINT [FK_PostLike_Post]
GO
ALTER TABLE [dbo].[PostLike]  WITH CHECK ADD  CONSTRAINT [FK_PostLike_Users] FOREIGN KEY([authorId])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[PostLike] CHECK CONSTRAINT [FK_PostLike_Users]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0:member 1:manager 2:admin' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Users', @level2type=N'COLUMN',@level2name=N'type'
GO
USE [master]
GO
ALTER DATABASE [Forum] SET  READ_WRITE 
GO
