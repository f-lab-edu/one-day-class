CREATE SCHEMA IF NOT EXISTS `one` 
   DEFAULT CHARACTER SET 'utf8mb4';
USE `one` ;

-- user Table Create SQL
CREATE TABLE user
(
    `id`             INT            NOT NULL    AUTO_INCREMENT COMMENT '유저 테이블 식별자', 
    `image_file_id`  INT            NULL        COMMENT '첨부한 심사 서류 이미지', 
    `user_id`        VARCHAR(45)    NOT NULL    COMMENT '유저가 가입 시 설정한 아이디', 
    `password`       VARCHAR(45)    NOT NULL    COMMENT '유저가 설정한 비밀번호', 
    `name`           VARCHAR(45)    NOT NULL    COMMENT '유저 이름', 
    `phone_number`   VARCHAR(45)    NOT NULL    COMMENT '유저가 인증한 휴대폰 번호', 
    `user_type`      VARCHAR(1)     NOT NULL    COMMENT '''G'': Guest, ''H'': Host, ''A'': Admin', 
    `user_status`    VARCHAR(1)     NOT NULL    COMMENT '''0'': 가입 완료, ''1'': 가입 심사 중, ''2'': 회원 탈퇴 중', 
    `create_time`    TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`    TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE user COMMENT '유저';


-- message Table Create SQL
CREATE TABLE message
(
    `id`           INT            NOT NULL    AUTO_INCREMENT COMMENT '메세지 테이블 식별자', 
    `user_id`      INT            NOT NULL    COMMENT '유저 테이블 식별자', 
    `content`      VARCHAR(45)    NOT NULL    COMMENT '알림 메세지에 표시될 내용', 
    `create_time`  TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`  TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE message COMMENT '메세지';


-- recommend Table Create SQL
CREATE TABLE recommend
(
    `id`           INT           NOT NULL    AUTO_INCREMENT COMMENT '추천 테이블 식별자', 
    `user_id`      INT           NOT NULL    COMMENT '유저 테이블 식별자', 
    `review_id`    INT           NOT NULL    COMMENT '리뷰 테이블 식별자', 
    `like_status`  VARCHAR(1)    NOT NULL    COMMENT '''0'': 추천 안함, ''1'': 추천함', 
    `create_time`  TIMESTAMP     NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`  TIMESTAMP     NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE recommend COMMENT '추천';


-- review Table Create SQL
CREATE TABLE review
(
    `id`                INT            NOT NULL    AUTO_INCREMENT COMMENT '리뷰 테이블 식별자', 
    `user_id`           INT            NOT NULL    COMMENT '유저 테이블 식별자(리뷰를 남긴 GUEST 식별자)', 
    `one_day_class_id`  INT            NOT NULL    COMMENT '클래스 테이블 식별자', 
    `image_file_id`     INT            NULL        COMMENT '이미지 파일 테이블 식별자', 
    `content`           VARCHAR(45)    NOT NULL    COMMENT '리뷰 내용', 
    `review_type`       VARCHAR(1)     NOT NULL    COMMENT '''0'': 일반 후기, ''1'': 사진 후기', 
    `rating`            DOUBLE         NOT NULL    COMMENT '리뷰 평점', 
    `create_time`       TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`       TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE review COMMENT '리뷰';


-- comment Table Create SQL
CREATE TABLE comment
(
    `id`           INT            NOT NULL    AUTO_INCREMENT COMMENT '댓글 테이블 식별자', 
    `user_id`      INT            NOT NULL    COMMENT '유저 테이블 식별자', 
    `review_id`    INT            NOT NULL    COMMENT '리뷰 테이블 식별자', 
    `content`      VARCHAR(45)    NOT NULL    COMMENT '댓글 내용', 
    `create_time`  TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`  TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE comment COMMENT '댓글';


-- nested_comment Table Create SQL
CREATE TABLE nested_comment
(
    `id`           INT            NOT NULL    AUTO_INCREMENT COMMENT '대댓글 테이블 식별자', 
    `user_id`      INT            NOT NULL    COMMENT '유저 테이블 식별자', 
    `comment_id`   INT            NOT NULL    COMMENT '댓글 테이블 식별자', 
    `content`      VARCHAR(45)    NOT NULL    COMMENT '대댓글 내용', 
    `create_time`  TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`  TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE nested_comment COMMENT '대댓글';


-- image_file Table Create SQL
CREATE TABLE image_file
(
    `id`            INT            NOT NULL    AUTO_INCREMENT COMMENT '이미지 파일 테이블 식별자', 
    `path`          VARCHAR(45)    NOT NULL    COMMENT '이미지 파일 경로', 
    `name`          VARCHAR(45)    NOT NULL    COMMENT '이미지 파일명', 
    `content_type`  VARCHAR(1)     NOT NULL    COMMENT '''0'': 심사, ''1'': 후기, ''2'': 카테고리, ''3'': 클래스', 
    `create_time`   TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`   TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE image_file COMMENT '이미지 파일';


-- user_big_category Table Create SQL
CREATE TABLE user_big_category
(
    `user_id`          INT          NOT NULL    COMMENT '유저 테이블 식별자', 
    `big_category_id`  INT          NOT NULL    COMMENT '대분류 카테고리 테이블 식별자', 
    `create_time`      TIMESTAMP    NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`      TIMESTAMP    NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (user_id, big_category_id)
) ENGINE = InnoDB;

ALTER TABLE user_big_category COMMENT '유저 대분류 카테고리';


-- big_category Table Create SQL
CREATE TABLE big_category
(
    `id`             INT            NOT NULL    AUTO_INCREMENT COMMENT '대분류 카테고리 테이블 식별자', 
    `image_file_id`  INT            NULL        COMMENT '이미지 파일 테이블 식별자', 
    `name`           VARCHAR(45)    NOT NULL    COMMENT '대분류 카테고리 이름', 
    `detail`         VARCHAR(45)    NOT NULL    COMMENT '대분류 카테고리 설명', 
    `create_time`    TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`    TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE big_category COMMENT '대분류 카테고리';


-- small_category Table Create SQL
CREATE TABLE small_category
(
    `id`               INT            NOT NULL    AUTO_INCREMENT COMMENT '소분류 카테고리 테이블 식별자', 
    `big_category_id`  INT            NOT NULL    COMMENT '대분류 카테고리 테이블 식별자', 
    `name`             VARCHAR(45)    NOT NULL    COMMENT '소분류 카테고리 이름', 
    `create_time`      TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`      TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE small_category COMMENT '소분류 카테고리';


-- address Table Create SQL
CREATE TABLE address
(
    `id`           INT            NOT NULL    AUTO_INCREMENT COMMENT '주소 테이블 식별자', 
    `city`         VARCHAR(45)    NOT NULL    COMMENT '도/시', 
    `town`         VARCHAR(45)    NOT NULL    COMMENT '시/군/구', 
    `create_time`  TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`  TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE address COMMENT '주소';


-- course Table Create SQL
CREATE TABLE course
(
    `id`                 INT            NOT NULL    AUTO_INCREMENT COMMENT '원데이 클래스 테이블 식별자', 
    `user_id`            INT            NOT NULL    COMMENT '유저 테이블 식별자(클래스를 개설한 HOST 식별자)', 
    `small_category_id`  INT            NOT NULL    COMMENT '소분류 카테고리 테이블 식별자', 
    `address_id`         INT            NOT NULL    COMMENT '주소 테이블 식별자(원데이 클래스 주소)', 
    `image_file_id`      INT            NULL        COMMENT '이미지 파일 테이블 식별자', 
    `address_detail`     VARCHAR(45)    NOT NULL    COMMENT '원데이 클래스 상세 주소', 
    `name`               VARCHAR(45)    NOT NULL    COMMENT '원데이 클래스 이름', 
    `material`           VARCHAR(45)    NOT NULL    COMMENT '원데이 클래스 준비물', 
    `capacity`           INT            NOT NULL    COMMENT '원데이 클래스 한 타임 수강 정원', 
    `detail`             VARCHAR(45)    NOT NULL    COMMENT '원데이 클래스 설명', 
    `rating`             DOUBLE         NULL        COMMENT '원데이 클래스 평점', 
    `create_time`        TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`        TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE course COMMENT '원데이 클래스';


-- course_detail Table Create SQL
CREATE TABLE course_detail
(
    `id`             INT            NOT NULL    AUTO_INCREMENT COMMENT '원데이 클래스 상세 테이블 식별자', 
    `course_id`      INT            NOT NULL    COMMENT '원데이 클래스 테이블 식별자', 
    `date`           VARCHAR(45)    NOT NULL    COMMENT '원데이 클래스 개설 일자', 
    `time`           VARCHAR(45)    NOT NULL    COMMENT '원데이 클래스 개설 시간', 
    `course_status`  VARCHAR(1)     NOT NULL    COMMENT '''0'': 개설, ''1'': 폐지', 
    `create_time`    TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`    TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE course_detail COMMENT '원데이 클래스 상세';


-- course_application_history Table Create SQL
CREATE TABLE course_application_history
(
    `id`                  INT           NOT NULL    AUTO_INCREMENT COMMENT '원데이 클래스 신청 내역 테이블 식별자', 
    `course_detail_id`    INT           NOT NULL    COMMENT '원데이 클래스 상세 테이블 식별자', 
    `user_id`             INT           NOT NULL    COMMENT '유저 테이블 식별자(클래스 신청한 GUEST 식별자)', 
    `application_status`  VARCHAR(1)    NOT NULL    COMMENT '''0'': 신청, ''1'': 취소', 
    `create_time`         TIMESTAMP     NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`         TIMESTAMP     NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE course_application_history COMMENT '원데이 클래스 신청 내역';


-- small_category_application_history Table Create SQL
CREATE TABLE small_category_application_history
(
    `id`                  INT            NOT NULL    AUTO_INCREMENT COMMENT '소분류 카테고리 신청 내역 테이블 식별자', 
    `user_id`             INT            NOT NULL    COMMENT '유저 테이블 식별자(신청한 HOST 식별자)', 
    `big_category_id`     INT            NOT NULL    COMMENT '대분류 카테고리 테이블 식별자', 
    `name`                VARCHAR(45)    NOT NULL    COMMENT '소분류 카테고리 이름', 
    `application_status`  VARCHAR(1)     NOT NULL    COMMENT '''0'': 신청, ''1'': 승인, ''2'': 거절', 
    `create_time`         TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`         TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE small_category_application_history COMMENT '소분류 카테고리 신청 내역';


-- big_category_application_history Table Create SQL
CREATE TABLE big_category_application_history
(
    `id`                  INT            NOT NULL    AUTO_INCREMENT COMMENT '대분류 카테고리 신청 내역 테이블 식별자', 
    `user_id`             INT            NOT NULL    COMMENT '유저 테이블 식별자(신청한 HOST 식별자)', 
    `image_file_id`       INT            NULL        COMMENT '이미지 파일 테이블 식별자', 
    `name`                VARCHAR(45)    NOT NULL    COMMENT '대분류 카테고리 이름', 
    `address_detail`      VARCHAR(45)    NOT NULL    COMMENT '대분류 카테고리 설명', 
    `application_status`  VARCHAR(1)     NOT NULL    COMMENT '''0'': 신청, ''1'': 승인, ''2'': 거절', 
    `create_time`         TIMESTAMP      NOT NULL    COMMENT '컬럼 최초 생성 시간', 
    `update_time`         TIMESTAMP      NOT NULL    COMMENT '컬럼 최종 수정 시간', 
     PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE big_category_application_history COMMENT '대분류 카테고리 신청 내역';
