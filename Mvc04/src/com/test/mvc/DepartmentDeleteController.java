/*====================================================
	#42.DepartmentDeleteController.java
	- 사용자 정의 컨트롤러 클래스
	- 직원 데이터 입력 삭제 처리 → DAO 필요 
	- 이후 departmentlist.action 을 다시 요청할 수 있도록 안내
	- DAO 객체에 대한 의존성 주입 (DI) 을 위한 준비 
	  → 인터페이스 행태의 자료형을 속성으로 구성
	  	  → setter 메소드 준비 
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
public class DepartmentDeleteController implements Controller
{
	private IDepartmentDAO dao;
	
	public void setDao(IDepartmentDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드 
		
		
		ModelAndView mav = new ModelAndView();
		
		// 로그인 여부만 확인 → 관리자인지 일반 직원인지 확인할 필요 없음.
		// 세션 처리 과정 추가 --------------------------------------------
		HttpSession session = request.getSession();
		
		if(session.getAttribute("name")==null) //-- 로그인이 되어 있지 않은 상황
		{	
			
			mav.setViewName("redirect:loginform.action");
			
		}
		
		// ----------------------------------------------------------------
		
		// 이전 페이지로부터(EmployeeList.jsp)로부터 넘어온 데이터 수신 
		//--departmentId
		String departmentId = request.getParameter("departmentId");
		
		try
		{
			dao.remove(departmentId);
			
			mav.setViewName("redirect:departmentlist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
		
	}
	
	
}
