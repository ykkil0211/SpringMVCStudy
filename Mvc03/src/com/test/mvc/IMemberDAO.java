/*=================
	IMemberDAO.java
	- 인터페이스
=================== */
package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IMemberDAO
{
	// 회원 데이터 추가(등록) 메소드 선언
	public int add(MemberDTO member) throws SQLException;
	
	// 전체 인원 수 확인(조회) 메소드 선언 
	public int count() throws SQLException;
	
	// 전체 회원 리스트 확인(조회) 메소드 선언 
	public ArrayList<MemberDTO>list() throws SQLException;

}
