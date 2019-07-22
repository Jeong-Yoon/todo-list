-- priority
INSERT INTO priority(name) VALUES('HIGH');
INSERT INTO priority(name) VALUES('MEDIUM');
INSERT INTO priority(name) VALUES('LOW');

-- task
INSERT INTO task(title,content,reg_date,end_date,priority_id) VALUES('Java의 정석 반납','도서관에 Java의 정석 반납하기','2019-05-19','2019-05-31',1);
INSERT INTO task(title,content,reg_date,end_date,priority_id) VALUES('자료구조론 공부','자료구조론책 150p까지 읽기','2019-05-20','2019-05-31',2);
INSERT INTO task(title,content,reg_date,end_date,priority_id) VALUES('세탁소에서 옷찾기','10시까지 들려서 옷 찾아오기','2019-05-21','2019-05-31',3);
INSERT INTO task(title,content,reg_date,end_date,priority_id) VALUES('DFS 복습하기','하루에 한 문제씩 풀기','2019-05-30','2019-06-11',1);
INSERT INTO task(title,content,reg_date,end_date,priority_id) VALUES('알고리즘 풀기','DP문제 하나씩 풀기','2019-06-10','2019-06-21',2);

