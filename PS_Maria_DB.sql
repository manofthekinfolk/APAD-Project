create database pickupsport;
use pickupsport;

CREATE TABLE Users(PKUserID INT PRIMARY KEY, Admin_ BOOL not NULL,FirstName VARCHAR(50) Not NULL, LastName VARCHAR(30) NOT NULL, Email VARCHAR(50) NOT NULL, Pass_word VARCHAR(50) NOT NULL, StreetAddress VARCHAR(100) NOT NULL, City VARCHAR(50) NOT NULL, State_ VARCHAR(30), ZipCode int(5));

CREATE TABLE Events(
PKEventID INT primary key NOT NULL AUTO_INCREMENT,
FKSportsID INT, 
FieldName VARCHAR(50),
EventDescription VARCHAR(1000), 
UserCapacity INT NOT NULL, 
TimeStart datetime NOT NULL,
TimeEnd datetime NOT NULL, 
FKVenueID INT, 
FKTagID INT);

CREATE TABLE Tags(
TagID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
TagName VARCHAR(20) NOT NULL);

CREATE TABLE Venues(
PKVenueID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
Venue VARCHAR(100) NOT NULL, 
EventCapacity INT NOT NULL);

CREATE TABLE UsersEvent(
PKUsersEvent INT NOT NULL PRIMARY KEY,
FKUserID INT, 
FKEventID INT);


use pickupsport;
alter table events add constraint fk_venueid foreign key (FKVenueID) 
references Venues(PKVenueID) on update restrict on delete set null;

alter table events add constraint fk_tagid foreign key (FKTagID) 
references Tags(TagID) on update restrict on delete set null;

alter table UsersEvent add constraint fk_euserid foreign key (FKUserID) 
references Users(PKUserID) on update restrict on delete set null;

alter table UsersEvent add constraint fk_eventid foreign key (FKEventID) 
references Events(PKEventID) on update restrict on delete set null;

INSERT INTO Users(PKUserID,Admin_,FirstName,LastName,Email,Pass_word,StreetAddress,City,State_,ZipCode) VALUES (1,1,'Steve', 'Rogers','sr@gmail.com','peggy','123 Idk', 'New York City','NY', 34521);
INSERT INTO Events()
INSERT INTO UsersEvent()
INSERT INTO Venues(PKVenueID,Venue,EventCapacity)