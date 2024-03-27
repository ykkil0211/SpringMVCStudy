package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class DepartmentDAO implements IDepartmentDAO
{

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	@Override
	public ArrayList<Department> list() throws SQLException
	{
		ArrayList<Department> result = new ArrayList<Department>();
		
		String sql = "SELECT * FROM DEPARTMENTVIEW ORDER BY DEPARTMENTID";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			
			Department department = new Department();
			
			department.setDepartmentId(rs.getString(1));
			department.setDepartmentName(rs.getString(2));
			department.setDelCheck(rs.getInt(3));
			
			result.add(department);
			
		}
		
		pstmt.close();
		rs.close();
		conn.close();
		
		
		return result;
	}

	@Override
	public int add(Department department) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "INSERT INTO DEPARTMENT(DEPARTMENTID, DEPARTMENTNAME)"
				+ " VALUES(DEPARTMENTSEQ.NEXTVAL, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, department.getDepartmentName());
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public int remove(String departmentId) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "DELETE FROM DEPARTMENT WHERE DEPARTMENTID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(departmentId));
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		
		return result;
	}

	//부서 데이터 변경 
	@Override
	public int modify(Department department) throws SQLException
	{
		int result = 0;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "UPDATE DEPARTMENT SET DEPARTMENTNAME=? WHERE DEPARTMENTID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, department.getDepartmentName());
		pstmt.setInt(2, Integer.parseInt(department.getDepartmentId()));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		
		return result;
	}

	@Override
	public Department searchId(String departmentId) throws SQLException
	{
		Connection conn = dataSource.getConnection();

		Department result = new Department();
		
		String sql = "SELECT DEPARTMENTID, DEPARTMENTNAME, DELCHECK FROM"
				+ " DEPARTMENTVIEW WHERE DEPARTMENTID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, departmentId);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			
			result.setDepartmentId(rs.getString(1));
			result.setDepartmentName(rs.getString(2));
			result.setDelCheck(rs.getInt(3));
			
		}
		
		pstmt.close();
		rs.close();
		conn.close();
		
		return result;
	}
	
	
	

}
