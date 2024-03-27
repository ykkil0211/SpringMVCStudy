package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PositionDeleteController implements Controller
{
	
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
		
		// 로그인 여부만 확인 → 관리자인지 일반 직원인지 확인할 필요 없음.
		// 세션 처리 과정 추가 --------------------------------------------
		HttpSession session = request.getSession();
		
		if(session.getAttribute("name")==null) //-- 로그인이 되어 있지 않은 상황
		{	
			
			mav.setViewName("redirect:loginform.action");
			
		}
		
		// ----------------------------------------------------------------
		
		
		String positionId = request.getParameter("positionId");
		
		try
		{
			dao.remove(positionId);
			
			mav.setViewName("redirect:positionlist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		
		}
		
		System.out.println("1");
		
		
		return mav;
		
	}
}
