CREATE DATABASE library_db;

USE library_db;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(100) NOT NULL,

    email VARCHAR(100) NOT NULL,

    username VARCHAR(50) UNIQUE NOT NULL,

    password VARCHAR(100) NOT NULL,

    role VARCHAR(20) DEFAULT 'USER',

    phone VARCHAR(20),

    department ENUM(
        'CSE',
        'IT',
        'MECHANICAL',
        'ELECTRICAL',
        'CIVIL',
        'ECE',
        'MBA',
        'BCA',
        'MCA'
    ) NOT NULL,

    enrollment_no CHAR(4) UNIQUE NOT NULL,

    fine DOUBLE DEFAULT 0,

    books_taken INT DEFAULT 0,

    books_due INT DEFAULT 0
);

SELECT * FROM library_db.users;

INSERT INTO users
(name,email,username,password,role,phone,department,enrollment_no,fine,books_taken,books_due)

VALUES

(
'Admin',
'admin@gmail.com',
'admin',
'admin123',
'ADMIN',
'9999999999',
'CSE',
'0001',
0,
0,
0
);

INSERT INTO users
(name,email,username,password,role,phone,department,enrollment_no,fine,books_taken,books_due)

VALUES

('Aman Sharma','aman1@gmail.com','aman1','1234','USER','9876543201','CSE','1001',0,2,0),

('Rohit Verma','rohit2@gmail.com','rohit2','1234','USER','9876543202','IT','1002',20,2,1),

('Sneha Patel','sneha3@gmail.com','sneha3','1234','USER','9876543203','MECHANICAL','1003',0,1,0),

('Priya Singh','priya4@gmail.com','priya4','1234','USER','9876543204','CIVIL','1004',50,2,2),

('Karan Mehta','karan5@gmail.com','karan5','1234','USER','9876543205','ELECTRICAL','1005',0,0,0),

('Neha Joshi','neha6@gmail.com','neha6','1234','USER','9876543206','ECE','1006',10,2,1),

('Vikas Yadav','vikas7@gmail.com','vikas7','1234','USER','9876543207','MBA','1007',0,1,0),

('Pooja Nair','pooja8@gmail.com','pooja8','1234','USER','9876543208','BCA','1008',30,2,1),

('Rahul Das','rahul9@gmail.com','rahul9','1234','USER','9876543209','MCA','1009',0,2,0),

('Anjali Roy','anjali10@gmail.com','anjali10','1234','USER','9876543210','CSE','1010',15,2,1),

('Deepak Kumar','deepak11@gmail.com','deepak11','1234','USER','9876543211','IT','1011',0,1,0),

('Simran Kaur','simran12@gmail.com','simran12','1234','USER','9876543212','MECHANICAL','1012',25,2,2),

('Arjun Rao','arjun13@gmail.com','arjun13','1234','USER','9876543213','CIVIL','1013',0,0,0),

('Kavita Shah','kavita14@gmail.com','kavita14','1234','USER','9876543214','ELECTRICAL','1014',5,2,1),

('Nitin Jain','nitin15@gmail.com','nitin15','1234','USER','9876543215','ECE','1015',0,2,0),

('Meera Iyer','meera16@gmail.com','meera16','1234','USER','9876543216','MBA','1016',40,2,2),

('Suresh Pillai','suresh17@gmail.com','suresh17','1234','USER','9876543217','BCA','1017',0,1,0),

('Ritika Sen','ritika18@gmail.com','ritika18','1234','USER','9876543218','MCA','1018',12,2,1),

('Yash Malhotra','yash19@gmail.com','yash19','1234','USER','9876543219','CSE','1019',0,2,0),

('Divya Kapoor','divya20@gmail.com','divya20','1234','USER','9876543220','IT','1020',60,2,2),

('Harsh Gupta','harsh21@gmail.com','harsh21','1234','USER','9876543221','MECHANICAL','1021',0,0,0),

('Aisha Khan','aisha22@gmail.com','aisha22','1234','USER','9876543222','CIVIL','1022',18,2,1),

('Mohit Arora','mohit23@gmail.com','mohit23','1234','USER','9876543223','ELECTRICAL','1023',0,1,0),

('Tanvi Desai','tanvi24@gmail.com','tanvi24','1234','USER','9876543224','ECE','1024',22,2,1),

