/*=====================================================
	#36. LoginFormController.java
	- 사용자 정의 컨트롤러 클래스
	- 로그인 폼 요청에 대한 액션 수행
	- 아마도.. 사용자의 최초 요청 페이지이거나...
	- 로그인을 거치지 않고 다른 페이지를 요청한 사용자가 
	  안내받아 이동하게 되는 페이지
	- 단순히 로그인 폼이 구성되어 있는 페이지 (view) 개시
 ======================================================*/
package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자 정의 컨트롤러 클래스를 구성함 
//    cf. Controller annotation 활용
public class LogoutController implements Controller
{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드 
		
		ModelAndView mav = new ModelAndView();
		
		// 세션에 구성된 속성값들 제거 
		HttpSession session = request.getSession();
		
		session.removeAttribute("name");
		session.removeAttribute("admin");
		
		// 로그아웃 뷰 페이지(→ 안전하게 로그아웃 처리되었음)
		//                       → 로그인 페이지로 돌아가기)
		// 없이...
		// 바로 로그인 페이지를 다시 요청할 수 있도록 안내 
		
		mav.setViewName("redirect:loginform.action");
		
		return mav;
		
	}
	
	
}
