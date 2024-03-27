package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ReceiveController implements Controller
{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드 
		
		request.setCharacterEncoding("UTF-8");
		
		ModelAndView mav = new ModelAndView();
		
		String userName = request.getParameter("userName"); 
		
		mav.addObject("userName",userName);
		mav.setViewName("/WEB-INF/view/Receive.jsp");
		
		return mav;
		
	}
}
