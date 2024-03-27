package com.test.mybatis;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GradeController
{
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/gradelist.action", method = RequestMethod.GET)
	public String memberList(ModelMap model)
	{
		
		IGradeDAO dao = sqlSession.getMapper(IGradeDAO.class);
		
		//dao.count();
		//dao.list();
		
		model.addAttribute("count", dao.count());
		model.addAttribute("list", dao.list());
		
		return "/WEB-INF/view/GradeList.jsp";
		
	}
	
	
	
	@RequestMapping(value = "/gradeinsertform.action", method = RequestMethod.GET)
	public String memberInsertForm()
	{
		return "/WEB-INF/view/GradeInsertForm.jsp";
	}

	
	@RequestMapping(value = "/gradeinsert.action", method = RequestMethod.POST)
	public String memberInsert(GradeDTO g)
	{
		
		IGradeDAO dao = sqlSession.getMapper(IGradeDAO.class);

		dao.add(g);

		return "redirect:gradelist.action";
	  
	  
	}
	
	@RequestMapping(value= "/gradeupdateform.action", method = RequestMethod.GET)
	public String memberUpdateForm(String sid, ModelMap model)
	{
		
		int id = Integer.parseInt(sid);
		
		GradeDTO dto = new GradeDTO();
		
		IGradeDAO dao = sqlSession.getMapper(IGradeDAO.class);
		
		dto = dao.lists(id);
		
		model.addAttribute("lists", dto);
		
		return "/WEB-INF/view/GradeUpdateForm.jsp";
	}
	
	@RequestMapping(value="/gradeupdate.action", method = RequestMethod.POST)
	public String memberUpdate(GradeDTO g)
	{
		
		IGradeDAO dao = sqlSession.getMapper(IGradeDAO.class);
		
		dao.modify(g);
		
		return "redirect:gradelist.action";
		
	}
	
	@RequestMapping(value="/gradedelete.action", method = RequestMethod.GET)
	public String memberDelete(String sid)
	{
		
		IGradeDAO dao = sqlSession.getMapper(IGradeDAO.class);
		
		dao.remove(sid);
		
		return "redirect:gradelist.action";
		
	}

	 
}
