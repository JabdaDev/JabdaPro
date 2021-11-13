package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.user.UserTO;

public class adminDAO {
	@Autowired
	private DataSource dataSource;
	
	public adminDAO(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.dataSource = dataSource;
		
		System.out.println(dataSource);
	}
	
	/* rank 등급수정 */
	public int rankModifyOK(UserTO to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int flag = 1;
		
		try {
			conn = this.dataSource.getConnection();
			
			String sql = "update user set user_rank = ? where user_email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, to.getRank());
			pstmt.setString(2, to.getEmail());
			
			if(pstmt.executeUpdate() == 1) {
				flag = 0;
			};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}			
		}
		return flag;
	}
}
