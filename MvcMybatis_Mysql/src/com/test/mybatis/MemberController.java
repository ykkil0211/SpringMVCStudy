package com.test.mybatis;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController
{
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/memberlist.action", method = RequestMethod.GET)
	public String memberList(ModelMap model)
	{
		
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		
		model.addAttribute("count", dao.count());
		model.addAttribute("list", dao.list());
		
		return "/WEB-INF/view/MemberList.jsp";
		
	}
	
	@RequestMapping(value="/memberinsert.action", method = RequestMethod.POST)
	public String memberinsert(MemberDTO m)
	{
		
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		
		dao.add(m);
		
		return "redirect:memberlist.action";
	}
	
	@RequestMapping(value="/memberdelete.action", method = RequestMethod.GET)
	public String memberDelete(String id)
	{
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		
		dao.remove(id);
		
		return "redirect:memberlist.action";
		
	}
	
	@RequestMapping(value="/memberupdateform.action", method = RequestMethod.GET)
	public String memberUpdateForm(ModelMap model, String id)
	{
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		
		model.addAttribute("member", dao.search(id));
		
		return "/WEB-INF/view/MemberUpdateForm.jsp";

	}
	
	@RequestMapping(value="/memberupdate.action", method = RequestMethod.POST)
	public String memberUpdate(MemberDTO m) 
	{
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);

		System.out.println(m.getId() + " " + m.getName() + " " + m.getTel() + " " + m.getEmail());

		dao.modify(m);
		
		return "redirect:memberlist.action";

	}
	
}
