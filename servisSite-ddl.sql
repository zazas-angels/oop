DROP DATABASE IF EXISTS ServisSite;
CREATE DATABASE ServisSite;
USE ServisSite;


DROP TABLE IF EXISTS pictures;
CREATE TABLE pictures (
  ID        INT NOT NULL AUTO_INCREMENT,
  imagefile CHAR(64),
  PRIMARY KEY (ID)
);

##colors
DROP TABLE IF EXISTS colors;
CREATE TABLE colors (
  ID    INT NOT NULL AUTO_INCREMENT,
  value VARCHAR(32),
  PRIMARY KEY (ID)
);


##font
DROP TABLE IF EXISTS fonts;
CREATE TABLE fonts (
  ID    INT NOT NULL AUTO_INCREMENT,
  value VARCHAR(32),
  PRIMARY KEY (ID)
);
##theme
DROP TABLE IF EXISTS themes;
CREATE TABLE themes (
  ID            INT NOT NULL AUTO_INCREMENT,
  FontID        INT          DEFAULT 1,
  BackColorID   INT          DEFAULT 1,
  FontColorID   INT          DEFAULT 1,
  FontSize      DOUBLE       DEFAULT 11,
  IsBold        BOOL         DEFAULT FALSE,
  IsItalyc      BOOL         DEFAULT FALSE,
  BackPictureID INT          DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (FontID) REFERENCES fonts (ID),
  FOREIGN KEY (BackColorID) REFERENCES colors (ID),
  FOREIGN KEY (FontColorID) REFERENCES colors (ID),
  FOREIGN KEY (BackPictureID) REFERENCES pictures (ID)
);
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  ID         INT                            NOT NULL AUTO_INCREMENT,
  name       VARCHAR(150),
  url        VARCHAR(64),
  mail       VARCHAR(64),
  type       VARCHAR(64),
  isActive   BOOL                                    DEFAULT FALSE,
  isBanned   BOOL                                    DEFAULT FALSE,
  password   VARCHAR(64),
  raiting    DOUBLE                                  DEFAULT 0,
  avatarFile CHAR(64) DEFAULT "default.png" NOT NULL,
  ThemeID    INT                                     DEFAULT 1,
  PRIMARY KEY (ID),
  UNIQUE (url),
  UNIQUE (mail),
  FOREIGN KEY (ThemeID) REFERENCES themes (ID)
);

DROP TABLE IF EXISTS users_confcodes;
CREATE TABLE users_confcodes (
  ID          INT NOT NULL AUTO_INCREMENT,
  userId      INT NOT NULL,
  confirmCode CHAR(64),
  FOREIGN KEY (userId) REFERENCES users (ID),
  UNIQUE (userId),
  PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
  ID       INT NOT NULL AUTO_INCREMENT,
  name     VARCHAR(150),
  ParentId INT          DEFAULT NULL,
  PRIMARY KEY (ID),
  UNIQUE (name, ParentId),
  FOREIGN KEY (ParentId) REFERENCES categories (ID)
);

DROP TABLE IF EXISTS users_categories;
CREATE TABLE users_categories (
  ID         INT NOT NULL AUTO_INCREMENT,
  UserID     INT NOT NULL,
  CategoryID INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (UserID) REFERENCES users (ID),
  FOREIGN KEY (CategoryID) REFERENCES categories (ID)
);


DROP TABLE IF EXISTS elements;
CREATE TABLE elements (
  ID        INT NOT NULL AUTO_INCREMENT,
  UserID    INT NOT NULL,
  Type      ENUM('text', 'albom', 'picture', 'panel', 'comment'),
  X         DOUBLE       DEFAULT 0,
  Y         DOUBLE       DEFAULT 0,
  name      VARCHAR(64)  DEFAULT "",
  ##background
  ColorID   INT          DEFAULT NULL,
  Width     DOUBLE       DEFAULT 0,
  Heigth    DOUBLE       DEFAULT 0,
  ##plani
  ##also indicator is null
  hyperLink VARCHAR(64)  DEFAULT NULL,

  PRIMARY KEY (ID),
  FOREIGN KEY (UserID) REFERENCES users (ID),
  FOREIGN KEY (ColorID) REFERENCES colors (ID)

);

