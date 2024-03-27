package com.test.mybatis;

import java.util.ArrayList;

public interface IGradeDAO
{
	public ArrayList<GradeDTO>list();
	
	public GradeDTO lists(int sid);
	
	public int count();
	public int add(GradeDTO g);
	
	public int modify(GradeDTO g);
	public int remove(String sid);
}
