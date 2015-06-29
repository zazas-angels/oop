DROP DATABASE IF EXISTS ServisSite;
CREATE DATABASE ServisSite
  CHARACTER SET 'utf8';
USE ServisSite;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  ID         INT                            NOT NULL AUTO_INCREMENT,
  name       VARCHAR(150),
  url        VARCHAR(64),
  mail       VARCHAR(64),
  type       VARCHAR(64),
  isActive   BOOL                                    DEFAULT FALSE,
  isBanned   BOOL                                    DEFAULT FALSE,
  bannDuration int default -1,
  bannDate   date default '2015-01-01',
  password   VARCHAR(64),
  raiting    DOUBLE                                  DEFAULT 0,
  avatarFile CHAR(64) DEFAULT "default.png" NOT NULL,

  PRIMARY KEY (ID),
  UNIQUE (url),
  UNIQUE (mail)

);

DROP TABLE IF EXISTS users_confcodes;
CREATE TABLE users_confcodes (
  ID          INT NOT NULL AUTO_INCREMENT,
  userId      INT NOT NULL,
  confirmCode CHAR(64),
  UNIQUE (userId),
  PRIMARY KEY (ID),
  FOREIGN KEY (userId) REFERENCES users (ID)
    ON DELETE CASCADE
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

DROP TABLE IF EXISTS user_page;
CREATE TABLE user_page (
  ID       INT NOT NULL AUTO_INCREMENT,
  page		LONGTEXT,
  UserId INT          DEFAULT NULL,
  unique(UserId),
  PRIMARY KEY (ID),
  FOREIGN KEY (UserId) REFERENCES users (ID)
    ON DELETE CASCADE
);


DROP TABLE IF EXISTS users_categories;
CREATE TABLE users_categories (
  ID         INT NOT NULL AUTO_INCREMENT,
  UserID     INT NOT NULL,
  CategoryID INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (CategoryID) REFERENCES categories (ID)
    ON DELETE CASCADE,
  FOREIGN KEY (UserID) REFERENCES users (ID)
    ON DELETE CASCADE
);



##admins
DROP TABLE IF EXISTS admins;
CREATE TABLE admins (
  ID       INT NOT NULL AUTO_INCREMENT,
  mail     VARCHAR(64),
  password VARCHAR(64),
  PRIMARY KEY (ID)
);

##superAdmins
DROP TABLE IF EXISTS superAdmins;
CREATE TABLE superAdmins (
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
  authorID  int  DEFAULT -1,
  text       VARCHAR(2000),
  postDate   DATETIME,
  PRIMARY KEY (ID)
);

##wantedCategories
DROP TABLE IF EXISTS wantedCategories;
CREATE TABLE wantedCategories (
  ID               INT NOT NULL AUTO_INCREMENT,
  authorName       VARCHAR(64),
  authorID  int  DEFAULT -1,
  categoryName VARCHAR(64),
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
  userID  int  DEFAULT -1,
  postDate     DATETIME,
  PRIMARY KEY (ID)
);

DELIMITER //


CREATE TRIGGER addNotification After INSERT ON users
FOR EACH ROW
  BEGIN
    INSERT INTO notifications
    SET notification = 'createdUser', userName = NEW.name, userID = NEW.ID, postDate = now();
    INSERT INTO user_page   SET page = '', UserId = NEW.ID;

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
    ON DELETE CASCADE
);


##tags for search
DROP TABLE IF EXISTS tags;
CREATE TABLE tags (
  ID      INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name    VARCHAR(60) NOT NULL,
  userID  INT         NOT NULL,
  UNIQUE (userID, name),
  FOREIGN KEY (userID) REFERENCES users (ID)
    ON DELETE CASCADE
);