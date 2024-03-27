/*====================================================
	PositionInsertController.java
	- 사용자 정의 컨트롤러 클래스
	- 직원 데이터 입력 액션 처리 → DAO 필요 
	- 이후 employeelist.action 을 다시 요청할 수 있도록 안내
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
public class PositionInsertController implements Controller
{
	
	// 인터페이스 형태의 속성 구성 
	private IPositionDAO dao;
	
	public void setDao(IPositionDAO dao)
	{
		this.dao = dao;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드 
		
		ModelAndView mav = new ModelAndView();
		
		// 세션 처리 과정 추가 ---------------------------------------------------
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("name")==null)
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
			
		} else if(session.getAttribute("admin")==null) //-- 로그인은 되었지만 관리자가 아닌 상황
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
			//-- 로그인은 되어 있지만 이 때 클라이언트는 
			//   일반 직원으로 로그인 되어있는 상황이므로 
			//   기존의 로그인 내용을 없애고 로그아웃 액션 처리하여
			//   다시 관리자로 로그인 할 수 있도록 처리 
			
		}
		
		//------------------------------------------------------------- 세션 처리 과정 추가
		
		// 이전 페이지(EmployeeInsertform.jsp)로부터 넘어온 데이터 수신 
		//-- name ~ extraPay
		String positionName = request.getParameter("positionName");
		String minBasicPay = request.getParameter("minBasicPay");

		
		try
		{
			Position position = new Position();
			
			
			position.setPositionName(positionName);
			position.setMinBasicPay(Integer.parseInt(minBasicPay));
			
			dao.add(position);
			
			// check~!
			//mav.setViewName("WEB-INF/view/employeeList.jsp");
			mav.setViewName("redirect:positionlist.action");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
		
	}
	
	
}
