/*================================================
	#39.RegionListController.java
	- 사용자 정의 컨트롤러 클래스 
	- 회원 리스트 페이지 요청에 대한 액션 처리 
	- DAO 객체에 대한 의존성 주입(DI)을 위한 준비 
	  → 인터페이스 형태의 자료형을 속성으로 구성 
	  → setter 메소드 준비 
 =================================================*/
package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자 정의 컨트롤러 클래스를 구성함 
//    cf. Controller annotation 활용
public class RegListController implements Controller
{
	
	//주요 속성 구성 
	// → 인터페이스 형태의 자료형 속성으로 구성
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
		
		
		ArrayList<Region> RegionList = new ArrayList<Region>();
		
		try
		{
			RegionList = dao.list();
			
			mav.addObject("RegionList", RegionList);
			
			mav.setViewName("/WEB-INF/view/RegList.jsp");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
				
		return mav;
		
	}
	
	
}
