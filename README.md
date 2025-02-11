# Board-server

## 2025-01-15
### 수정사항
- username -> name 
- apllication.properties (개인 로컬정보) 깃이그노어에 추가해서 보호

## 2025-01-16
@table(name) 설정으로 postsql에서 충돌 수정

## 2025-01-17
- 리엑트 로그인 회원가입 폼 부분 틀 완성
- 추가 (birthday, firstname, lastName) 추가 
- 그에따라 회원가입 서비스 ,dto 수정

## 2025-01-20 
- Jwt 토큰 인증관련 로직 구현중
- 클라이언트 요청 정상처리 에러 수정
- 로그인서비스 일부 수정
## 2025-02-03
- jwt 토큰을 http-Only 쿠키 방식으로 변경
  (보안상의 이유)
- 로그아웃로직을 구현해서 토큰(쿠키) 만료 패턴 설정
- 리엑트 일부분 수정(로그인,로그아웃)
## 2025-02-10
- 리엑트 리듀서 관련 로직 수정/추가 
- Authenticater 를 통해 사용자 로그인 상태 유지 로직 설게 
## 2025-02-11
- 리엑트 menu , header 부분합침( 가시성,기능추가)
- 전체적인 리팩토링