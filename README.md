# NiceInfo_PreTask
NICE평가정보 상반기 수시채용 실무면접 사전과제

## 빌드 및 실행방법

### 동작 환경
	
**Back-end 로컬 빌드**
	
	-window 10 home(os)
	
	-java 1.8.0_231(언어)
	
	-tomcat 8.0.45(서버)
	
**Back-end 도커 빌드**
	
	-CentOS Linux release 7.7.1908(os)
	
	-java 1.8.0_241(언어)
	
	-tomcat 8.5.51(서버)
	
**Front-end(React) 빌드**

	-window OS

	-node v12.16.1

	-npm 6.13.4
	
**공통**
	
	-mysql5.7  

- - -

### Back-end 실행 방법


#### 로컬 빌드
	
선행작업(윈도우 환경에 자바, 톰캣 설치된 상황)

**war 파일 리빌드 시, eclipse 등을 통해 war 파일 생성(현재 Root.war 파일 넣어둔 상태)**

**톰캣 빌드 폴더에 war파일 이동(war파일명은 ROOT.war여야함 변경 불가)**
	
	cmd > move ROOT.war 톰캣설치폴더/webapps

**톰캣 서버 실행**
	
	cmd > cd 톰캣설치폴더/bin
	
	cmd > startup.bat -> 프로젝트 자동 빌드. 로그 확인 가능.

**톰캣 서버종료**
	
	cmd > cd 톰캣설치폴더/bin
	
	cmd > shutdown.bat


#### Docker Setting
	
선행작업(윈도우 환경에 도커 설치된 상황, 도커 터미널에 접근 가능한 상황)

**DockerHub에서 image 다운로드(artistdeveloper docker 계정에 업로드 상태)**
	
	docker pull artistdeveloper/apicall:4

**컨테이너 생성**
	
	docker run -i -t -d --dns=8.8.8.8 --name apiCallProject artistdeveloper/apicall:4

**컨테이너 터미널 접속**
	
	docker exec -it apiCallProject /bin/bash 

**톰캣 서버시작**
	
	./usr/local/tomcat/apache-tomcat-8.5.51/bin/startup.sh -> 프로젝트 자동빌드

**톰캣 로그출력 시**
	
	tail -f /usr/local/tomcat/apache-tomcat-8.5.51/logs/catalina.out

**톰캣 서버종료**
	
	cd /usr/local/tomcat/apache-tomcat-8.5.51/bin/shutdown.sh

**파일 매니페스트 : ROOT.war**
	
- - -

### Front-end 실행 방법

	- 로컬 경로 설정
 
  	- npm install create-react-app(템플릿 자동 생성)
 
 	- source codes import
 
 	- npm start
 
- - -

### 개발자

	김영인(youngin0108@gmail.com)


