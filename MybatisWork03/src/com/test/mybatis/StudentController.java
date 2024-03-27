package com.test.mybatis;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController
{
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/studentlist.action", method = RequestMethod.GET)
	public String memberList(ModelMap model)
	{
		
		//IMemberDAO dao = (ImemberDAO)new MemberDAO();
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
		
		//dao.count();
		//dao.list();
		
		model.addAttribute("count", dao.count());
		model.addAttribute("list", dao.list());
		
		return "/WEB-INF/view/StudentList.jsp";
		
	}
	
	
	@RequestMapping(value = "/studentinsertform.action", method = RequestMethod.GET)
	public String memberInsertForm() 
	{
		return "/WEB-INF/view/StudentInsertForm.jsp";
	}
	
	@RequestMapping(value = "/studentinsert.action", method = RequestMethod.POST)
	public String memberInsert(StudentDTO s)
	{
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
		
		
		dao.add(s);
		
		return "redirect:studentlist.action";
		
	}
	
	@RequestMapping(value = "/studentupdateform.action", method = RequestMethod.GET)
	public String memberUpdateForm(String sid, ModelMap model) 
	{
		
		int id = Integer.parseInt(sid);
		
		StudentDTO dto = new StudentDTO();
		
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);

		dto = dao.list2(id);
				
		model.addAttribute("lists", dto);

		return "/WEB-INF/view/StudentUpdateForm.jsp";
		
	}
	
	@RequestMapping(value="/studentupdate.action", method = RequestMethod.POST)
	public String memberUpdate(StudentDTO s)
	{
		
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);
		
		dao.modify(s);
		
		return "redirect:studentlist.action";

	}
	
	@RequestMapping(value="/studentdelete.action", method= RequestMethod.GET)
	public String memberDelete(String sid)
	{
		IStudentDAO dao = sqlSession.getMapper(IStudentDAO.class);

		dao.remove(sid);
		
		return "redirect:studentlist.action";

	}
	
}
