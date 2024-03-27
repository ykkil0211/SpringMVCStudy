/*======================================
	MemberInsertController.java
	- 사용자 정의 컨트롤러 클래스 
	- 회원 데이터 추가 액션 처리 클래스
	- DAO 객체에 대한 의존성 주입 
	  (의존성 주입을 위한 준비 필요)
 =======================================*/
package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자 정의 컨트롤러 클래스를 구성함 
//    cf. Controller annotation 활용
public class MemberInsertController implements Controller
{
	
	// dao 관련 속성 구성 → 인터페이스 형태 
	private IMemberDAO dao;
	
	
	public void setDao(IMemberDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드 
		ModelAndView mav = new ModelAndView();
		
		// 캐릭터 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		//이전 페이지(MemberList.jsp)로부터 넘어온 데이터 수신
		//-- name, telephone
		String name = request.getParameter("name");
		String tel = request.getParameter("telephone");
		
		try
		{
			//MemberDTO 구성 
			MemberDTO member = new MemberDTO();
			
			member.setName(name);
			member.setTelephone(tel);
			
			// insert 쿼리문을 수정하는 dao의 add() 메소드 호출
			dao.add(member);
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		// check~!!
		//mav.setViewName("/WEB-INF/view/MemberList.jsp");	//--(Ｘ)
		
		// check~!!
		// sendRedirect() → MemberList.jsp					//--(Ｘ)
		//				  → memberlist.do					//--(Ｏ)
		
		//mav.setViewName("memberlist.do");
		mav.setViewName("redirect:memberlist.do");
				
		return mav;
		
	}
	
	
}
