## 코드 merge 방법
이슈 해결 후 다음 방법에 맞춰 merge 진행
 
### 1. git clone
로컬에 본인이 원하는 위치에 develop 브랜치 폴더 클론
 
### 2. feature/#{이슈 번호} 브랜치 생성 및 checkout 후 작업
작업 진행
 
### 3. 테스트(기능 개발 등)가 필요한 경우 테스트코드 작성 후 테스트 실행
 
### 4. commit 및 push
commit 및 push전 확인 사항
* 작성되어 있는 전체 테스트 코드 통과 여부 확인
* 불필요한 개행 및 정렬이나 띄어쓰기 확인
* 사용되지 않는 import문 제거
 
위 사항들 확인 후 commit 및 push
 
**예시**</br>
![image](https://github.com/TeamChumaengi/chumaengi/assets/97610532/1dc8e6dd-e6a4-476b-bf8a-6b1d3131f612)


### 4. PR Request 생성
* PR 템플릿에 맞춰서 작성
* assignment, label 선택했는지 확인
* project 선택이 되어있다면 제거 -> BackLog에 이슈만 올라가도록 하기 위함 ( BackLog에서 이슈 통해서 pr확인 가능하기 때문에 별도로 올리기X )

**템플릿 작성예시**</br>
![image](https://github.com/TeamChumaengi/chumaengi/assets/97610532/6fe0d974-76cf-4b77-9fc7-4d27edca69d7)

* pr이 올라가면 리뷰대상이 되니 pr말고 해당 이슈로 가서 project status를 Reviewing으로 변경

### 5. Merge
코드 리뷰 후 Merge</br>
**코드 리뷰**
* 코드에서 문제, 불필요한 import문 등의 수정 사항에 대한 리뷰
* 궁금한 코드에 대한 질문

**리뷰사항에 대한 수정이 모두 끝나면 merge 진행**</br>
squash and merge로 선택해서 진행</br>
다른 merge 진행시 불필요한 커밋 메시지 발생

![image](https://github.com/TeamChumaengi/chumaengi/assets/97610532/e6df2045-aeb4-42ba-992d-3c34d21c843e)

**develop 커밋 메시지**</br>
[#이슈번호] 커밋메시지규칙에 맞춰서 작성

![image](https://github.com/TeamChumaengi/chumaengi/assets/97610532/832ad45b-9c63-45c9-ada3-3881b9f8f73a)

**merge 후 해당 브랜치 delete**

이슈가 닫히면 해당 이슈로 가서 project status를 Done인지 확인, Done이 아니라면 Done으로 변경 (아래 예시처럼 완료된 이슈가 Done에 나오도록 함)

![image](https://github.com/TeamChumaengi/chumaengi/assets/97610532/7b6af2ff-8b52-44f6-9389-ab5923818414)

