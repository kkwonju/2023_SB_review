# DB 생성
DROP DATABASE IF EXISTS Spring_AM;
CREATE DATABASE Spring_AM;
USE Spring_AM;

# 게시물 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

# 회원 테이블 생성
CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(30) NOT NULL,
    loginPw CHAR(60) NOT NULL,
    `name` CHAR(30) NOT NULL,
    nickname CHAR(30) NOT NULL,
    cellphoneNum CHAR(30) NOT NULL,
    email CHAR(30) NOT NULL,
    authLevel TINYINT(2) NOT NULL DEFAULT 3 COMMENT '권한 레벨 (3=일반, 7=관리자)',
    delStatus TINYINT(1) NOT NULL DEFAULT 0 COMMENT '탈퇴 여부 (0=탈퇴 전, 1=탈퇴 후)',
    delDate DATETIME COMMENT '탈퇴날짜'
);

# 게시물 테이블에 작성자 정보 추가
# Alter table article add column memberId int(10) unsigned not null after `updateDate`;

# 게시물 테스트 데이터 생성
INSERT INTO article 
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
title = '제목 1',
`body` = '내용 1';

INSERT INTO article 
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
title = '제목 2',
`body` = '내용 2';

INSERT INTO article 
SET regDate = NOW(),
updateDate = NOW(),
memberId = 3,
title = '제목 3',
`body` = '내용 3';

# 회원 테스트 데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'admin',
    loginPw = 'pw1',
    `name` = '유재석',
    nickname = '재석',
    cellphoneNum = '010-0000-0000',
    email = 'you@naver.com',
    authLevel = 7;
    
INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'test2',
    loginPw = 'pw2',
    `name` = '박명수',
    nickname = '명수',
    cellphoneNum = '010-1111-1111',
    email = 'su@naver.com';
    
INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'test3',
    loginPw = 'pw3',
    `name` = '정준하',
    nickname = '준하',
    cellphoneNum = '010-2222-2222',
    email = 'ha@naver.com';
    
SELECT * FROM article;

SELECT * FROM `member`;


SELECT LAST_INSERT_ID();