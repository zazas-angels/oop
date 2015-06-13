
DROP database IF EXISTS ServisSite;
Create database ServisSite;
USE ServisSite;


DROP TABLE IF EXISTS pictures;
CREATE TABLE pictures (
	ID int not null auto_increment,
	imagefile CHAR(64),    
	primary key(ID)
);

##colors
DROP TABLE IF EXISTS colors;
CREATE TABLE colors (
	ID int not null auto_increment,
    value varchar(32),
	primary key(ID)
);


##font
DROP TABLE IF EXISTS fonts;
CREATE TABLE fonts (
	ID int not null auto_increment,
   value  varchar(32),
	primary key(ID)
);
##theme
DROP TABLE IF EXISTS themes;
CREATE TABLE themes (
	ID int not null auto_increment,
    FontID int default 1,
	BackColorID int default 1,
    FontColorID int default 1,
    FontSize double default 11,
    IsBold bool default false,
    IsItalyc bool default false,    
	BackPictureID int default null,
	primary key(ID),
     FOREIGN KEY (FontID) REFERENCES fonts(ID),
    FOREIGN KEY (BackColorID) REFERENCES colors(ID),
     FOREIGN KEY (FontColorID) REFERENCES colors(ID),
      FOREIGN KEY (BackPictureID) REFERENCES pictures(ID)
);
DROP TABLE IF EXISTS users;
CREATE TABLE users (
	ID int not null auto_increment,
	name varchar(150),
    url varchar(64),
    mail varchar(64),
    type VARCHAR(64),
    isActive bool default false,
     isBanned bool default false,
    password varchar(64),
    raiting double default 0,
    avatarFile CHAR(64) default "default.png" not null,
    ThemeID int default 1,
	primary key(ID),
    unique(url),
     unique(mail),
    FOREIGN KEY (ThemeID) REFERENCES themes(ID)
);

DROP TABLE IF EXISTS users_confcodes;
CREATE TABLE users_confcodes (
	ID int not null auto_increment,
	userId int not null,
	confirmCode  CHAR(64),
	  FOREIGN KEY (userId) REFERENCES users(ID), 
	  Unique(userId),
	primary key(ID)
);

DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
	ID int not null auto_increment,
	name varchar(150),
    ParentId int default null,
	primary key(ID),
	  unique(name,ParentId),
      FOREIGN KEY (ParentId) REFERENCES categories(ID)
);

DROP TABLE IF EXISTS users_categories;
CREATE TABLE users_categories (
	ID int not null auto_increment,
	UserID int not null,
    CategoryID int not null,
	primary key(ID),
    FOREIGN KEY (UserID) REFERENCES users(ID),
    FOREIGN KEY (CategoryID) REFERENCES categories(ID)
);


DROP TABLE IF EXISTS elements;
CREATE TABLE elements (
	ID int not null auto_increment,
	UserID int not null,
    Type enum('text', 'albom','picture','panel','comment'),
    X double default 0,
    Y double default 0,
    name varchar(64) default "",
    ##background
    ColorID int default null,
	Width double default 0,
    Heigth double default 0,
    ##plani
    ##also indicator is null
    hyperLink varchar(64) default null,
    
	primary key(ID),
    FOREIGN KEY (UserID) REFERENCES users(ID),
    FOREIGN KEY (ColorID) REFERENCES colors(ID)
    
);

DROP TABLE IF EXISTS texts;
CREATE TABLE texts (
	ID int not null auto_increment,
    ElementID int not null,
	value CHAR(200),     
	primary key(ID),
     FOREIGN KEY (ElementID) REFERENCES elements(ID)
);

##elements_info
DROP TABLE IF EXISTS elements_info;
CREATE TABLE elements_info (
	ID int not null auto_increment,   
	ElementID int not null,
    FontID int not null,
    FontSize double default 11,
    IsBold bool default false,
    IsItalyc bool default false,
    ColorID int not null,
	primary key(ID),
     FOREIGN KEY (ElementID) REFERENCES elements(ID),
     FOREIGN KEY (FontID) REFERENCES fonts(ID),
    FOREIGN KEY (ColorID) REFERENCES colors(ID)
);


##alboms
DROP TABLE IF EXISTS alboms;
CREATE TABLE alboms (
	ID int not null auto_increment,
    AlbomID int not null,
    ##albom
    ParentAlbomID int default null,
	primary key(ID),
    FOREIGN KEY (AlbomID) REFERENCES alboms(ID)
);


##albom elemnt

DROP TABLE IF EXISTS alboms_elements;
CREATE TABLE alboms_elements (
	ID int not null auto_increment,
	ElementID int not null,
    AlbomID int not null,
	primary key(ID),
    FOREIGN KEY (AlbomID) REFERENCES alboms(ID),
    FOREIGN KEY (ElementID) REFERENCES elements(ID)
);


##albompicture

DROP TABLE IF EXISTS alboms_pictures;
CREATE TABLE alboms_pictures (
	ID int not null auto_increment,
    AlbomID int not null,
	PictureID int not null,
	primary key(ID),
    FOREIGN KEY (AlbomID) REFERENCES alboms(ID),
    FOREIGN KEY (PictureID) REFERENCES pictures(ID)
);


##picture element and can be add something easily(eg. frame)
DROP TABLE IF EXISTS elements_pictures;
CREATE TABLE elements_pictures (
	ID int not null auto_increment,
    ElementID int not null,
	PictureID int not null,
	primary key(ID),
    FOREIGN KEY (ElementID) REFERENCES elements(ID),
    FOREIGN KEY (PictureID) REFERENCES pictures(ID)
);


##admins
DROP TABLE IF EXISTS admins;
CREATE TABLE admins (
	ID int not null auto_increment,
   mail varchar(64),
    password varchar(64),
	primary key(ID)
);


##reports
DROP TABLE IF EXISTS reports;
CREATE TABLE reports (
    ID int not null auto_increment,
    authorName VARCHAR(64),
    authorUrl  VARCHAR(64) DEFAULT "#",
    text       VARCHAR(2000),
    postDate   DATETIME,
    primary key(ID)
);

##wantedCategories
DROP TABLE IF EXISTS wantedCategories;
CREATE TABLE wantedCategories (
    ID               INT NOT NULL AUTO_INCREMENT,
    authorName       VARCHAR(64),
    authorUrl        VARCHAR(64)  DEFAULT "#",
    cateogotyName    VARCHAR(64),
    parentCategoryID INT          DEFAULT NULL,
    postDate         DATE,
    PRIMARY KEY (ID),
    FOREIGN KEY (parentCategoryID) REFERENCES categories (ID)
);