/*=====================================================
	#30. EmployeeUpdateFormController.java
	- 사용자 정의 컨트롤러 클래스
	- 직원 데이터 수정 폼 요청에 대한 액션 처리 
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
public class RegionUpdateFormController implements Controller
{
	
	// check~!!!
	// EmployeeInsertFormController 구성과는 다른 방식으로 처리~!!!
	// -----------------------------
	// → 필요한 모든 메소드를 EmployeeDAO 에 정의해 두었던 상태
	
	// 주요 속성 구성 
	private IRegionDAO regionDAO;

	
	// setter 구성 
	public void setRegionDAO(IRegionDAO regionDAO)
	{
		this.regionDAO = regionDAO;
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
		
		
		try
		{
			// 이전 페이지(EmployeeList.jsp)로 부터 넘어온 데이터 수신 
			//-- employeeId
			String regionId = request.getParameter("regionId");
			
			Region region = new Region();
			
			region = regionDAO.searchId(regionId);
			
			mav.addObject("region", region);
			
			mav.setViewName("WEB-INF/view/RegionUpdateForm.jsp");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
		
	}
	
	
}