DROP TABLE IF EXISTS texts;
CREATE TABLE texts (
  ID        INT NOT NULL AUTO_INCREMENT,
  ElementID INT NOT NULL,
  value     CHAR(200),
  PRIMARY KEY (ID),
  FOREIGN KEY (ElementID) REFERENCES elements (ID)
);

##elements_info
DROP TABLE IF EXISTS elements_info;
CREATE TABLE elements_info (
  ID        INT NOT NULL AUTO_INCREMENT,
  ElementID INT NOT NULL,
  FontID    INT NOT NULL,
  FontSize  DOUBLE       DEFAULT 11,
  IsBold    BOOL         DEFAULT FALSE,
  IsItalyc  BOOL         DEFAULT FALSE,
  ColorID   INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (ElementID) REFERENCES elements (ID),
  FOREIGN KEY (FontID) REFERENCES fonts (ID),
  FOREIGN KEY (ColorID) REFERENCES colors (ID)
);


##alboms
DROP TABLE IF EXISTS alboms;
CREATE TABLE alboms (
  ID            INT NOT NULL AUTO_INCREMENT,
  AlbomID       INT NOT NULL,
  ##albom
  ParentAlbomID INT          DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (AlbomID) REFERENCES alboms (ID)
);


##albom elemnt

DROP TABLE IF EXISTS alboms_elements;
CREATE TABLE alboms_elements (
  ID        INT NOT NULL AUTO_INCREMENT,
  ElementID INT NOT NULL,
  AlbomID   INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (AlbomID) REFERENCES alboms (ID),
  FOREIGN KEY (ElementID) REFERENCES elements (ID)
);


##albompicture

DROP TABLE IF EXISTS alboms_pictures;
CREATE TABLE alboms_pictures (
  ID        INT NOT NULL AUTO_INCREMENT,
  AlbomID   INT NOT NULL,
  PictureID INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (AlbomID) REFERENCES alboms (ID),
  FOREIGN KEY (PictureID) REFERENCES pictures (ID)
);


##picture element and can be add something easily(eg. frame)
DROP TABLE IF EXISTS elements_pictures;
CREATE TABLE elements_pictures (
  ID        INT NOT NULL AUTO_INCREMENT,
  ElementID INT NOT NULL,
  PictureID INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (ElementID) REFERENCES elements (ID),
  FOREIGN KEY (PictureID) REFERENCES pictures (ID)
);


##admins
DROP TABLE IF EXISTS admins;
CREATE TABLE admins (
  ID       INT NOT NULL AUTO_INCREMENT,
  mail     VARCHAR(64),
  password VARCHAR(64),
  PRIMARY KEY (ID)
);


##reports
DROP TABLE IF EXISTS reports;
CREATE TABLE reports (
  ID         INT NOT NULL AUTO_INCREMENT,
  authorName VARCHAR(64),
  authorUrl  VARCHAR(64)  DEFAULT "#",
  text       VARCHAR(2000),
  postDate   DATETIME,
  PRIMARY KEY (ID)
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

##notifications
DROP TABLE IF EXISTS notifications;
CREATE TABLE notifications (
  ID           INT NOT NULL AUTO_INCREMENT,
  notification ENUM ('createdUser'),
  userName     VARCHAR(64),
  userUrl      VARCHAR(64)  DEFAULT "#",
  postDate     DATETIME,
  PRIMARY KEY (ID)
);

DELIMITER //

CREATE TRIGGER addNotification BEFORE INSERT ON users
FOR EACH ROW
  BEGIN
    INSERT INTO notifications
    SET notification = 'createdUser', userName = NEW.name, userUrl = NEW.url, postDate = now();
  END;
//
DELIMITER ;


##markers for google maps
DROP TABLE IF EXISTS markers;
CREATE TABLE markers (
  ID      INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name    VARCHAR(60) NOT NULL,
  address VARCHAR(80) NOT NULL,
  lat     DOUBLE      NOT NULL,
  lng     DOUBLE      NOT NULL,
  userID  INT         NOT NULL,
  UNIQUE (userId, lat, lng),
  FOREIGN KEY (userID) REFERENCES users (ID)
);