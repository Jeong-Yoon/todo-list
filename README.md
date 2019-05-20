# todo-list
### 스프링 부트를 이용한 todo list

## 빌드 환경
- jdk 1.8
- maven 3.5.2

## 빌드 및 실행 방법
프로젝트를 클론합니다.
```
git clone https://github.com/Jeong-Yoon/todo-list.git
```

프로젝트 폴더로 이동합니다.
```
cd todo-list
```

maven으로 프로젝트를 빌드합니다.(test는 건너뜁니다)
```
mvn package -Dmaven.test.skip=true
```

jar 파일을 실행 시킵니다.
```
java -jar target/todo-list-0.0.1-SNAPSHOT.jar
```

## 기능
1. TODO 추가
- '추가'버튼을 눌러 새로운 TODO를 등록할 수 있습니다.
- 마감 기한은 오늘날짜부터 그 이후로 선택이 가능합니다.
- 우선순위는 HIGH, MEDIUM, LOW 중 선택할 수 있습니다.

2. TODO 삭제
- x 표시를 눌러 TODO를 삭제할 수 있습니다.
