-- Sets the timezone for CURRENT_TIMESTAMP
ALTER SESSION SET TIME_ZONE = 'Asia/Singapore';

INSERT INTO AUTHOR (NAME, BIRTHDAY, CREATED_DATE) VALUES ('Banana', TO_DATE('1998-07-23', 'YYYY-MM-DD'), SYSDATE);
INSERT INTO AUTHOR (NAME, BIRTHDAY, CREATED_DATE) VALUES ('Apple', TO_DATE('1998-07-23', 'YYYY-MM-DD'), SYSDATE);
INSERT INTO AUTHOR (NAME, BIRTHDAY, CREATED_DATE) VALUES ('Pear', TO_DATE('1998-07-23', 'YYYY-MM-DD'), SYSDATE);
INSERT INTO AUTHOR (NAME, BIRTHDAY, CREATED_DATE) VALUES ('Orange', TO_DATE('1998-07-23', 'YYYY-MM-DD'), SYSDATE);
INSERT INTO AUTHOR (NAME, BIRTHDAY, CREATED_DATE) VALUES ('Papaya', TO_DATE('1998-07-23', 'YYYY-MM-DD'), SYSDATE);

INSERT INTO BOOK (ISBN, TITLE, AUTHORS, YEAR, PRICE, GENRE, CREATED_DATE, MODIFIED_DATE) VALUES ('ISBN1', 'Book A', '#1#2#', 2000, 500.05, 'Art', SYSDATE, SYSDATE);
INSERT INTO BOOK (ISBN, TITLE, AUTHORS, YEAR, PRICE, GENRE, CREATED_DATE, MODIFIED_DATE) VALUES ('ISBN2', 'Book A', '#3#4#', 2001, 500.05, 'Science', SYSDATE, SYSDATE);
INSERT INTO BOOK (ISBN, TITLE, AUTHORS, YEAR, PRICE, GENRE, CREATED_DATE, MODIFIED_DATE) VALUES ('ISBN3', 'Book B', '#1#', 2000, 500.05, 'Art', SYSDATE, SYSDATE);
INSERT INTO BOOK (ISBN, TITLE, AUTHORS, YEAR, PRICE, GENRE, CREATED_DATE, MODIFIED_DATE) VALUES ('ISBN4', 'Book C', '#3#', 2000, 500.05, 'Art', SYSDATE, SYSDATE);
INSERT INTO BOOK (ISBN, TITLE, AUTHORS, YEAR, PRICE, GENRE, CREATED_DATE, MODIFIED_DATE) VALUES ('ISBN5', 'Book D', '#12#', 2000, 500.05, 'Art', SYSDATE, SYSDATE);
INSERT INTO BOOK (ISBN, TITLE, AUTHORS, YEAR, PRICE, GENRE, CREATED_DATE, MODIFIED_DATE) VALUES ('ISBN6', 'Book E', '#4#', 2000, 500.05, 'Art', SYSDATE, SYSDATE);
INSERT INTO BOOK (ISBN, TITLE, AUTHORS, YEAR, PRICE, GENRE, CREATED_DATE, MODIFIED_DATE) VALUES ('ISBN7', 'Book F', '#1#2#', 2000, 500.05, 'Art', SYSDATE, SYSDATE);
