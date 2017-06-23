package com.nandhu.dive.Dao;

import java.sql.SQLException;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nandhu.dive.model.User;

import org.springframework.stereotype.Repository;



	@Repository
	public class UserDao {
		
		private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

		@Autowired
		private JdbcTemplate jdbcTemplate;

		public List<User> findAll() {
			String sql = "select id,name,email,active from user_account";

			List<User> userList = jdbcTemplate.query(sql, (rs, rowno) -> {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setActive(rs.getBoolean("active"));
				return user;
			});
			LOGGER.info("List of users retrieval success");
			return userList;
		}

		public boolean login(String email,String password) throws SQLException {
			
		Boolean Status = false;
		try{
				String query = "select email,password from  user_account where email =? and password =?";
				Object[] args = new Object[] {email,password};
				User userList = jdbcTemplate.queryForObject(query, args, (rs, rowno) -> {
					User user = new User();
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					if(user.getEmail().equals(email)&& user.getPassword().equals(password)){
					}
					return user;
			
				
				});
				Status = true;
				LOGGER.info("user retrieval success");
		}
		catch(Exception e){
			Status = false;
			LOGGER.debug("User id not found - ID{}");
			
		}
		return Status;
				
			
		}

		public User findOne(Integer id) {
			String sql = "select id,name,email,active from user_account where id = ?";
			Object[] params = new Object[] { id };
			User userList = jdbcTemplate.queryForObject(sql, params, (rs, rowno) -> {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setActive(rs.getBoolean("active"));
				return user;
			});
			return userList;
		}

		public void save(User user) {

			String sql = "insert into user_account ( name, email, password)  values ( ?, ? , ? )";
			Object[] params = new Object[] { user.getName(), user.getEmail(), user.getPassword() };
			int rows = jdbcTemplate.update(sql, params);
			System.out.println("No of rows inserted:" + rows);
		}

		public void update(User user) {

			String sql = "update user_account set name= ?, email = ? , password = ?  ,active = ?  where id = ?";
			Object[] params = new Object[] { user.getName(), user.getEmail(), user.getPassword(), user.isActive(),
					user.getId() };
			int rows = jdbcTemplate.update(sql, params);
			System.out.println("No of rows updated:" + rows);
		}

		public void delete(Integer id) {

			String sql = "delete from user_account where id = ?";
			Object[] params = new Object[] { id };
			int rows = jdbcTemplate.update(sql, params);
			System.out.println("No of rows deleted:" + rows);
		}

		public void activateAccount(Integer id) {

			String sql = "update user_account set active = 1 where id = ?";
			Object[] params = new Object[] { id };
			int rows = jdbcTemplate.update(sql, params);
			System.out.println("No of rows updated:" + rows);
		}

		public void deActivateAccount(Integer id) {

			String sql = "update user_account set active = 0 where id = ?";
			Object[] params = new Object[] { id };
			int rows = jdbcTemplate.update(sql, params);
			System.out.println("No of rows updated:" + rows);
		}

	}

