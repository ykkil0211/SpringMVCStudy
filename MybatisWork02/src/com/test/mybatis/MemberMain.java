/*========================
	MemberMain.java
	- 사용자 정의 컨트롤러
 ==========================*/
package com.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberMain
{
	// 주요 속성 구성 
	// mybatis 객체 의존성(자동) 주입 ~!!
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/memberlist.action", method = RequestMethod.GET)
	public String memberList(ModelMap model)
	{
		
		//IMemberDAO dao = (ImemberDAO)new MemberDAO();
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		
		//dao.count();
		//dao.list();
		
		model.addAttribute("count", dao.count());
		model.addAttribute("list", dao.list());
		
		return "/WEB-INF/view/MemberList.jsp";
		
		
	}
	
	@RequestMapping(value = "/memberinsert.action", method = RequestMethod.POST)
	public String memberInsert(MemberDTO m)
	{
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		
		dao.add(m);
		
		return "redirect:memberlist.action";
		
	}
	
	// 삭제 기능 처리 ④
	@RequestMapping(value = "/memberdelete.action", method = RequestMethod.GET)
	public String memberDelete(String mid)
	{
		
		IMemberDAO dao = sqlSession.getMapper(IMemberDAO.class);
		
		dao.remove(mid);
		
		return "redirect:memberlist.action";
	}
	
	
}
