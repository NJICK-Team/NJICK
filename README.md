# Drillo 프로젝트

## 프로젝트 개요

칸반보드 기반의 협업 애플리케이션

## 기능 정의

- 모든 기능들의 필수 항목은 `null`이 될 수 없으며, 만약 `null`값이 들어오게 된다면 `CustomRuntimeExcpeiton` 예외를 발생시킨다.
- 모든 응답은 공통화된 포맷을 제공한다.
- Drillo 프로젝트는 회원가입을 한 사용자만 이용할 수 있다.

#### [회원]

- 사용자는 회원 가입, 회원 정보 수정, 회원 탈퇴, 회원 정보 조회를 할 수 있다.
  - 회원 정보 수정 가능 항목은 닉네임과 비밀번호가 있다.
- 로그인, 로그아웃 기능을 제공한다.
  - 로그인 시 비밀번호 혹은 이메일이 틀린 경우 `CustomRuntimeExceptin` 예외를 발생시킨다.

    
#### [보드]

- 회원은 보드를 생성, 수정, 조회, 삭제를 할 수 있다.
  - 조회의 경우 회원이 생성한 모든 보드를 조회하는 기능과, 참여하고 있는 모든 보드를 조회하는 기능으로 구분된다.
- 보드에 사용자를 초대할 수 있다.
  - 초대장은 이메일을 통해 발송되면, 이메일에는 입장을 위한 OTP가 포함되어 있다. OTP의 유효기간은 보안을 고려하여 2일로 지정되어 있다. 만약 2일이 초과할 경우 다시 초대장을 발급해야 한다.

#### [카드]

- 회원은 하나의 보드에 업무 카드를 생성, 조회, 수정, 삭제, 복수 인원 할당을 할 수 있다.
  - 조회의 경우 카드의 세부 항목과 전체 카드 조회로 구분된다.
- 카드는 업무 상태와 댓글을 포함한다.
  - 상태는 생성, 수정, 삭제, 조회가 가능하고, 업무 카드에 종속된다.
  - 카드에는 댓글을 생성할 수 있다. 댓글 작성자는 작성한 댓글을 수정, 삭제할 수 있다.

## 기술 스택

- JDK 17
- SpringBoot 3.2.2
- Spring MVC
- Swagger 2.3.0
- Lombok
- Jpa
- MySQL 8.3.x
- Redis
- Mail
- JWT
- Slack

## ERD

<img width="700" alt="image" src="https://github.com/NJICK-Team/NJICK/assets/155048724/9acc088e-b9a1-422b-925d-df7889e1ebdb">


## API 명세서

[API 명세서 바로가기](https://teamsparta.notion.site/d1c071addf0e4f378a20c5569b4f511d?v=d22816e294d0412c9714f3a4e3cf29df)

## Member

- 팀장: 남예빈
  - https://github.com/kimpangya?tab=repositories
- 팀원: 장석빈
  - https://github.com/chilichili-97
- 팀원: 임현태
  - https://github.com/jinkshower
- 팀원: 최유라
  - https://github.com/YuniqCoding
- 팀원: 권용수
  - https://ditto-dev.tistory.com/
