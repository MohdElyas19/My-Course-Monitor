package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cms.exceptions.CourseException;
import com.cms.model.Course;
import com.cms.utility.DbUtil;

public class CourseDaoImpl implements CourseDao {

	@Override
	public String createCourse(Course course) throws CourseException {
		String result = "Course Not inserted";
		
		try(Connection conn = DbUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into Course values(?,?,?,?)");
			
			ps.setInt(1,course.getCourseId());
			ps.setString(2,course.getCourseName());
			ps.setInt(3,course.getFee());
			ps.setString(4,course.getCourseDescription());
			
			int x = ps.executeUpdate();
			if(x > 0) {
				result = "Course Registered Successfully";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseException(e.getMessage());
		}
		
		return result;
	}

	@Override
	public String updateCourse(int courseFee, String courseName) throws CourseException {
		String result = "Course Not Updated";
		
		try(Connection conn = DbUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("update Course set fee = ? where courseName = ?");
			
			ps.setInt(1,courseFee);
			ps.setString(2,courseName);
			
			int x = ps.executeUpdate();
			if(x > 0) {
				result = "Course Updated Successfully";
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseException(e.getMessage());
		}
		
		return result;
	}

	@Override
	public List<Course> viewCourse() throws CourseException {
		
		List<Course> courseList = new ArrayList<>();
		
		try(Connection conn = DbUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from Course");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("courseName"));
				course.setFee(rs.getInt("fee"));
				course.setCourseDescription(rs.getString("courseDescription"));
				
				courseList.add(course);
			}
			
			if(courseList.size() == 0) {
				throw new CourseException("No data found in this course");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseException(e.getMessage());
		}
		
		return courseList;
	}

}

