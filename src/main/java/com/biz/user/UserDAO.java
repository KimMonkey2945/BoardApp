package com.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biz.common.JDBCUtil;


public class UserDAO {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	private String USER_LIST = "select * from users";
	private String USER_INSERT = "insert into users values(?, ?, ?, ?)";
	private String USER_UPDATE = "update users set name = ?, role = ? where id = ?";
	private String USER_DELETE = "delete users where id = ?";
	private String USER_GET = "select * from users where id = ?";
	
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			rs = stmt.executeQuery();
			while (rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
	}
	
	public void insertUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_INSERT);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getRole());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public void updateUserVO(UserVO vo) {
		try {
			
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_UPDATE);
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getRole());
			stmt.setString(3, vo.getId());
			stmt.executeUpdate();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	
	
	
	
}