('Ramesh Patil','ramesh25@gmail.com','ramesh25','1234','USER','9876543225','MBA','1025',0,2,0),

('Shreya Bose','shreya26@gmail.com','shreya26','1234','USER','9876543226','BCA','1026',35,2,2),

('Aditya Mishra','aditya27@gmail.com','aditya27','1234','USER','9876543227','MCA','1027',0,1,0),

('Nisha Thomas','nisha28@gmail.com','nisha28','1234','USER','9876543228','CSE','1028',8,2,1),

('Varun Saxena','varun29@gmail.com','varun29','1234','USER','9876543229','IT','1029',0,0,0),

('Payal Chawla','payal30@gmail.com','payal30','1234','USER','9876543230','MECHANICAL','1030',45,2,2),

('Akash Reddy','akash31@gmail.com','akash31','1234','USER','9876543231','CIVIL','1031',0,2,0),

('Komal Bhatt','komal32@gmail.com','komal32','1234','USER','9876543232','ELECTRICAL','1032',16,2,1),

('Sahil Oberoi','sahil33@gmail.com','sahil33','1234','USER','9876543233','ECE','1033',0,1,0),

('Riya Menon','riya34@gmail.com','riya34','1234','USER','9876543234','MBA','1034',28,2,1),

('Manish Tiwari','manish35@gmail.com','manish35','1234','USER','9876543235','BCA','1035',0,2,0),

('Ishita Paul','ishita36@gmail.com','ishita36','1234','USER','9876543236','MCA','1036',55,2,2),

('Abhishek Soni','abhishek37@gmail.com','abhishek37','1234','USER','9876543237','CSE','1037',0,0,0),

('Sanya Arora','sanya38@gmail.com','sanya38','1234','USER','9876543238','IT','1038',9,2,1),

('Gaurav Bansal','gaurav39@gmail.com','gaurav39','1234','USER','9876543239','MECHANICAL','1039',0,1,0),

('Muskan Ali','muskan40@gmail.com','muskan40','1234','USER','9876543240','CIVIL','1040',32,2,2),

('Rajesh Khanna','rajesh41@gmail.com','rajesh41','1234','USER','9876543241','ELECTRICAL','1041',0,2,0),

('Pallavi Jain','pallavi42@gmail.com','pallavi42','1234','USER','9876543242','ECE','1042',14,2,1),

('Naveen Rao','naveen43@gmail.com','naveen43','1234','USER','9876543243','MBA','1043',0,1,0),

('Bhavna Gupta','bhavna44@gmail.com','bhavna44','1234','USER','9876543244','BCA','1044',26,2,1),

('Chirag Modi','chirag45@gmail.com','chirag45','1234','USER','9876543245','MCA','1045',0,2,0),

('Saloni Dixit','saloni46@gmail.com','saloni46','1234','USER','9876543246','CSE','1046',48,2,2),

('Tarun Gill','tarun47@gmail.com','tarun47','1234','USER','9876543247','IT','1047',0,0,0),

('Heena Joshi','heena48@gmail.com','heena48','1234','USER','9876543248','MECHANICAL','1048',11,2,1),

('Lokesh Yogi','lokesh49@gmail.com','lokesh49','1234','USER','9876543249','CIVIL','1049',0,1,0),

('Ananya Seth','ananya50@gmail.com','ananya50','1234','USER','9876543250','ELECTRICAL','1050',38,2,2);



CREATE TABLE books (

    book_id INT PRIMARY KEY AUTO_INCREMENT,

    title VARCHAR(200) NOT NULL,

    author VARCHAR(100) NOT NULL,

    category VARCHAR(100),

    quantity INT DEFAULT 0,

    available_quantity INT DEFAULT 0,

    shelf_number VARCHAR(20)
);

INSERT INTO books
(title, author, category, quantity, available_quantity, shelf_number)

VALUES

-- CSE
('Java Programming','James Gosling','CSE',5,5,'A1'),
('Data Structures Using C','Mark Allen','CSE',4,4,'A2'),
('Operating System Concepts','Galvin','CSE',6,6,'A3'),
('Computer Networks','Andrew Tanenbaum','CSE',5,5,'A4'),
('Database Management System','Korth','CSE',7,7,'A5'),

