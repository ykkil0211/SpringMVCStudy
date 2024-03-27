/*=====================================================
	#36. LoginFormController.java
	- 사용자 정의 컨트롤러 클래스
	- 로그아웃 액션 처리 전용 객체.
	- 세션에 구성된 속성값들 제거 
	- 다시 로그인 폼(loginform.action)을 요청할 수 있도록 안내 
 ======================================================*/
package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자 정의 컨트롤러 클래스를 구성함 
//    cf. Controller annotation 활용
public class LoginFormController implements Controller
{
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드 
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("WEB-INF/view/LoginForm.jsp");
		
		
		return mav;
		
	}
	
	
}
