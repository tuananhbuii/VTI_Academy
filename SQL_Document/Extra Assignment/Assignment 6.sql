DROP DATABASE IF EXISTS Assignment_6;
CREATE DATABASE Assignment_6;
USE Assignment_6;

CREATE TABLE IF NOT EXISTS `Projects`(
    ProjectID           TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    ManagerID           TINYINT UNSIGNED UNIQUE KEY ,
    ProjectName         VARCHAR(50) NOT NULL ,
    ProjectStartDate    DATETIME DEFAULT NOW(),
    ProjectDescription  VARCHAR(100),
    ProjectDetail       VARCHAR(100),
    ProjetcCompleteOn   DATETIME DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS `Employee`(
    EmployeeID              TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    EmployeeLastName        VARCHAR(50) NOT NULL ,
    EmployeeHireDate        DATETIME DEFAULT NOW(),
    EmployeeStatus          VARCHAR(100),
    SupervisorID            TINYINT UNSIGNED NOT NULL ,
    SocialSecurityNumber    INT UNSIGNED,
    CONSTRAINT kp_SupervisorID FOREIGN KEY (SupervisorID) REFERENCES `Projects` (ManagerID) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS `Project_Modules`(
    ModuleID                    TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    ProjectID                   TINYINT UNSIGNED NOT NULL ,
    EmployeeID                  TINYINT UNSIGNED NOT NULL ,
    ProjectModulesDate          DATETIME DEFAULT NOW(),
    ProjetcModulesCompleteOn    DATETIME DEFAULT NOW(),
    ProjectModulesDescription   VARCHAR(100),
    CONSTRAINT kp_ProjectID FOREIGN KEY (ProjectID) REFERENCES `Projects` (ProjectID) ON DELETE CASCADE,
    CONSTRAINT kp_EmployeeID_modules FOREIGN KEY (EmployeeID) REFERENCES `Employee`(EmployeeID)ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `Work_Done`
(
    WorkDoneID          TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    EmployeeID          TINYINT UNSIGNED NOT NULL,
    ModuleID            TINYINT UNSIGNED NOT NULL,
    WorkDoneDate        DATETIME DEFAULT NOW(),
    WorkDoneDescription VARCHAR(100),
    WorkDoneStatus      VARCHAR(50),
    CONSTRAINT kp_EmployeeID_work FOREIGN KEY (EmployeeID) REFERENCES `Employee` (EmployeeID)ON DELETE CASCADE,
    CONSTRAINT kp_ModuleID FOREIGN KEY (ModuleID) REFERENCES `Project_Modules` (ModuleID)ON DELETE CASCADE
);

INSERT INTO `Projects` (ManagerID,ProjectName, ProjectStartDate, ProjectDescription , ProjectDetail, ProjetcCompleteOn)
VALUES                 (    1    , 'Dự án A' ,  '2021-06-01'   ,'Xây dựng phòng bếp',    'Working' ,    '2021-07-07'  ),
                       (    2    , 'Dự án B' ,  '2021-05-01'   , 'Xây dựng nhà WC'  ,    'Waiting' ,    '2021-06-06'  ),
                       (    3    , 'Dự án C' ,  '2021-04-01'   ,'Xây dựng phòng ngủ',    'Working' ,    '2021-05-05'  ),
                       (    4    , 'Dự án D' ,  '2021-03-01'   , 'Xây dựng Nhà Kho' ,    'Waiting' ,    '2021-04-04'  ),
                       (    5    , 'Dự án E' ,  '2021-02-01'   ,'Xây dựng phòng họp',    'Working' ,    '2021-03-03'  );

INSERT INTO `Employee` (EmployeeLastName, EmployeeHireDate, EmployeeStatus, SupervisorID, SocialSecurityNumber)
VALUES                 (   'Tuấn Anh'   ,  '2012-05-01'   ,   'Kỹ sư'     ,       5     ,      '17103100'  ),
                       (   'Đình Phong' ,  '2015-02-05'   ,   'Công Nhân' ,       4     ,      '17103101'  ),
                       (   'Đinh Thủy'  ,  '2018-06-09'   ,   'Công Nhân' ,       5     ,      '17103102'  ),
                       (   'Quốc Tuấn'  ,  '2012-05-01'   ,   'Kỹ sư'     ,       4     ,      '17103103'  ),
                       (   'Hải Anh'    ,  '2013-12-25'   ,   'Kỹ sư'     ,       3     ,      '17103104'  ),
                       (   'Đình Tiến'  ,  '2019-11-28'   ,   'Công Nhân' ,       2     ,      '17103105'  ),
                       (   'Lê Hải'     ,  '2020-10-25'   ,   'Công Nhân' ,       1     ,      '17103106'  ),
                       (   'Hoa Mai'    ,  '2020-05-15'   ,   'Kế Toán'   ,       5     ,      '17103107'  ),
                       (   'Phạm Hùng'  ,  '2021-02-23'   ,   'Công Nhân' ,       5     ,      '17103108'  ),
                       (   'Chí Lê'     ,  '2021-03-16'   ,   'Công Nhân' ,       5     ,      '17103109'  );

INSERT INTO `Project_Modules` (ProjectID, EmployeeID, ProjectModulesDate, ProjetcModulesCompleteOn, ProjectModulesDescription)
VALUES                        (    1    ,    7      ,      '2021-06-15' ,         '2021-06-20'    ,    'Thiết kế Tủ bếp'     ),
                              (    1    ,    7      ,      '2021-06-20' ,         '2021-06-30'    ,    'Thiết kế Bồn rửa'    ),
                              (    5    ,    1      ,      '2021-02-5'  ,         '2021-02-25'    ,    'Thiết kế Camera'     ),
                              (    5    ,    1      ,      '2021-02-6'  ,         '2021-02-26'    ,    'Thiết kế Máy chiếu'  ),
                              (    5    ,    1      ,      '2021-02-7'  ,         '2021-02-27'    ,    'Thiết kế Bàn họp'    );

INSERT INTO `Work_Done` (EmployeeID, ModuleID, WorkDoneDate, WorkDoneDescription, WorkDoneStatus)
VALUES                  (     7    ,     1   , '2021-06-18',    'Đã xong'       ,   'Dự án A'   ),
                        (     7    ,     1   , '2021-06-22',    'Đã xong'       ,   'Dự án A'   ),
                        (     2    ,     5   , '2021-02-15',    'Đã xong'       ,   'Dự án E'   ),
                        (     1    ,     5   , '2021-02-16',    'Đã xong'       ,   'Dự án E'   ),
                        (     1    ,     5   , '2021-02-17',    'Đã xong'       ,   'Dự án E'   );


/* b) Viết stored procedure (không có parameter) để Remove tất cả thông tin
project đã hoàn thành sau 3 tháng kể từ ngày hiện. In số lượng record đã
remove từ các table liên quan trong khi removing (dùng lệnh print) */

/*DROP PROCEDURE IF EXISTS Ques_b;
DELIMITER //
CREATE PROCEDURE Ques_b()
    BEGIN
        WITH `Project_3_Month` AS
        (
            SELECT  ProjectID
            FROM    `Projects`
            WHERE  MONTH(NOW()) - MONTH(ProjetcCompleteOn) >= 3
        )
        DELETE
        FROM  `Projects`
        WHERE ProjectID IN (SELECT * FROM `Project_3_Month`);
    end //
DELIMITER ;
CALL Ques_b();*/
-- c) Viết stored procedure (có parameter) để in ra các module đang được thực hiện

DROP PROCEDURE IF EXISTS Ques_c;
DELIMITER $$
CREATE PROCEDURE  Ques_c( OUT id_module TINYINT)
    BEGIN
        SELECT ModuleID
        FROM   `Project_Modules`
        WHERE  ModuleID NOT IN (SELECT ModuleID FROM `Work_Done`);
    end $$
DELIMITER ;
 CALL Ques_c(@idMol);

/* d) Viết hàm (có parameter) trả về thông tin 1 nhân viên đã tham gia làm mặc
dù không ai giao việc cho nhân viên đó (trong bảng Works) */

DROP PROCEDURE IF EXISTS Ques_d;
DELIMITER //
CREATE PROCEDURE Ques_d(OUT Employ_id TINYINT)
    BEGIN
        SELECT EmployeeID INTO Employ_id
        FROM   `work_done`
        WHERE EmployeeID NOT IN (SELECT EmployeeID FROM Project_Modules)LIMIT 1;
    end //
DELIMITER ;
set @Employ_id = 0;
call assignment_6.Ques_d(@Employ_id);
select @Employ_id;