-- IT
('Cloud Computing','Raj Kumar','IT',4,4,'B1'),
('Cyber Security Basics','William Stallings','IT',5,5,'B2'),
('Web Technologies','Deitel','IT',6,6,'B3'),
('Software Engineering','Ian Sommerville','IT',5,5,'B4'),
('Linux Administration','Richard Blum','IT',3,3,'B5'),

-- MECHANICAL
('Thermodynamics','PK Nag','MECHANICAL',6,6,'C1'),
('Fluid Mechanics','RK Rajput','MECHANICAL',5,5,'C2'),
('Theory of Machines','SS Rattan','MECHANICAL',4,4,'C3'),
('Machine Design','VB Bhandari','MECHANICAL',7,7,'C4'),
('Engineering Mechanics','Beer Johnston','MECHANICAL',5,5,'C5'),

-- ELECTRICAL
('Electrical Machines','BL Theraja','ELECTRICAL',5,5,'D1'),
('Power Systems','CL Wadhwa','ELECTRICAL',4,4,'D2'),
('Control Systems','Nagrath Gopal','ELECTRICAL',6,6,'D3'),
('Circuit Theory','Hayt Kemmerly','ELECTRICAL',5,5,'D4'),
('Electrical Measurements','AK Sawhney','ELECTRICAL',3,3,'D5'),

-- CIVIL
('Strength of Materials','RS Khurmi','CIVIL',5,5,'E1'),
('Surveying','BC Punmia','CIVIL',4,4,'E2'),
('Geotechnical Engineering','GK Duggal','CIVIL',5,5,'E3'),
('Transportation Engineering','Khanna Justo','CIVIL',6,6,'E4'),
('Structural Analysis','Reddy','CIVIL',5,5,'E5'),

-- ECE
('Digital Electronics','Morris Mano','ECE',6,6,'F1'),
('Analog Circuits','Sedra Smith','ECE',4,4,'F2'),
('Communication Systems','Simon Haykin','ECE',5,5,'F3'),
('Microprocessors','Ramesh Gaonkar','ECE',5,5,'F4'),
('Signals and Systems','Oppenheim','ECE',4,4,'F5'),

-- MBA
('Principles of Management','Koontz','MBA',6,6,'G1'),
('Financial Accounting','TS Reddy','MBA',5,5,'G2'),
('Marketing Management','Philip Kotler','MBA',7,7,'G3'),
('Human Resource Management','Gary Dessler','MBA',4,4,'G4'),
('Business Economics','Pindyck','MBA',5,5,'G5'),

-- BCA
('Programming in C','Yashwant Kanetkar','BCA',6,6,'H1'),
('Java Fundamentals','Herbert Schildt','BCA',5,5,'H2'),
('Computer Graphics','Hearn Baker','BCA',4,4,'H3'),
('Internet Technologies','Kogent','BCA',5,5,'H4'),
('Python Programming','Guido Rossum','BCA',6,6,'H5'),

-- MCA
('Advanced Java','Herbert Schildt','MCA',5,5,'I1'),
('Artificial Intelligence','Elaine Rich','MCA',4,4,'I2'),
('Machine Learning','Tom Mitchell','MCA',6,6,'I3'),
('Compiler Design','Aho Ullman','MCA',5,5,'I4'),
('Data Mining','Jiawei Han','MCA',4,4,'I5'),

-- GENERAL / COMMON
('Engineering Mathematics','BS Grewal','GENERAL',10,10,'J1'),
('Engineering Physics','HC Verma','GENERAL',8,8,'J2'),
('Engineering Chemistry','Jain Jain','GENERAL',7,7,'J3'),
('Environmental Studies','Anubha Kaushik','GENERAL',5,5,'J4'),
('Soft Skills','Alex K','GENERAL',6,6,'J5'),

-- EXTRA BOOKS
('Discrete Mathematics','Kenneth Rosen','CSE',5,5,'K1'),
('Big Data Analytics','Vikram Pudi','IT',4,4,'K2'),
('Robotics Engineering','Mittal Nagrath','MECHANICAL',3,3,'K3'),
('Renewable Energy Systems','GDN Raju','ELECTRICAL',5,5,'K4'),
('Concrete Technology','MS Shetty','CIVIL',6,6,'K5');

SELECT * FROM library_db.books;

CREATE TABLE issued_books (
    issue_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    book_id INT,
    issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    return_date DATE,
    status VARCHAR(20)
);
SELECT * FROM library_db.issued_books;
