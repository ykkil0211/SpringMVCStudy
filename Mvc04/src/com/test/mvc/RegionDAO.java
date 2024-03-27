package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class RegionDAO implements IRegionDAO
{
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	@Override
	public ArrayList<Region> list() throws SQLException
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
	public int add(Region region) throws SQLException
	{
		int result = 0;
		
		String sql = "INSERT INTO REGION(REGIONID, REGIONNAME) VALUES(REGIONSEQ.NEXTVAL, ?)";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, region.getRegionName());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public int remove(String regionId) throws SQLException
	{
		int result = 0;
		
		String sql = "DELETE FROM REGION WHERE REGIONID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(regionId));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public int modify(Region region) throws SQLException
	{
		int result = 0;
		
		String sql = "UPDATE REGION SET REGIONNAME=? WHERE REGIONID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, region.getRegionName());
		pstmt.setInt(2, Integer.parseInt(region.getRegionId()));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	public Region searchId(String regionId) throws SQLException
	{
		Connection conn = dataSource.getConnection();

		Region result = new Region();
		
		String sql = "SELECT REGIONID, REGIONNAME, DELCHECK"
				+ " FROM REGIONVIEW WHERE REGIONID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, regionId);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			
			result.setRegionId(rs.getString(1));
			result.setRegionName(rs.getString(2));
			result.setDelCheck(rs.getInt(3));
			
		}
		
		pstmt.close();
		rs.close();
		conn.close();
		
		return result;
	}
	
}
