package com.user;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.UserTO;

@Repository
public class UserDAO {
	
	@Autowired
	private DataSource dataSource;
	
	public int join_ok(UserTO to) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
		int flag = 1;
		try {
			mdSHA256.update(to.getPassword().getBytes("UTF-8"));
			byte[] passwordHash = mdSHA256.digest();
			
			StringBuilder pwhash = new StringBuilder();
	        for(byte b : passwordHash) {
	            String hexString = String.format("%02x", b);
	            pwhash.append(hexString);
	        }
	        
	        
	        
			conn = this.dataSource.getConnection();
			String sql = "insert into user values(0, ?, ?, ?, '일반')";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getNickname() );
			pstmt.setString( 2, to.getEmail() );
			pstmt.setString( 3, pwhash.toString() );
			
			int result = pstmt.executeUpdate();
			System.out.println( result );
			if( result == 1 ) {
				flag = 0;
			}
		} catch( SQLException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
		}
		
		return flag;
	}
	
	/* 이메일 중복체크 */
	public int email_check(UserTO to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int flag = 2;
		System.out.println( "flag1 : " + flag );
		try {
			conn = this.dataSource.getConnection();
			String sql = "select user_email from user where user_email = ?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getEmail() );
			rs = pstmt.executeQuery();
			System.out.println( "rs : " + rs.last() );
			System.out.println( "flag2 : " + flag );
			if( rs.last()  ) {
				// 쿼리의 결과가 있으면
				flag = 1;
				System.out.println( "flag3 : " + flag );
			} else {
				// 쿼리의 결과가 없으면
				flag = 0;
				System.out.println( "flag4 : " + flag );
			}
		} catch( SQLException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
		}
		System.out.println( "flag5 : " + flag );
		return flag;
	}
	
	
	/* 닉네임 중복체크 */
	public int nickname_check(UserTO to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int flag = 2;
		try {
			conn = this.dataSource.getConnection();
			String sql = "select user_nickname from user where user_nickname = ?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getNickname() );
			rs = pstmt.executeQuery();
			// System.out.println( "rs : " + rs.last() );
			// System.out.println( "flag2 : " + flag );
			// System.out.println(  );
			if( rs.last()  ) {
				// 쿼리의 결과가 있으면
				flag = 1;
			} else {
				// 쿼리의 결과가 없으면
				flag = 0;
			}
		} catch( SQLException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
		}
		return flag;
	}
	
	public int user(String id, String password) throws NoSuchAlgorithmException {
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      String upw = "";
	      MessageDigest md = MessageDigest.getInstance("SHA-256");
	      md.update( password.getBytes() );
		   
		   byte byteData[] = md.digest();
		   StringBuilder sb = new StringBuilder();
		   for(int i = 0; i < byteData.length; i++) {
			   sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
		   }
		   
		   upw = sb.toString();
	      ArrayList<UserTO> datas = new ArrayList<UserTO>();
	      int flag = 1;
	      System.out.println(id + "넘어온 id값 입니다");
	      System.out.println(upw + "넘어온 password값 입니다");
	      try {
	         conn = this.dataSource.getConnection();
	         String sql = "select * from user where user_email = ? and user_pw = ?";
	         pstmt = conn.prepareStatement( sql );
	         pstmt.setString(1, id);
	         pstmt.setString(2, upw);
	         rs = pstmt.executeQuery();
	      
	         if(rs.next() != false ) {
	        	 flag = 0;
	         }
//	         while( rs.next()  ) {
//	            UserTO uto = new UserTO();
//	            
//	            uto.setUserID( rs.getString("userid") );
//	            uto.setUserPassword( rs.getString("userpassword") );
//	            uto.setUserName( rs.getString("username") );
//	            uto.setUserGender( rs.getString("usergender") );
//	            uto.setUserEmail( rs.getString("useremail") );
//	            uto.setUserphone( rs.getString("userphone") );
//	            
//	            datas.add(uto); // uto에 넣은 데이터들을 ArrayList인 datas안에 넣는부분
//	         }
	      } catch( SQLException e ) {
	         System.out.println( "[에러] : " + e.getMessage() );
	      } finally {
	         if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
	         if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
	         if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
	      }
	      
	      return flag;
	      
	   }
	   
	   public int login(String userID, String userPassword) {
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   
		   
		   try {
			   
			   String SQL = "select user_pw from user where user_email = ?";
			   pstmt = conn.prepareStatement(SQL);
			   pstmt.setString(1, userID);
			   rs = pstmt.executeQuery();
			   if (rs.next()) {
				   if(rs.getString(1).equals(userPassword)) {
					   return 1; // 로그인성공
				   } else {
					   return 0; // 비밀번호 불일치
				   }
			   }
			   return -1; // 아이디가 없음
			
		   }catch(Exception e) {
			   System.out.println( "[에러] : " + e.getMessage() );
		   }
		   return -2; // 데이터베이스 오류를 의미함
	   }
	   
	   
	   public UserTO logincheck(String userID) {
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   
		   UserTO uto = new UserTO();
		   System.out.println("체크아이디: " + userID);
		   
		   try {
			   conn = this.dataSource.getConnection();
			   String sql = "select seq, user_nickname, user_email, user_pw from user where user_email = ?";
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, userID);
			   rs = pstmt.executeQuery();
			   
			   while(rs.next()) {
//				   uto.setSeq(rs.getString("userSeq"));
//				   uto.setUserID(rs.getString("userID"));
//				   uto.setUserName(rs.getString("userName"));
//				   uto.setUserGender(rs.getString("userGender"));
//				   uto.setUserEmail(rs.getString("userEmail"));
//				   uto.setUserphone(rs.getString("userphone"));
				   uto.setSeq(rs.getString("seq"));
				   uto.setNickname(rs.getString("user_nickname"));
				   uto.setEmail(rs.getString("user_email"));
				   uto.setPassword(rs.getString("user_pw"));
				   
				   
//				   System.out.println(rs.getString("nickname")); // 아이디 출력되는거 확인
				   
				   
			   } 
		   }catch (Exception e) {
			   System.out.println( "[에러] : " + e.getMessage() );
		   }
		   return uto;
	   }
}
