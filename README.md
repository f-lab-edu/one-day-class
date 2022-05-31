# :bookmark_tabs: **목차**
1. [프로젝트 개요](#books-프로젝트-개요)
2. [프로젝트 기술 스택](#satellite-프로젝트-기술-스택)
3. [Use Case / 유저 시나리오](#computer-use-case--유저-시나리오)
4. [Flow Chart](#pushpin-flow-chart)
5. [기능 구조도](#hammer-기능-구조도)
6. [메뉴 구조도](#flags-메뉴-구조도)
7. [서버 구조도](#computer-서버-구조도)
8. [ERD](#pencil2-erd)
9. [프로토 타입](#iphone-프로토-타입)

# **:books: 프로젝트 개요**
본 프로젝트에서는 원데이 클래스 서비스를 제공하는 시스템을 개발합니다. 원데이 클래스 서비스는 기본적으로 HOST가 클래스를 개설하면 GUEST가 가능한 일자에 예약(신청)하는 서비스입니다. 원데이 클래스란 하루 몇 시간 동안 일회성으로 이뤄지는 수업을 말하며 자신이 관심 있는 분야를 직접 선택해서 체험해볼 수 있다는 장점이 있습니다.

원데이 클래스 서비스를 이용하는 사용자는 GUEST, HOST, ADMIN으로 나뉩니다. GUEST와 HOST는 일반 회원으로 서비스를 이용하기 위해 회원가입이 필요하며, ADMIN은 관리자로 관리자 계정을 통해서 서비스를 관리하는 역할을 수행합니다. GUEST는 원데이 클래스 수강자로서 클래스 신청, 후기 작성, 피드 조회 등의 활동을 수행하며, HOST는 원데이 클래스 개설, 수강생 관리 등의 활동을 수행합니다.

# **:satellite: 프로젝트 기술 스택**
1. JDK 17
2. Spring Boot 2.6.8
3. MyBatis 3
4. MySQL
5. Gradle
6. IntelliJ

# **:computer: Use Case / 유저 시나리오**

몇 가지 대표 케이스만 기입되어 있습니다. 모든 케이스를 확인하려면 [WIKI - Use Case / 시나리오](https://github.com/f-lab-edu/one-day-class/wiki/Use-Case---%EC%9C%A0%EC%A0%80-%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4)를 참고해주세요.

## **1. GUEST**

### **USE CASE 6 - 클래스 신청**
1. 유저가 로그인한다.
2. 클래스 목록을 조회한다.
   - 2-1. 카테고리로 조회한다.
      - 2-1-1. 조회하고자 하는 지역을 선택한다.
      - 2-1-2. 최상위 카테고리 목록(카테고리명)과 카테고리 대표 사진이 유저에게 보여진다.
      - 2-1-3. 조회하고자 하는 카테고리를 선택한다.
      - 2-1-4. 선택된 카테고리의 하위 카테고리(카테고리명)들을 보여 준다.
      - 2-1-5. 최하위 카테고리를 선택할 때까지 상기 2-1-2 ~ 2-1-3번의 흐름을 반복한다.
      - 2-1-6. 선택된 최하위 카테고리에 포함된 클래스들의 목록을 보여준다.
      - 2-1-7. 3번 흐름으로 이동한다.
   - 2-2. 클래스명 검색으로 조회한다.
      - 2-2-1. 조회하고자 하는 지역을 선택한다.
      - 2-2-2. 검색어를 입력하고 검색 버튼을 클릭한다.
      - 2-2-3. 해당 지역에 개설돼있고, 해당 검색어를 포함한 클래스 목록이 보여진다.
      - 2-2-4. 3번 흐름으로 이동한다.
3. 상세한 정보를 알고 싶은 클래스 하나를 선택한다.
4. 클래스 상세 정보가 보여진다.
5. 클래스 신청하기 버튼을 클릭한다.
6. 신청 가능한 일자들이 보여지고 신청하고 싶은 일자를 선택한다.
7. 해당 일자에 신청 가능한 시간대들이 보여지고 신청하고 싶은 시간대를 선택한다.
8. 신청하기 버튼을 클릭한다.
   - 8-1. 신청을 성공하면 신청이 완료되었다는 메세지와 함께 신청이 완료된다.
   - 8-2. 5~7번 흐름 사이에 정원이 가득차면 정원이 가득 찼다는 메세지와 함께 신청이 실패하고 4번 흐름으로 이동한다.

## **2. HOST**

### **USE CASE 1 - HOST 회원가입**
1. 로그인 화면에서 HOST로 가입을 선택한다.
2. HOST용 회원가입 폼이 보여진다.
3. 아이디를 입력 후 중복확인 버튼을 클릭한다.
   - 3-1. 존재하는 아이디가 있으면 중복 아이디 존재 메세지를 응답한다.
   - 3-2. 존재하는 아이디가 없으면 사용 가능 메세지를 응답한다.
4. 비밀번호와 검증 비밀번호를 입력한다.
5. 전화번호를 입력 후 인증번호 발송 버튼을 클릭한다.
6. 유저는 3분 내에 수신한 인증번호를 입력하고 인증하기 버튼을 클릭한다.
7. 유저의 생년월일을 입력한다.
8. 주 분야(카테고리)를 선택한다.
9. 해당 분야의 전문가임을 입증할 수 있는 서류를 첨부한다.
10. 회원가입 버튼을 클릭하면 HOST 회원가입을 완료한다.
11. 로그인 화면으로 이동한다.
12. 관리자 승인 이후 회원가입이 완료된다.

### **USE CASE 3 - 클래스 개설**
1. 유저가 HOST로 로그인한다.
2. 클래스 관리 탭으로 이동한다.
3. 개설중인 클래스 버튼을 클릭한다.
4. 개설할 클래스의 카테고리(대분류)를 선택한다.
5. 개설할 클래스의 카테고리(소분류)를 선택한다.
6. 클래스 정보를 입력한다.
7. 클래스 개설 완료 버튼을 클릭한다.
   - 7-1. 현재 HOST가 개설중인 다른 클래스와 시간대가 겹치면 겹치는 클래스명과 일자, 시간대와 함께 오류 메세지를 응답한다.
   - 7-2. 클래스 최대 개설 가능 개수를 초과하여 개설하고자 하는 경우에는 오류 메세지를 응답한다.
8. 클래스 개설을 완료한다.

## **3. ADMIN**
### **USE CASE 1 - HOST 가입 심사**
1. ADMIN으로 로그인한다.
2. 회원 관리 탭으로 이동한다.
3. HOST 전문분야 관리 탭으로 이동한다.
4. 전문분야 카테고리(대분류) 신규 등록을 원하는 HOST를 선택한다.
5. HOST가 작성한 신청서와 서류를 확인한다.
6. 해당 HOST의 전문 분야를 심사한다.
   - 6-1. 승인한다.
   - 6-2. 거절한다.
7. HOST 전문 분야 심사를 완료한다.
8. 심사 결과를 HOST에게 전송한다.

### **USE CASE 3 - 대분류 카테고리(전문 분야) 등록**
1. ADMIN으로 로그인한다.
2. 카테고리 관리 탭으로 이동한다.
3. 카테고리(대분류) 관리 버튼을 클릭한다.
4. 카테고리(대분류) 목록이 관리자에게 보여진다.
5. 카테고리(대분류) 추가 버튼을 클릭한다.
6. 등록하고자 하는 카테고리명, 카테고리 설명, 카테고리 대표 사진을 입력하고 등록 버튼을 클릭한다.
7. 카테고리명 중복 여부를 체크한다.
   - 7-1. 이미 존재하는 카테고리명이면 존재하는 카테고리명이라는 메세지를 응답하고 6번 흐름으로 이동한다.
   - 7-2. 이외에는 8번 흐름으로 이동한다.
8. 신규 카테고리가 등록되고 이를 포함한 카테고리 목록이 관리자에게 보여진다.

# **:pushpin: Flow Chart**

몇 가지 대표 케이스만 기입되어 있습니다. 모든 케이스를 확인하려면 [WIKI - Flow Chart](https://github.com/f-lab-edu/one-day-class/wiki/Flow-Chart)를 참고해주세요.

## **1. GUEST**
![클래스 신청](https://user-images.githubusercontent.com/57613635/168810165-929e5df9-dd53-4334-9623-f3f78cd77509.png)

## **2. HOST**
![HOST 회원가입](https://user-images.githubusercontent.com/57613635/168810179-c0079e8c-b2ac-4f7b-b5a9-150d8b279208.png)

---
![클래스 개설](https://user-images.githubusercontent.com/57613635/168810191-add69094-f24c-49f1-bd1a-57d9e8a1a07a.png)


## **3. ADMIN**
![HOST 가입 심사](https://user-images.githubusercontent.com/57613635/168810213-c6adc674-f0eb-4c01-a78f-e60f3224aad8.png)

# **:hammer: 기능 구조도**
기능 구조도는 [WIKI - 기능 구조도](https://github.com/f-lab-edu/one-day-class/wiki/%EA%B8%B0%EB%8A%A5-%EA%B5%AC%EC%A1%B0%EB%8F%84)를 참고해주세요.

# **:flags: 메뉴 구조도**
![GUEST 메뉴 구조도](https://user-images.githubusercontent.com/57613635/169255926-65137500-4c08-4729-a0ea-8da86edb832a.png)

---
![HOST 메뉴 구조도](https://user-images.githubusercontent.com/57613635/169255955-b03baa01-5344-482f-b0ee-e72d97d38081.png)

---
![ADMIN 메뉴 구조도](https://user-images.githubusercontent.com/57613635/169255988-b05b3298-ef82-4ed6-9a7d-627d99d1f7e1.png)

# **:computer: 서버 구조도**
(TODO)

# **:pencil2: ERD**
![ERD](https://user-images.githubusercontent.com/57613635/170429787-3864443d-200b-4a06-ac93-c7dc5ed52d60.png)

# **:iphone: 프로토 타입**
백엔드 개발에 집중하기 위해 프론트엔드는 카카오 오븐을 활용해 디자인하는 정도로 마무리하였습니다.

아래 URL에서 UI 기획안을 확인하실 수 있습니다.

[UI 기획안 보기](https://ovenapp.io/view/L1bPO5539pakldT4p9wsn8xBqcgV2Ys3/)

## **GUEST**
![02_GUEST 회원가입](https://user-images.githubusercontent.com/57613635/169477991-e8a61cbc-c11a-479e-a4ac-1173d2104072.png)
![04_카테고리 목록](https://user-images.githubusercontent.com/57613635/169478286-5f4d0c5c-5df1-4722-a52b-6fc3c30ccaf5.png)
![05_클래스 목록](https://user-images.githubusercontent.com/57613635/169478290-3d333412-e268-4e8f-8ce2-5690c8d29528.png)
![06_검색한 클래스 목록](https://user-images.githubusercontent.com/57613635/169478295-69df2832-d9e8-48df-a008-c9fb29a2e3ce.png)
![07_클래스 상세](https://user-images.githubusercontent.com/57613635/169478298-9626ff49-5be7-4f41-aa23-2e19db6b2b40.png)
![08_클래스 신청](https://user-images.githubusercontent.com/57613635/169478300-0e3a887b-bca3-4120-a672-a0476a5581eb.png)
![09_클래스 신청 완료](https://user-images.githubusercontent.com/57613635/169478304-17f229be-14ba-4bdc-a2c3-a89e89db7f49.png)
![10_피드](https://user-images.githubusercontent.com/57613635/169478307-0ce3c369-106d-4223-aab6-b9c319311314.png)
![12_GUEST 마이 페이지](https://user-images.githubusercontent.com/57613635/169478780-6eb0861e-6cf3-41a1-a8f5-3ef5dfbd6a47.png)
![13_수강 예정 클래스](https://user-images.githubusercontent.com/57613635/169478782-3f9935a3-65e0-4e2e-a3b7-83738870eda1.png)
![14_수강 완료 클래스](https://user-images.githubusercontent.com/57613635/169478785-4454432f-8794-4ae7-b35e-4f728a052f21.png)
![15_일반 후기 작성](https://user-images.githubusercontent.com/57613635/169478787-49e967ad-021b-45a5-9d00-69f69cc9383c.png)
![16_사진 후기 작성](https://user-images.githubusercontent.com/57613635/169478789-6bab40d1-90e6-4154-aa04-361bac7a16c2.png)

## **HOST**
![03_HOST 회원가입](https://user-images.githubusercontent.com/57613635/169477997-35bce286-edd3-4ced-9ec6-98d0905a277d.png)
![21_HOST 클래스 관리 탭](https://user-images.githubusercontent.com/57613635/169479114-736d9137-b316-440d-be07-bdadbe70ad1c.png)
![22_개설중인 클래스](https://user-images.githubusercontent.com/57613635/169479118-c4bb693b-454f-4d00-a8a5-57a21815faae.png)
![23_클래스 수정](https://user-images.githubusercontent.com/57613635/169479122-401f90be-ce58-4e2c-9162-5a58b1541d29.png)
![24_클래스 폐지](https://user-images.githubusercontent.com/57613635/169479125-4ca3e66c-c36c-4ea6-83a9-38fddc61202e.png)
![25_마감된 클래스](https://user-images.githubusercontent.com/57613635/169479127-ff47e11f-963e-49df-a289-d2cecbc4575f.png)
![26_클래스 개설](https://user-images.githubusercontent.com/57613635/169479128-e302d0e4-fc81-4fad-bfdd-e9b00a838899.png)
![27_카테고리(소분류) 신청 내역](https://user-images.githubusercontent.com/57613635/169479134-7bfc4b31-77f3-43d7-a408-158e48ec7bcc.png)
![28_카테고리(소분류) 신청](https://user-images.githubusercontent.com/57613635/169479137-8623efd2-dc54-40bf-985b-869819d8b855.png)
![29_HOST 마이 페이지](https://user-images.githubusercontent.com/57613635/169479139-676774b8-5054-464d-bc11-d2350cc8f393.png)
![30_전문분야 심사 신청](https://user-images.githubusercontent.com/57613635/169479140-997c0a94-dca0-4a4a-b3ed-35185c67e57d.png)

## **GUEST, HOST 공통**
![01_로그인](https://user-images.githubusercontent.com/57613635/169477604-7834fe85-2e38-46a8-ab3e-189905bb0505.png)
![11_알림 센터](https://user-images.githubusercontent.com/57613635/169478769-02734567-deb8-4c5c-93df-3245a214bbf5.png)
![17_비밀번호 입력](https://user-images.githubusercontent.com/57613635/169478792-9859a9a1-fea6-49fa-ae32-e97d3a0a241f.png)
![18_회원탈퇴 확인](https://user-images.githubusercontent.com/57613635/169478793-216fe741-96de-498a-87d7-3d00e1c7b5da.png)
![19_회원정보 수정](https://user-images.githubusercontent.com/57613635/169478796-6526d4b9-a92b-41c1-ada1-e0a01be45c1e.png)
![20_비밀번호 변경](https://user-images.githubusercontent.com/57613635/169478799-65796638-de8a-4da5-8a8a-d68142974556.png)

## **ADMIN**
![31_ADMIN 회원 관리 탭](https://user-images.githubusercontent.com/57613635/169479411-5311e860-6a4f-461b-a083-7b7e1d48bf34.png)
![32_HOST 가입 신청 내역](https://user-images.githubusercontent.com/57613635/169479420-14ac8231-b8b8-45aa-98c2-188d07c22efc.png)
![33_HOST 가입 신청 정보](https://user-images.githubusercontent.com/57613635/169479423-7e2d8fca-a2ea-47ba-8dde-509e82c18ebd.png)
![34_HOST 전문분야 신청 내역](https://user-images.githubusercontent.com/57613635/169479428-0f1c741b-d78f-4437-b2ab-d1309f1225e2.png)
![35_ADMIN 카테고리 관리 탭](https://user-images.githubusercontent.com/57613635/169479432-86e5929e-8f2a-4c44-9566-69cd42d28d60.png)
![36_카테고리(대분류) 목록](https://user-images.githubusercontent.com/57613635/169479433-e24d1661-b062-439c-b540-fb59274cd7f6.png)
![37_카테고리(대분류) 등록](https://user-images.githubusercontent.com/57613635/169479435-3d23e091-d5e7-46cf-b9c1-a333f8ff2866.png)
![38_카테고리(소분류) 관리](https://user-images.githubusercontent.com/57613635/169479438-4c12a14e-8360-4e8b-a558-929868672d76.png)


# **브랜치 관리 전략**
## **Git Flow**
(TODO)
