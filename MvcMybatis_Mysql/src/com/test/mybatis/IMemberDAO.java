/*========================
	IMemberDAO.java
	- 인터페이스
==========================*/
package com.test.mybatis;

import java.util.ArrayList;

public interface IMemberDAO
{
	public ArrayList<MemberDTO> list();
	public int count();
	public int add(MemberDTO m);
	public int remove(String id);
	public MemberDTO search(String id);
	public int modify(MemberDTO m);
	
}
