# sparta-scheduler


---

## 📁 일정 (Schedule)

| 메서드 | 엔드포인트 | 설명 | 요청 바디 예시 |
|--------|-------------|------|----------------|
| POST   | `/`         | 일정 생성 | ```json\n{\n  "title": "제목",\n  "content": "내용",\n  "name": "이름",\n  "password": "password"\n}``` |
| GET    | `/1`        | 단일 일정 조회 (ID = 1) | 없음 |
| GET    | `/`         | 전체 일정 조회 | 없음 |
| PATCH  | `/16`       | 일정 일부 수정 (ID = 16) | ```json\n{\n  "title": "123",\n  "name": "123",\n  "password": "password"\n}``` |
| DELETE | `/1`        | 일정 삭제 (ID = 1) | 없음 |

---

## 👤 사용자 (User)

| 메서드 | 엔드포인트 | 설명 | 요청 바디 예시 |
|--------|-------------|------|----------------|
| POST   | `/`         | 유저 생성 | ```json\n{\n  "name": "홍길동",\n  "email": "abcde@naver.com",\n  "password": "password"\n}``` |
| GET    | `/1`        | 유저 조회 (ID = 1) | 없음 |
| PATCH  | `/1`        | 유저 정보 수정 | ```json\n{\n  "name": "김철수",\n  "password": "password"\n}``` |
| DELETE | `/1`        | 유저 삭제 | ```json\n{\n  "password": "password"\n}``` |

> 📝 유저 연동 시 일정의 `"name"` 필드는 `"userId"` 혹은 UID로 변경 필요


## 🗃️ Database Schema (DDL)

`---

## 🗃️ ERD (Entity Relationship Definition)

### 📅 schedule
| 컬럼명         | 타입           | 제약조건                | 설명       |
|----------------|----------------|--------------------------|------------|
| scheduleId     | INT            | PK                        | 일정 ID     |
| uid            | INT            | FK (user.uid), NOT NULL  | 사용자 ID   |
| name           | VARCHAR(255)   | NOT NULL                 | 작성자명     |
| password       | VARCHAR(255)   | NOT NULL                 | 비밀번호     |
| title          | VARCHAR(255)   | NOT NULL                 | 일정 제목    |
| contents       | VARCHAR(255)   | NOT NULL                 | 일정 내용    |
| createdAt      | DATE           | NOT NULL                 | 생성일 (자동) |
| modifiedAt     | DATE           | NOT NULL                 | 수정일 (자동) |

---

### 💬 comment
| 컬럼명         | 타입           | 제약조건                              | 설명         |
|----------------|----------------|----------------------------------------|--------------|
| commentId      | INT            | PK                                     | 댓글 ID       |
| scheduleId     | INT            | FK (schedule.scheduleId), NOT NULL     | 참조 일정 ID   |
| uid            | INT            | FK (user.uid), NOT NULL                | 사용자 ID     |
| name           | VARCHAR(255)   | NOT NULL                               | 작성자명       |
| password       | VARCHAR(255)   | NOT NULL                               | 비밀번호       |
| contents       | VARCHAR(255)   | NOT NULL                               | 댓글 내용      |
| createdAt      | DATE           | NOT NULL                               | 생성일 (자동)   |
| modifiedAt     | DATE           | NOT NULL                               | 수정일 (자동)   |

---

### 👤 user
| 컬럼명         | 타입           | 제약조건     | 설명     |
|----------------|----------------|--------------|----------|
| uid            | INT            | PK           | 사용자 ID |
| name           | VARCHAR(255)   | NULL         | 사용자 이름 |
| email          | VARCHAR(255)   | NULL         | 이메일    |
| password       | VARCHAR(255)   | NULL         | 비밀번호  |
| createdAt      | DATE           | NULL         | 생성일    |
| modifiedAt     | DATE           | NULL         | 수정일    |
