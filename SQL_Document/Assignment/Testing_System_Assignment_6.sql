DROP DATABASE IF EXISTS `Testing_System_Assignment_6`;
CREATE DATABASE `Testing_System_Assignment_6`;
USE `Testing_System_Assignment_1`;

-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các account thuộc phòng ban đó

DROP PROCEDURE IF EXISTS get_DepartmentName;
DELIMITER $$
CREATE PROCEDURE get_DepartmentName (IN in_Department_Name VARCHAR(50))
	BEGIN
		SELECT	*
        FROM	`Department` D
        JOIN	`Account` A
        USING	(DepartmentID)
        WHERE	DepartmentName = in_Department_Name;
    END$$
DELIMITER ;

CALL get_DepartmentName('Sale');
-- Question 2: Tạo store để in ra số lượng account trong mỗi group

DROP PROCEDURE IF EXISTS get_AccountOfGroup;
DELIMITER $$
CREATE PROCEDURE get_AccountOfGroup (IN in_GroupID TINYINT UNSIGNED)
	BEGIN
		SELECT 		GroupID, COUNT(AccountID) AS 'So_luong'
		FROM		`GroupAccount` 
		WHERE		GroupID = in_GroupID
		GROUP BY	GroupID;
	END$$
DELIMITER ;

-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo trong tháng hiện tại

DROP PROCEDURE IF EXISTS get_TypeQuestion;
DELIMITER $$
CREATE PROCEDURE get_TypeQuestion()
	BEGIN
		SELECT 		COUNT(TypeID)
        FROM 		`Question`
        WHERE 		MONTH(CreateDate) = MONTH(NOW());
    END$$
DELIMITER ;

-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất
DROP PROCEDURE IF EXISTS get_TypeQuestion_ID; 
DELIMITER $$
CREATE PROCEDURE get_TypeQuestion_ID()
	BEGIN
		    SELECT 		TQ.TypeID
			FROM		`Question` Q
            JOIN 		`TypeQuestion` TQ
			GROUP BY 	Q.TypeID
			HAVING		COUNT(Q.TypeID) = (SELECT MAX(COUNTQ)
											FROM (SELECT	COUNT(TypeID) AS COUNTQ		
													FROM		`Question` 
													GROUP BY	TypeID) AS MaxTypeID);
    END$$
DELIMITER ;

-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question

DROP PROCEDURE IF EXISTS get_TypeQuestion_Name; 
DELIMITER $$
CREATE PROCEDURE get_TypeQuestion_Name()
	BEGIN
		    SELECT 		TQ.TypeName
			FROM		`Question` Q
            JOIN 		`TypeQuestion` TQ
			GROUP BY 	Q.TypeID
			HAVING		COUNT(Q.TypeID) = (SELECT MAX(COUNTQ)
											FROM (SELECT	COUNT(TypeID) AS COUNTQ		
													FROM		`Question` 
													GROUP BY	TypeID) AS MaxTypeID);
    END$$
DELIMITER ;

/* Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên 
 chứa chuỗi của người dùng nhập vào hoặc trả về user có username chứa 
 chuỗi của người dùng nhập vào */
DROP PROCEDURE IF EXISTS get_String;
DELIMITER $$
CREATE PROCEDURE get_String (IN in_GroupName VARCHAR(20), IN in_UserName VARCHAR(50), in_select TINYINT )
	BEGIN
		IF in_select = 1 THEN
        
			SELECT 	*
			FROM 	`Group`
			WHERE 	GroupName = in_GroupName;
            
		ELSE
			
            SELECT *
            FROM	`Account`
            WHERE	Username = in_UserName;
		END IF;
	END $$
DELIMITER ;
-- Trả về Group
CALL `get_String` ('LonDon', 'tuananhbui', 1);
-- Trả về User
CALL `get_String` ('LonDon', 'tuananhbui', 2);

/* Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và 
 trong store sẽ tự động gán:
username sẽ giống email nhưng bỏ phần @..mail đi
positionID: sẽ có default là developer
departmentID: sẽ được cho vào 1 phòng chờ
 Sau đó in ra kết quả tạo thành công */
 
DROP PROCEDURE IF EXISTS get_IFAccount;
DELIMITER $$
CREATE PROCEDURE get_IFAccount(IN in_FullName VARCHAR(50), in_Email VARCHAR(50))
BEGIN
	INSERT INTO `Account` (Email, Username, FullName, DepartmentID, PositionID)
    VALUES (
		in_email, SUBSTRING_INDEX(in_email, '@', 1), in_fullName, '9' , '1'
    );
    SELECT *
    FROM `account` a ;
END $$
CALL testing_system_assignment_1.get_IFAccount('Le Tuan Anh', 'tuananh');
/* Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice
 để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất */
 
