DROP DATABASE IF EXISTS `Testing_System_Assignment_1`;
CREATE DATABASE IF NOT EXISTS `Testing_System_Assignment_1`;
USE `Testing_System_Assignment_1`;


/*------------------------- TẠO BẢNG ---------------------------------
-----------------------------------------------------------------------*/

-- Table Department
DROP TABLE IF EXISTS `Department`;
CREATE TABLE `Department`(
	DepartmentID 	TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    DepartmentName 	VARCHAR(50) NOT NULL
);

-- Table Position
DROP TABLE IF EXISTS `Position`;
CREATE TABLE `Position`(
	PositionID 		TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    PositionName 	ENUM('Dev', 'Test', 'Scrum Master', 'PM') UNIQUE KEY NOT NULL
    
);

-- Table Account
DROP TABLE IF EXISTS `Account`;
 CREATE TABLE `Account`(
	AccountID 		TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    Email 			VARCHAR(30) UNIQUE KEY NOT NULL,
    Username 		VARCHAR(50) UNIQUE KEY NOT NULL,
    FullName 		VARCHAR(60) NOT NULL,
    DepartmentID 	TINYINT UNSIGNED NOT NULL,
    PositionID 		TINYINT UNSIGNED NOT NULL,
    CreateDate 		DATETIME DEFAULT NOW(),
    FOREIGN KEY (DepartmentID) REFERENCES Department (DepartmentID) ON DELETE CASCADE ON UPDATE CASCADE
 );
 
 -- Table Group
 DROP TABLE IF EXISTS `Group`;
 CREATE TABLE `Group`(
	GroupID 		TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    GroupName 		VARCHAR(20) NOT NULL,
    CreatorID 		TINYINT UNSIGNED NOT NULL,
    CreateDate 		DATETIME DEFAULT NOW(),
    FOREIGN KEY (CreatorID) REFERENCES `Account`(AccountID) ON DELETE CASCADE ON UPDATE CASCADE
 );
 
  -- Table GroupAccount
 DROP TABLE IF EXISTS `GroupAccount`;
 CREATE TABLE `GroupAccount`(
	GroupID 		TINYINT UNSIGNED NOT NULL,
    AccountID 		TINYINT UNSIGNED NOT NULL,
    JoinDate 		DATETIME DEFAULT NOW(),
    CONSTRAINT kp_Group FOREIGN KEY (GroupID) REFERENCES `Group` (GroupID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT kp_Account FOREIGN KEY (AccountID) REFERENCES `Account` (AccountID) ON DELETE CASCADE ON UPDATE CASCADE
 );

 -- Table TypeQuestion
DROP TABLE IF EXISTS `TypeQuestion`;
 CREATE TABLE `TypeQuestion`(
	TypeID 			TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    TypeName 		ENUM('Essay', 'Multiple-Choice')
 
 );
  -- Table CategoryQuestion
 DROP TABLE IF EXISTS `CategoryQuestion`;
 CREATE TABLE `CategoryQuestion`(
	CategoryID 		TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    CategoryName 	ENUM('Java', '.NET', 'SQL', 'Postman', 'Ruby','PHP')
 );
 
  -- Table Question
 DROP TABLE IF EXISTS `Question`;
 CREATE TABLE `Question`(
	QuestionID 		TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    Content 		VARCHAR(500) NOT NULL,
    CategoryID 		TINYINT UNSIGNED NOT NULL,
    TypeID 			TINYINT UNSIGNED NOT NULL,
    CreatorID 		TINYINT UNSIGNED NOT NULL,
    CreateDate 		DATETIME DEFAULT NOW(),
    FOREIGN KEY (CategoryID) REFERENCES CategoryQuestion (CategoryID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (TypeID) REFERENCES TypeQuestion (TypeID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CreatorID) REFERENCES `Account`(AccountID) ON DELETE CASCADE ON UPDATE CASCADE
 );
 
 -- Table Answer
 DROP TABLE IF EXISTS Answer;
 CREATE TABLE Answer(
	AnswerID 		TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    Content  		VARCHAR(500) NOT NULL,
    QuestionID 		TINYINT UNSIGNED NOT NULL,
    isCorrect 		BIT NOT NULL,
    FOREIGN KEY (QuestionID) REFERENCES Question (QuestionID) ON DELETE CASCADE ON UPDATE CASCADE   
 );

 -- Table Exam
 DROP TABLE IF EXISTS Exam;
 CREATE TABLE Exam(
	ExamID 			TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `Code` 			CHAR(15) NOT NULL UNIQUE KEY,
    Title  			VARCHAR(50) NOT NULL,
    CategoryID 		TINYINT UNSIGNED NOT NULL,
    Duration 		TINYINT UNSIGNED NOT NULL,
    CreatorID 		TINYINT UNSIGNED NOT NULL,
    CreateDate		DATETIME DEFAULT NOW(),
    FOREIGN KEY (CategoryID) REFERENCES CategoryQuestion (CategoryID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CreatorID) REFERENCES `Account`(AccountID) ON DELETE CASCADE ON UPDATE CASCADE
 );
 
 
 -- Table ExamQuestion
 DROP TABLE IF EXISTS ExamQuestion;
 CREATE TABLE ExamQuestion(
	ExamID 			TINYINT UNSIGNED NOT NULL,
    QuestionID 		TINYINT UNSIGNED NOT NULL,
    PRIMARY KEY(ExamID, QuestionID)
 );