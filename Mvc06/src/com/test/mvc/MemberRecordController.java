/*=====================================================
   MemberRecordController.java
   - 사용자 정의 컨트롤러 클래스
  
   - 데이터베이스의 리스트를 읽어오는 액션 처리 
   - 『memberlist.action』 → 요청 → memberList() 처리
  
   - 데이터 입력(회원 등록) 폼 요청 관련 액션 처리
   - 『memberinsertform.action』 → 요청 → memberInsertForm() 처리 
   
   - 데이터 입력(회원 등록) 액션 처리 
   - 『memberinsert.action』 → 요청 → memberInsert() 처리 
 ==================================================================*/
package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberRecordController
{
	@RequestMapping("/memberlist.action")
	public String memberlist(Model model) throws SQLException
	{
		ArrayList<MemberDTO> dto = new ArrayList<MemberDTO>();
		
		MemberDAO dao = new MemberDAO();
		
		dao.connection();
		
		dto = dao.lists();
		
		model.addAttribute("dto",dto);
		
		return "/WEB-INF/view/MemberList.jsp";
	}
	
	@RequestMapping("/memberinsertform.action")
	public String memberinsertform()
	{
		
		return "/WEB-INF/view/MemberInsertForm.jsp";
		
	}
	
	// 데이터 입력(회원 등록) 액션 처리
	@RequestMapping("/memberinsert.action")
	public String memberinsert(@RequestParam String name
							 , @RequestParam String id
							 , @RequestParam String pw
							 , @RequestParam String email
							 , @RequestParam String tel) throws SQLException
	{
		MemberDAO dao = new MemberDAO();

		dao.connection();
		
		MemberDTO dto = new MemberDTO();
		
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setTel(tel);
		dto.setEmail(email);
		
		
		dao.insertQuery(dto);
		
		
		return "redirect:memberlist.action";
	}
}
