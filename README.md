# NiceInfo_PreTask
NICE평가정보 상반기 수시채용 실무면접 사전과제

READ.ME

1.컴퓨터 구성 안내
	
	로컬 빌드시
	
	-window 10 home(os)
	
	-java 1.8.0_231(언어)
	
	-tomcat 8.0.45(서버)
	
	도커 빌드시
	
	-CentOS Linux release 7.7.1908(os)
	
	-java 1.8.0_241(언어)
	
	-tomcat 8.5.51(서버)
	
	React 빌드시

	-window OS

	-node v12.16.1

	-npm 6.13.4
	
	공통
	
	mysql5.7 
	
2.설치 안내(설치가 되어있다는 가정하에 제작)

React.js
 - 로컬 경로 설정
 
  - npm install create-react-app
 
 - 템플릿 자동 생성
 
 - 전달받은 소스 import(덮어쓰기도 무방)
 
 - npm start 로 확인
 

3.사용법(빌드)


	##Window Setting
	
	선행작업(윈도우 환경에 자바, 톰캣 설치된 상황)

	**톰캣 빌드 폴더에 war파일 이동(war파일명은 ROOT.war여야함 변경 불가)
	
	cmd > move ROOT.war 톰캣설치폴더/webapps

	**톰캣 서버 실행
	
	cmd > cd 톰캣설치폴더/bin
	
	cmd > startup.bat -> 새로운 터미널 창이 열리면서 로그가 올라온다. 프로젝트 관련로그들 여기에서 확인하면됩니다. 프로젝트 자동빌드됩니다.

	**톰캣 서버종료
	
	cmd > cd 톰캣설치폴더/bin
	
	cmd > shutdown.bat


	##Docker Setting
	
	선행작업(윈도우 환경에 도커 설치된 상황, 도커 터미널에 접근 가능한 상황)


	**레파지토리에서 images 다운
	
	docker pull artistdeveloper/apicall:4


	**컨테이너 생성
	
	docker run -i -t -d --dns=8.8.8.8 --name apiCallProject artistdeveloper/apicall:4


	**컨테이너 터미널 접속
	
	docker exec -it apiCallProject /bin/bash 


	**톰캣 서버시작 
	
	cd /usr/local/tomcat/apache-tomcat-8.5.51/bin/startup.sh -> 프로젝트 자동빌드됩니다.


	**톰캣 로그출력
	
	tail -f /usr/local/tomcat/apache-tomcat-8.5.51/logs/catalina.out


	**톰캣 서버종료
	
	cd /usr/local/tomcat/apache-tomcat-8.5.51/bin/shutdown.sh


4.파일 메니페스트(파일목록포함)
	
	ROOT.war
	
5.배포자 및 프로그래머의 연락처 정보

	김영인(youngin0108@gmail.com)