DROP PROCEDURE IF EXISTS get_MaxContentWithTypeName;
DELIMITER $$
CREATE PROCEDURE get_MaxContentWithTypeName(IN in_TypeName VARCHAR(15))
BEGIN
	IF (in_TypeName = 'Essay') THEN
		SELECT	Content, MAX(LENGTH(Content))
		FROM	Question
		WHERE	TypeID = 1;
	ELSEIF (in_TypeName = 'Multiple-Choice') THEN
		SELECT	Content, MAX(LENGTH(Content))
		FROM	Question
		WHERE	TypeID = 2;
	END IF;
END$$
DELIMITER ;
-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID

DROP PROCEDURE IF EXISTS del_ExamID;
DELIMITER $$
CREATE PROCEDURE del_ExamID (IN in_ExamID TINYINT UNSIGNED)
	BEGIN
		DELETE
        FROM	`Exam`
        WHERE 	ExamID = in_ExamID;
	END $$
DELIMITER ; 

/* Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử 
 dụng store ở câu 9 để xóa)
 Sau đó in số lượng record đã remove từ các table liên quan trong khi 
 removing */
DROP PROCEDURE IF EXISTS DelExam3Year;
DELIMITER $$
CREATE PROCEDURE DelExam3Year()
	BEGIN
		WITH `Exam3YearAgo` AS
        (
			SELECT 	ExamID 
            FROM	`Exam`
            WHERE 	(YEAR(NOW()) - YEAR(CreateDate)) = 3
		)
        DELETE
        FROM	`Exam`
        WHERE 	ExamID IN (	SELECT * FROM `Exam3YearAgo`);
	END $$
BEGIN WORK;
ROLLBACK;
DELIMITER ;

/* Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng 
 nhập vào tên phòng ban và các account thuộc phòng ban đó sẽ được 
 chuyển về phòng ban default là phòng ban chờ việc */
DROP PROCEDURE IF EXISTS DeleteDepartment;
DELIMITER $$
CREATE PROCEDURE DeleteDepartment(IN in_DepartmentName VARCHAR(20))
BEGIN
	INSERT INTO `Department` VALUES (11,'Waiting');
	UPDATE 	`Account`
    SET		DepartmentID = 11
    WHERE	DepartmentID = (SELECT 	DepartmentID	
							FROM	Department
							WHERE 	DepartmentName = in_DepartmentName);
	DELETE 
    FROM	Department
    WHERE	DepartmentName = in_DepartmentName;
END$$
DELIMITER ;
BEGIN WORK;
ROLLBACK;
SELECT * FROM `Account`;
-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay
DROP PROCEDURE IF EXISTS sp_CountQuesInMonth;
DELIMITER $$
CREATE PROCEDURE sp_CountQuesInMonth()
BEGIN
		SELECT EachMonthInYear.MONTH, COUNT(QuestionID) AS COUNT
		FROM
		(
             SELECT 1 AS MONTH
             UNION SELECT 2 AS MONTH
             UNION SELECT 3 AS MONTH
             UNION SELECT 4 AS MONTH
             UNION SELECT 5 AS MONTH
             UNION SELECT 6 AS MONTH
             UNION SELECT 7 AS MONTH
             UNION SELECT 8 AS MONTH
             UNION SELECT 9 AS MONTH
             UNION SELECT 10 AS MONTH
             UNION SELECT 11 AS MONTH
             UNION SELECT 12 AS MONTH
        ) AS EachMonthInYear
		LEFT JOIN Question ON EachMonthInYear.MONTH = MONTH(CreateDate)
		GROUP BY EachMonthInYear.MONTH
		ORDER BY EachMonthInYear.MONTH ASC;
END$$
DELIMITER ;

/* Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 
 tháng gần đây nhất
 (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong 
tháng") */
DROP PROCEDURE IF EXISTS sp_CountQuesPrevious6Month;
DELIMITER $$
CREATE PROCEDURE sp_CountQuesPrevious6Month()
BEGIN
		SELECT Previous6Month.MONTH, COUNT(QuestionID) AS COUNT
		FROM
		(
			SELECT MONTH(CURRENT_DATE - INTERVAL 5 MONTH) AS MONTH
			UNION
			SELECT MONTH(CURRENT_DATE - INTERVAL 4 MONTH) AS MONTH
			UNION
			SELECT MONTH(CURRENT_DATE - INTERVAL 3 MONTH) AS MONTH
			UNION
			SELECT MONTH(CURRENT_DATE - INTERVAL 2 MONTH) AS MONTH
			UNION
			SELECT MONTH(CURRENT_DATE - INTERVAL 1 MONTH) AS MONTH
			UNION
			SELECT MONTH(CURRENT_DATE - INTERVAL 0 MONTH) AS MONTH
        ) AS Previous6Month
		LEFT JOIN Question ON Previous6Month.MONTH = MONTH(CreateDate)
		GROUP BY Previous6Month.MONTH
		ORDER BY Previous6Month.MONTH ASC;
END$$
DELIMITER ;
