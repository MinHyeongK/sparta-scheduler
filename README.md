# sparta-scheduler


## 📌 Scheduler API 명세

| 분류 | 기능 | 메서드 | 엔드포인트 | Body 예시 |
|------|------|--------|------------|-----------|
| **Schedule** | 일정 생성 | POST | `/` | ```json { "title": "제목", "contents": "내용", "name": "이름", "password": "password" } ``` |
|  | 단건 일정 조회 | GET | `/{scheduleId}` | - |
|  | 전체 일정 조회 | GET | `/?name=이름` | - |
|  | 일정 일부 수정 | PATCH | `/{scheduleId}` | ```json { "title": "123", "name": "123", "password": "password" } ``` |
|  | 일정 삭제 | DELETE | `/{scheduleId}` | ```json { "password": "password" } ``` |
| **User** | 유저 생성 | POST | `/users` | ```json { "userName": "홍길동", "email": "abcde@naver.com", "password": "password" } ``` |
|  | 전체 유저 조회 | GET | `/users` | - |
|  | 단일 유저 조회 | GET | `/users/{userId}` | - |
|  | 유저 수정 | PATCH | `/users/{userId}` | ```json { "userName": "김수한무", "password": "password" } ``` |
|  | 유저 삭제 | DELETE | `/users/{userId}` | ```json { "password": "password" } ``` |
| **Comment** | 댓글 생성 | POST | `/{scheduleId}` | ```json { "contents": "contents", "name": "name", "password": "password", "createdAt": "createdAt", "updatedAt": "updatedAt" } ``` |


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
