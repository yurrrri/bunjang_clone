# bunjang_mock_aos_zoe

## 스플래시

<img src="https://user-images.githubusercontent.com/37764504/106917040-34254d00-674b-11eb-8667-ce1a033d3c2d.png" width="270" height="480">

## 1. 로그인 화면

### 뷰
* circle indicator 모양 변경 예정(20210205 현재 커스텀 적용이 안됨)
* EditText가 처음부터 나오는게 아닌 안보였다가 한 항목마다 다 입력했을때 그 다음 EditText가 나오게 드러나게 변경 예정
* 다 입력했을때에만 버튼 활성화되게 변경 예정
* 커서 색깔 변경 예정
* 인증번호 3분 뒤 만료 구현해야함

<img src="https://user-images.githubusercontent.com/37764504/107031348-6095a380-67f5-11eb-9fde-4970b37164e3.png" width="350" height="480">
<img src="https://user-images.githubusercontent.com/37764504/107037763-e407c280-67fe-11eb-87a0-3a4bea05f6ad.png" width="350" height="480">
<img src="https://user-images.githubusercontent.com/37764504/107375005-9b684600-6b2b-11eb-8112-1bf6a1e0a837.png" width="350" height="550">
<img src="https://user-images.githubusercontent.com/37764504/107376709-8ee4ed00-6b2d-11eb-8aa7-68b5ead7a163.png" width="350" height="550">
<img src="https://user-images.githubusercontent.com/37764504/107377050-e4b99500-6b2d-11eb-83a6-5bc5a7a7e884.png" width="350" height="550">
<img src="https://user-images.githubusercontent.com/37764504/107377247-19c5e780-6b2e-11eb-984d-299aea7a3eb4.png" width="350" height="550">

**인증번호가 일치하지 않을 경우 -> 안내 dialog**
<br>
<br>
<img src="https://user-images.githubusercontent.com/37764504/107377479-5c87bf80-6b2e-11eb-90d7-4e218f3226ea.png" width="350" height="550">

**로그인이 아닐경우 -> 회원가입 완료 창**
<br>
<br>
<img src="https://user-images.githubusercontent.com/37764504/107433791-30d7fa00-6b6d-11eb-9866-60b25ae5300d.png" width="350" height="550">


### API

https://dev.h3yon-bunjang.shop/auth
<br>
* post -> body : phoneNumber(폰 번호)
문자 인증번호 발송 API 완료

https://dev.h3yon-bunjang.shop/sign
<br>
* post -> body : 유저 정보
로그인일 시 메인으로 바로 넘어가게끔 완료
회원가입일 시 가입완료 화면으로 넘어간 뒤 메인으로 넘어가게끔 완료
회원가입 API 완료


## 2. 마이샵 - 유저정보 설정

### 뷰

* 현재 커스텀 툴바 적용이 안됨 (20210210)
* 프로필 아래에 text viewpager 적용 필요
* 뒤로가기 버튼 툴바에 추가해야함
* 현재 성별 체크박스 반영이 안됨

<img src="https://user-images.githubusercontent.com/37764504/107572256-5d544a80-6c2f-11eb-8d23-d8a84aa12e32.png" width="350" height="700">
<img src="https://user-images.githubusercontent.com/37764504/107546193-70efb900-6c0f-11eb-8744-59ad7212cf3c.png" width="350" height="700">

**1) 사용자 설정 -> 계정 설정**
<br>
<br>
<img src="https://user-images.githubusercontent.com/37764504/107861557-ebc40880-6e89-11eb-8fb2-366c382c1145.png" width="350" height="700">
<br>
<br>
**계정 설정 -> 성별**
<br>
<br>
<img src="https://user-images.githubusercontent.com/37764504/107873426-df759500-6ef5-11eb-851f-8f73ac31b709.png" width="350" height="700">

### API

https://dev.h3yon-bunjang.shop/users/:userIdx/profile
* 프로필 조회 API 완료(get)
* 프로필 수정 API 완료(patch)

## 3. 상품등록

### 뷰

### API

## 4. 상품조회 
## - 홈 화면

### 뷰

### API

## - 카테고리

### 뷰

### API

## - 검색

### 뷰

### API
