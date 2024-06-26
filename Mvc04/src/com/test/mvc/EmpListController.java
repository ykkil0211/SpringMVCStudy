/*===========================
	EmpListController.java
	- 사용자 정의 컨트롤러 클래스 
 ============================*/
package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자 정의 컨트롤러 클래스를 구성함 
//    cf. Controller annotation 활용
public class EmpListController implements Controller
{
	
	private IEmployeeDAO dao;

	
	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드 
		
		ModelAndView mav = new ModelAndView();		
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("name")==null)
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
			
		}
		
		
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		try
		{
			
			employeeList = dao.list();
			
			mav.addObject("employeeList", employeeList);
			
			mav.setViewName("/WEB-INF/view/EmpList.jsp");
;
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
		
	}
	
	
}
