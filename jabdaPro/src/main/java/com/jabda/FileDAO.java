package com.itmi;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.product.ProductTO;
import com.review.ReviewTO;

@Repository
public class FileDAO {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private MultipartHttpServletRequest multi;
	
	@Autowired
	private HttpServletRequest req;
	
	public String file_upload(MultipartHttpServletRequest multi) {
		
		// 저장 경로 설정
		String realpath = System.getProperty("user.dir");
		String path = realpath.replace("\\", "/") + "/src/main/webapp/assets/images/review/";
		String newFileName = ""; // 업로드 되는 파일명
		String nickname = req.getParameter("nickname");
		String productname = req.getParameter("productname");
		
		String monthPro = "";
		String datePro = "";
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if( month < 10 ) {
			monthPro = "0" + month; 
		} else {
			monthPro = month + "";
		}
		
		int date = cal.get(Calendar.DATE);
		if( date < 10 ) {
			datePro = "0" + date;
		} else {
			datePro = date + "";
		}
		
		String strReturn = "";
        File dir = new File(path);

        if(!dir.isDirectory()){
            dir.mkdir();
        }
        int cnt = 1;
        Iterator<String> files = multi.getFileNames();
        if( files != null) {
	        while(files.hasNext()){
	            String uploadFile = files.next();
	            System.out.println(uploadFile);
	            MultipartFile mFile = multi.getFile(uploadFile);
	            // System.out.println("파일 크기 : " + mFile.getSize());
	            
	            String orgfileName = mFile.getOriginalFilename();
	            String proFileName = orgfileName.substring(0, orgfileName.lastIndexOf(".") );
	            // System.out.println(proFileName);
	            
	            
	            String fileExtension = FilenameUtils.getExtension(orgfileName); 
	            try {
	                mFile.transferTo(new File(path + "ITMI_" + year + "" + monthPro + "" + datePro + "_" + nickname + "_" + cnt + "_" + productname + "." + fileExtension));
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            cnt++;
	        }
        }else {
    		strReturn = "성공";
    	}
        return strReturn;
	}

	
public String file_delete(String strSeq, String strNickname, String image1, String image2, String image3) {
		String realpath = System.getProperty("user.dir");
		String path = realpath.replace("\\", "/") + "/src/main/webapp/assets/images/review/";
		File file = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dataSource.getConnection();
			
			String sql = "select image1, image2, image3 from review where nickname=? and seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString(1, strNickname );
			pstmt.setString(2, strSeq );
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				/*
				 * if( image1.equals("") || image1 == null ) {} else { if(
				 * rs.getString("image1") != null ) { file = new File( path+
				 * rs.getString("image1") ); if( file.exists() ) { if( file.delete() ) {
				 * System.out.println("이미지1 파일삭제 성공"); }else {
				 * System.out.println("이미지1 파일삭제 실패"); } }else {
				 * System.out.println("이미지1 파일이 없음"); } } } if( image2.equals("") || image2 ==
				 * null ){} else { if( rs.getString("image2") != null ) { file = new File( path+
				 * rs.getString("image2") ); if( file.exists() ) { if( file.delete() ) {
				 * System.out.println("이미지2 파일삭제 성공"); }else {
				 * System.out.println("이미지2 파일삭제 실패"); } }else {
				 * System.out.println("이미지2 파일이 없음"); } } }
				 * 
				 * if( image3.equals("") || image3 == null ){} else { if( rs.getString("image3")
				 * != null ) { file = new File( path+ rs.getString("image3") ); if(
				 * file.exists() ) { if( file.delete() ) { System.out.println("이미지3 파일삭제 성공");
				 * }else { System.out.println("이미지3 파일삭제 실패"); } }else {
				 * System.out.println("이미지3 파일이 없음"); } } }
				 */
				if( rs.getString("image1") != null ) {
					file = new File( path+ rs.getString("image1") );
					if( file.exists() ) {
						if( file.delete() ) {
							System.out.println("이미지1 파일삭제 성공");
						}else {
							System.out.println("이미지1 파일삭제 실패");
						}
					}else {
						System.out.println("이미지1 파일이 없음");
					}
				}
				if( rs.getString("image2") != null ) {
					file = new File( path+ rs.getString("image2") );
					if( file.exists() ) {
						if( file.delete() ) {
							System.out.println("이미지2 파일삭제 성공");
						}else {
							System.out.println("이미지2 파일삭제 실패");
						}
					}else {
						System.out.println("이미지2 파일이 없음");
					}
				}
				if( rs.getString("image3") != null ) {
					file = new File( path+ rs.getString("image3") );
					if( file.exists() ) {
						if( file.delete() ) {
							System.out.println("이미지3 파일삭제 성공");
						}else {
							System.out.println("이미지3 파일삭제 실패");
						}
					}else {
						System.out.println("이미지3 파일이 없음");
					}
				}
			}
			
		} catch( Exception e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
			if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
			if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
		}
        return "성공";
	}

public String review_file_delete(String strSeq) {
	String realpath = System.getProperty("user.dir");
	String path = realpath.replace("\\", "/") + "/src/main/webapp/assets/images/review/";
	File file = null;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		conn = this.dataSource.getConnection();
		
		String sql = "select image1, image2, image3 from review where seq=?";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString(1, strSeq );
		rs = pstmt.executeQuery();
		
		while( rs.next() ) {
			if( rs.getString("image1") != null ) {
				file = new File( path+ rs.getString("image1") );
				if( file.exists() ) {
					if( file.delete() ) {
						System.out.println("이미지1 파일삭제 성공");
					}else {
						System.out.println("이미지1 파일삭제 실패");
					}
				}else {
					System.out.println("이미지1 파일이 없음");
				}
			}
			if( rs.getString("image2") != null ) {
				file = new File( path+ rs.getString("image2") );
				if( file.exists() ) {
					if( file.delete() ) {
						System.out.println("이미지2 파일삭제 성공");
					}else {
						System.out.println("이미지2 파일삭제 실패");
					}
				}else {
					System.out.println("이미지2 파일이 없음");
				}
			}
			if( rs.getString("image3") != null ) {
				file = new File( path+ rs.getString("image3") );
				if( file.exists() ) {
					if( file.delete() ) {
						System.out.println("이미지3 파일삭제 성공");
					}else {
						System.out.println("이미지3 파일삭제 실패");
					}
				}else {
					System.out.println("이미지3 파일이 없음");
				}
			}
		}
		
	} catch( Exception e ) {
		System.out.println( "[에러] " + e.getMessage() );
	} finally {
		if( rs != null ) try { rs.close(); } catch( SQLException e ) {}
		if( pstmt != null ) try { pstmt.close(); } catch( SQLException e ) {}
		if( conn != null ) try { conn.close(); } catch( SQLException e ) {}
	}
    return "성공";
}
	
}
