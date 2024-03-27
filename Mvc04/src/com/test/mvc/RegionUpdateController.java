/*===========================
	RegionUpdateController.java
	- 사용자 정의 컨트롤러 클래스 
 ============================*/
package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자 정의 컨트롤러 클래스를 구성함 
//    cf. Controller annotation 활용
public class RegionUpdateController implements Controller
{
	
	private IRegionDAO dao;
	

	public void setDao(IRegionDAO dao)
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
			
		} 
		
		//------------------------------------------------------------- 세션 처리 과정 추가
		
		String regionName = request.getParameter("regionName");
		String regionId = request.getParameter("regionId");
		
		try
		{

			Region region = new Region();
			
			region.setRegionName(regionName);
			region.setRegionId(regionId);
			
			dao.modify(region);
			
			mav.setViewName("redirect:regionlist.action");
			

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		
		return mav;
		
	}
	
	
}
