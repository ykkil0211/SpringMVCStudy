/*==================================
	#09. EmployeeDAO.java
	- 데이터베이스 액션 처리 클래스
	- 직원 데이터 CRUD 액션
	  (Create / Read / Update / Delete)
	- Connection 객체에 대한 의존성 주입을 위한 준비
	  → 인터페이스 형태의 속성 구성(DataSource)
	  → setter 구성 
====================================*/
package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class EmployeeDAO implements IEmployeeDAO
{
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	@Override
	public ArrayList<Employee> list() throws SQLException
	{

		ArrayList<Employee> result = new ArrayList<Employee>();
		/*
		EMPLOYEEID	SSN	BIRTHDAY	LUNAR	LUNARNAME	TELEPHONE	NAME	DEPARTMENTID	DEPARTMENTNAME	
		POSITIONID	POSITIONNAME	REGIONID	REGIONNAME	BASICPAY	EXTRAPAY	PAY	GRADE
		2	990211	1999-02-11	1	음력	010-6678-9867	길현욱	1	개발부	1	사원	2	경기	1500000	1500000	3000000	1
		1	971017	1997-10-17	0	양력	010-6380-7047	강혜성	1	개발부	2	대리	1	서울	2500000	1500000	4000000	0
		*/
		
		// String sql = "SELECT * FROM EMPLOYEEVIEW ORDER BY EMPLOYEEID";
		String sql = "SELECT EMPLOYEEID, SSN, BIRTHDAY,	LUNAR,	LUNARNAME,	TELEPHONE,	NAME,	DEPARTMENTID,	DEPARTMENTNAME"
		+ ", POSITIONID,	POSITIONNAME,	REGIONID,	REGIONNAME,	BASICPAY,	EXTRAPAY,	PAY, GRADE"
		+ " FROM EMPLOYEEVIEW ORDER BY EMPLOYEEID";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Employee employee = new Employee();
			
			employee.setEmployeeId(rs.getString(1));
			employee.setSsn(rs.getString(2));
			employee.setBirthday(rs.getString(3));
			employee.setLunar(rs.getInt(4));
			employee.setLunarName(rs.getString(5));
			employee.setTelephone(rs.getString(6));
			employee.setName(rs.getString(7));
			employee.setDepartmentId(rs.getString(8));
			employee.setDepartmentName(rs.getString(9));
			employee.setPositionId(rs.getString(10));
			employee.setPositionName(rs.getString(11));
			employee.setRegionId(rs.getString(12));
			employee.setRegionName(rs.getString(13));
			employee.setBasicPay(rs.getInt(14));
			employee.setExtraPay(rs.getInt(15));
			employee.setPay(rs.getInt(16));
			employee.setGrade(rs.getInt(17));
			
			result.add(employee);
			
		}
		
		
		pstmt.close();
		rs.close();
		conn.close();
		
		return result;
	}

	@Override
	public ArrayList<Region> regionList() throws SQLException
	{
		ArrayList<Region> result = new ArrayList<Region>();
		
		String sql = "SELECT * FROM REGIONVIEW ORDER BY REGIONID";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			Region region = new Region();
			
			region.setRegionId(rs.getString(1));
			region.setRegionName(rs.getString(2));
			region.setDelCheck(rs.getInt(3));
			
			result.add(region);
			
		}
		
		pstmt.close();
		rs.close();
		conn.close();
		
		return result;
	}

	@Override
	public ArrayList<Department> departmentList() throws SQLException
	{
		ArrayList<Department> result = new ArrayList<Department>();
		
		String sql= "SELECT * FROM DEPARTMENTVIEW ORDER BY DEPARTMENTID";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
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
	public ArrayList<Position> positionList() throws SQLException
	{
		ArrayList<Position> result = new ArrayList<Position>();
		
		String sql = "SELECT * FROM POSITIONVIEW ORDER BY POSITIONID";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			Position position = new Position();
			
			position.setPositionId(rs.getString(1));
			position.setPositionName(rs.getString(2));
			position.setMinBasicPay(rs.getInt(3));
			position.setDelCheck(rs.getInt(4));
			
			result.add(position);
		}
		
		pstmt.close();
		rs.close();
		conn.close();
		
		return result;
	}

	@Override
	public int getMinBasicPay(String positionID) throws SQLException
	{
		int result = 0;
		
		String sql = "SELECT MINBASICPAY FROM POSITION WHERE POSITIONID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(positionID));
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			result = rs.getInt("MINBASICPAY");
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public int employeeAdd(Employee employee) throws SQLException
	{
		int result = 0;
		
		String sql = "INSERT INTO EMPLOYEE(EMPLOYEEID, NAME, BIRTHDAY, LUNAR"
				+ ", TELEPHONE, DEPARTMENTID, POSITIONID, REGIONID"
				+ ", BASICPAY, EXTRAPAY, SSN1, SSN2)"
				  + " VALUES(EMPLOYEESEQ.NEXTVAL,?"
				  + ", TO_DATE(?, 'YYYY-MM-DD')"
				  + ", ?, ?, ?, ?, ?, ?"
				  + ", ?, ?"
				  + ", CRYPTPACK.ENCRYPT(?,?))";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getBirthday());
		pstmt.setInt(3, employee.getLunar());
		pstmt.setString(4, employee.getTelephone());
		pstmt.setInt(5, Integer.parseInt(employee.getDepartmentId()));
		pstmt.setInt(6, Integer.parseInt(employee.getPositionId()));
		pstmt.setInt(7, Integer.parseInt(employee.getRegionId()));
		pstmt.setInt(8, employee.getBasicPay());
		pstmt.setInt(9, employee.getExtraPay());
		pstmt.setString(10, employee.getSsn1());
		pstmt.setString(11, employee.getSsn2());
		pstmt.setString(12, employee.getSsn2());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();

		return result;
		
	}

	@Override
	public int remove(String employeeId) throws SQLException
	{
		int result = 0;
		
		String sql = "DELETE FROM EMPLOYEE WHERE EMPLOYEEID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(employeeId));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public int modify(Employee employee) throws SQLException
	{
		int result = 0;
		
		String sql = "UPDATE EMPLOYEE SET NAME=?"
				+ ", BIRTHDAY=TO_DATE(?, 'YYYY-MM-DD')"
				+ ", LUNAR=?, TELEPHONE=?, DEPARTMENTID=?"
				+ ", POSITIONID=?, REGIONID=?, BASICPAY=?"
				+ ", EXTRAPAY=?, SSN1=?"
				+ ", SSN2=CRYPTPACK.ENCRYPT(?,?)"
				+ " WHERE EMPLOYEEID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getBirthday());
		pstmt.setInt(3, employee.getLunar());
		pstmt.setString(4, employee.getTelephone());
		pstmt.setInt(5, Integer.parseInt(employee.getDepartmentId()));
		pstmt.setInt(6, Integer.parseInt(employee.getPositionId()));
		pstmt.setInt(7, Integer.parseInt(employee.getRegionId()));
		pstmt.setInt(8, employee.getBasicPay());
		pstmt.setInt(9, employee.getExtraPay());
		pstmt.setString(10, employee.getSsn1());
		pstmt.setString(11, employee.getSsn2());
		pstmt.setString(12, employee.getSsn2());
		pstmt.setInt(13, Integer.parseInt(employee.getEmployeeId()));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public Employee searchId(String employeeId) throws SQLException
	{
		
		Connection conn = dataSource.getConnection();
		
		Employee result = new Employee(); 
		
		String sql = "SELECT EMPLOYEEID, NAME, SSN1, TO_CHAR(BIRTHDAY, 'YYYY-MM-DD') AS BIRTHDAY"
				+ ", LUNAR, TELEPHONE, DEPARTMENTID, POSITIONID, REGIONID"
				+ ", BASICPAY, EXTRAPAY FROM EMPLOYEE WHERE EMPLOYEEID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, employeeId);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			result.setEmployeeId(rs.getString("EMPLOYEEID"));
			result.setName(rs.getString("NAME"));
			result.setSsn1(rs.getString("SSN1"));
			result.setBirthday(rs.getString("BIRTHDAY"));
			result.setLunar(rs.getInt("LUNAR"));
			result.setTelephone(rs.getString("TELEPHONE"));
			result.setDepartmentId(rs.getString("DEPARTMENTID"));
			result.setPositionId(rs.getString("POSITIONID"));
			result.setRegionId(rs.getString("REGIONID"));
			result.setBasicPay(rs.getInt("BASICPAY"));
			result.setExtraPay(rs.getInt("EXTRAPAY"));
			
		}
		
		pstmt.close();
		rs.close();
		conn.close();
		
		return result;
	}

	@Override
	public String login(String id, String pw) throws SQLException
	{
		String result = null;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT NAME"
				+ " FROM EMPLOYEE WHERE EMPLOYEEID = ?"
				+ " AND SSN2 = CRYPTPACK.ENCRYPT(?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(id));
		pstmt.setString(2, pw);
		pstmt.setString(3, pw);
		
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
			result = rs.getString("NAME");
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public String loginAdmin(String id, String pw) throws SQLException
	{
		String result = null;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT NAME FROM EMPLOYEE"
				+ " WHERE EMPLOYEEID = ?"
				+ " AND SSN2 = CRYPTPACK.ENCRYPT(?,?)"
				+ " AND GRADE = 0";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(id));
		pstmt.setString(2, pw);
		pstmt.setString(3, pw);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
			result = rs.getString("NAME");
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	
}
