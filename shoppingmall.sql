create table Products(
proId varchar(30),
proName varchar(30),
unitPrice Integer,
description varchar(300),
manufacturer varchar(30),
category varchar(30),
noOfStock Integer,
fileName varchar(20));
drop table Products;
select * from Products;
select * from Members2;
delete from Products where proId=5;
insert into Products (proId,proName,unitPrice,description,manufacturer,category, noOfStock,fileName)
values('1','아이패드 프로',1000000,'차원이 다른 성능을 선사하는 Apple M1칩! ProMotion, True Tone, P3의 넓은 색 영역을 갖춘 11형 Liquid Retina 디스플레이','Apple','iPad',5,'pro.jpg'),
('2','아이패드 에어',800000,'True Tone,P3의 넓은 색 영역을 갖춘 10.9형 Liquid Retina 디스플레이! Neural Engine을 탑재한 A14 Bionic 칩','Apple','iPad',1,'air.jpg'),
('3','아이패드 보급형',500000,'True Tone기술을 탑재해 놀라운 디테일과 생생한 컬러를 자랑하는 10.2형 Retina 디스플레이! 모든 작업에 더욱 탁월한 반응성을 더해주는 Neural Engine 탑재 A13 Bionic 칩','Apple','iPad',2,'pad.jpg'),
('4','아이패드 미니',700000,'True Tone,P3의 넓은 색 영역을 갖춘 놀랍도록 선명한 8.3형 Liquid Retina 디스플레이! 놀라운 성능과 온종일 가는 배터리를 선사하는 A15 Bionic 칩','Apple','iPad',3,'mini.jpg')
;

create table Board(
boardNo Integer primary key auto_increment,
userId varchar(30),
title varchar(40),
content varchar(500),
regDate datetime default current_timestamp,
hit Integer);
 
 drop table board;
 
 desc board;
select * from Board;
select count(*) from Board;

insert into Board (userId,title,content,hit) values ('123','안녕하세요?','반가워요 저는 운영자에요',0);
insert into Board (userId,title,content,hit) values ('000','하이루','반가워요 저는 운영자가 아니에요',0);
update board set hit=hit+1 where boardno =1;

update Board set title='1빠', content='1등!' where boardNo=1;