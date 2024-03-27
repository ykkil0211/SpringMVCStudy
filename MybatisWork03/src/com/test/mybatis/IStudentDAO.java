package com.test.mybatis;

import java.util.ArrayList;

public interface IStudentDAO
{
	public ArrayList<StudentDTO>list();
	
	public StudentDTO list2(int sid);
	
	public int count();
	public int add(StudentDTO s);
	
	public int modify(StudentDTO s);
	public int remove(String sid);

}
