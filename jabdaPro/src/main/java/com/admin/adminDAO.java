package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.UserTO;

@Repository
public class adminDAO {
	@Autowired
	private DataSource dataSource;
	
	public adminDAO(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.dataSource = dataSource;
		
		System.out.println(dataSource);
	}
	
	public UserTO memberview( UserTO to ){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = this.dataSource.getConnection();
			
			String sql = "select user_nickname, user_email, user_date, user_rank from user where user_email = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getEmail());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				to.setNickname(rs.getString("nickname"));
				to.setEmail(rs.getString("email"));
				to.setRank(rs.getString("rank"));
				to.setDate(rs.getString("date"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("오류 : " + e.getMessage());
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}			
		}
		
		return to;
	}
	
	public int memberModifyOK(UserTO to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int flag = 1;
		
		try {
			conn = this.dataSource.getConnection();
			String sql = "update user set user_nickname=?, user_email=?, user_rank=? where email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getNickname());
			pstmt.setString(2, to.getEmail());
			pstmt.setString(3, to.getRank());
			pstmt.setString(4, to.getEmail());
			
			int result = pstmt.executeUpdate();
			if(result == 1) {
				flag = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
