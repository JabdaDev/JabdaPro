package com.jabda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/******************************************
 * 여기서 Run as > Spring Boot App으로 실행
 * (Spring Boot App이 없을시 Run as > Java Application 으로 가능) 
 *
 * ItmiController.java
 * jsp를 추가할 경로 : webapp/WEB-INF/views/ 안에 추가 후
 * ItmiController.java에서 RequestMapping 추가해주면 정상적으로 보임
 * 
 * AdminController.java는 관리자페이지 매핑 컨트롤러
 * 
 * 각 페이지마다 중복되는 cdn이 있어서 global.jsp (페이지에는 global.do로 표시)로 수정
 * 
 * 방금 다운받은 파일에서 페이지 head부분에
 * <link href="./css/index.css" rel="stylesheet/>
 * <script src="./js/index.js"></script>
 * 위에 두 문장이 index페이지가 아닌데 있다면 css파일이 변경되었거나 만들어져있어요.
 * (VScode에서 이클립스로 옮길때 주의)
 * css / js / images 전부 assets폴더 안에 있어요.
 ******************************************/

/* src/main/java에 새로운 패키지 생기면 아래 수정하기 */
@SpringBootApplication(scanBasePackages = {"com.itmi", "com.user"})
public class jabdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(jabdaApplication.class, args);
	}
}
