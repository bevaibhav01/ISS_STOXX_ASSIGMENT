create database studynotion;
use studynotion;

CREATE TABLE IF NOT EXISTS Students (
    StudentID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Age INT NOT NULL CHECK (Age > 18), -- Check constraint for age
    Gender VARCHAR(10),
    EnrollmentDate DATE NOT NULL,
    CourseID INT NOT NULL,
    FOREIGN KEY (CourseID) REFERENCES Courses(CourseID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Courses (
    CourseID INT AUTO_INCREMENT PRIMARY KEY,
    CourseName VARCHAR(100) NOT NULL,
    InstructorName VARCHAR(100) NOT NULL,
    Duration INT NOT NULL, -- Duration in hours
    Fee DECIMAL(10, 2) NOT NULL
);

INSERT INTO Courses (CourseName, InstructorName, Duration, Fee)
VALUES 
('Introduction to Machine Learning', 'Dr. John Doe', 40, 199.99),
('Data Science Fundamentals', 'Prof. Alice Smith', 35, 149.99),
('Python for Beginners', 'Mr. Bob Johnson', 30, 129.99),
('Web Development Bootcamp', 'Ms. Charlie Brown', 45, 249.99),
('Advanced JavaScript', 'Dr. David Lee', 50, 179.99),
('Introduction to Algorithms', 'Prof. Eva Green', 40, 169.99),
('Database Systems', 'Dr. Frank Miller', 38, 159.99),
('Machine Learning in Python', 'Ms. Grace Taylor', 42, 219.99),
('Digital Marketing Essentials', 'Mr. Henry Clark', 30, 129.99),
('Cybersecurity for Beginners', 'Prof. Laura White', 32, 139.99);


INSERT INTO Students (Name, Email, Age, Gender, EnrollmentDate, CourseID)
VALUES 
('Alice Smith', 'alice.smith@example.com', 24, 'Female', '2023-09-01', 1),
('Bob Johnson', 'bob.johnson@example.com', 26, 'Male', '2023-08-15', 2),
('Charlie Brown', 'charlie.brown@example.com', 22, 'Male', '2023-10-01', 3),
('David Lee', 'david.lee@example.com', 29, 'Male', '2023-07-20', 4),
('Eva Green', 'eva.green@example.com', 25, 'Female', '2023-06-05', 5),
('Frank Miller', 'frank.miller@example.com', 28, 'Male', '2023-05-30', 6),
('Grace Taylor', 'grace.taylor@example.com', 23, 'Female', '2023-07-25', 7),
('Henry Clark', 'henry.clark@example.com', 30, 'Male', '2023-09-10', 8),
('Laura White', 'laura.white@example.com', 27, 'Female', '2023-06-15', 9),
('John Harris', 'john.harris@example.com', 31, 'Male', '2023-05-22', 10),
('Linda Green', 'linda.green@example.com', 32, 'Female', '2023-04-10', 1),
('Michael Scott', 'michael.scott@example.com', 26, 'Male', '2023-07-18', 2),
('Sophie Turner', 'sophie.turner@example.com', 24, 'Female', '2023-08-01', 3),
('James Bond', 'james.bond@example.com', 33, 'Male', '2023-06-20', 4),
('Olivia Brown', 'olivia.brown@example.com', 28, 'Female', '2023-05-12', 5);


-- Select all students with safe aliases
SELECT s.StudentID, s.Name AS StudentName, s.Email, c.CourseName 
FROM Students AS s 
JOIN Courses AS c ON s.CourseID = c.CourseID;

-- Order students by enrollment date in descending order
SELECT * 
FROM Students 
ORDER BY EnrollmentDate DESC;

-- Find students enrolled in specific courses using IN
SELECT Name, Email 
FROM Students 
WHERE CourseID IN (SELECT CourseID FROM Courses WHERE CourseName IN ('Java Programming', 'Python Programming'));

-- Update fee for a course
UPDATE Courses 
SET Fee = 299.99 
WHERE CourseName = 'Data Science';



-- Fetch student and their respective course details
SELECT s.Name AS StudentName, c.CourseName, c.InstructorName 
FROM Students AS s 
INNER JOIN Courses AS c 
ON s.CourseID = c.CourseID;


-- Fetch all students with course details (if enrolled)
SELECT s.Name AS StudentName, c.CourseName 
FROM Students AS s 
LEFT JOIN Courses AS c 
ON s.CourseID = c.CourseID;

-- Combine student names and instructor names
SELECT Name AS Participant 
FROM Students
UNION 
SELECT InstructorName AS Participant 
FROM Courses;

-- Create a view for students and their course details
CREATE OR REPLACE VIEW StudentCourses AS 
SELECT s.StudentID, s.Name AS StudentName, c.CourseName, c.InstructorName, c.Fee 
FROM Students AS s 
JOIN Courses AS c ON s.CourseID = c.CourseID;

SELECT * FROM StudentCourses;


-- Group students by CourseID and count them
SELECT CourseID, COUNT(*) AS TotalStudents 
FROM Students 
GROUP BY CourseID;

-- Only show courses with more than 5 students
SELECT CourseID, COUNT(*) AS TotalStudents 
FROM Students 
GROUP BY CourseID 
HAVING COUNT(*) > 5;

-- Select students without an email
SELECT * 
FROM Students 
WHERE Email IS NULL;




