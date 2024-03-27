package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class PositionDAO implements IPositionDAO
{
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	@Override
	public ArrayList<Position> list() throws SQLException
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
	public int add(Position position) throws SQLException
	{
		int result = 0;
		
		String sql = "INSERT INTO POSITION(POSITIONID, POSITIONNAME, MINBASICPAY)"
				+ " VALUES(POSITIONSEQ.NEXTVAL, ?, ?)";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, position.getPositionName());
		pstmt.setInt(2, position.getMinBasicPay());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public int remove(String positionId) throws SQLException
	{
		int result = 0;
		
		String sql = "DELETE FROM POSITION WHERE POSITIONID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(positionId));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public int modify(Position position) throws SQLException
	{
		int result = 0;
		
		String sql = "UPDATE POSITION SET POSITIONNAME= ?, MINBASICPAY=? WHERE POSITIONID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, position.getPositionName());
		pstmt.setInt(2, position.getMinBasicPay());
		pstmt.setInt(3, Integer.parseInt(position.getPositionId()));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public Position searchId(String positionId) throws SQLException
	{
		Connection conn = dataSource.getConnection();
		
		Position result = new Position();
		
		String sql = "SELECT POSITIONID, POSITIONNAME, MINBASICPAY, DELCHECK"
				+ " FROM POSITIONVIEW WHERE POSITIONID = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, positionId);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			
			result.setPositionId(rs.getString(1));
			result.setPositionName(rs.getString(2));
			result.setMinBasicPay(rs.getInt(3));
			result.setDelCheck(rs.getInt(4));
			
		}
		
		pstmt.close();
		rs.close();
		conn.close();
		
		return result;
	}
	

}
