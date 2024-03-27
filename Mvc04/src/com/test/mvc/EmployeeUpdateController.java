/*===========================
	SampleController.java
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
public class EmployeeUpdateController implements Controller
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
		
		// 세션 처리 과정 추가 ---------------------------------------------------
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("name")==null)
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
			
		} 
		
		//------------------------------------------------------------- 세션 처리 과정 추가
		
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String lunar = request.getParameter("lunar");
		String telephone = request.getParameter("telephone");
		String departmentId = request.getParameter("departmentId");
		String positionId = request.getParameter("positionId");
		String regionId = request.getParameter("regionId");
		String basicPay = request.getParameter("basicPay");
		String extraPay = request.getParameter("extraPay");
		String ssn1 = request.getParameter("ssn1");
		String ssn2 = request.getParameter("ssn2");
		String employeeId = request.getParameter("employeeId");
		
		try
		{
			Employee employee = new Employee();
			
			employee.setName(name);
			employee.setBirthday(birthday);
			employee.setLunar(Integer.parseInt(lunar));
			employee.setTelephone(telephone);
			employee.setDepartmentId(departmentId);
			employee.setPositionId(positionId);
			employee.setRegionId(regionId);
			employee.setBasicPay(Integer.parseInt(basicPay));
			employee.setExtraPay(Integer.parseInt(extraPay));
			employee.setSsn1(ssn1);
			employee.setSsn2(ssn2);
			employee.setEmployeeId(employeeId);
			
			dao.modify(employee);
			
			mav.setViewName("redirect:employeelist.action");
			

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		
		return mav;
		
	}
	
	
}
