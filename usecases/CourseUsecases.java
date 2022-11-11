package com.cms.usecases;

import java.util.List;
import java.util.Scanner;

import com.cms.dao.CourseDao;
import com.cms.dao.CourseDaoImpl;
import com.cms.exceptions.CourseException;
import com.cms.model.Course;

public class CourseUsecases {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER \n 1 to create course \n 2 to update new course \n 3 to view course");
		int choice = sc.nextInt();
		CourseDao cdo = new CourseDaoImpl();
		
//		while(true) {
			switch(choice) {
				case 1:{
					System.out.println("Enter CourseId");
					int CourseId = sc.nextInt();
					System.out.println("Enter CourseName");
					String CourseName = sc.next();
					System.out.println("Enter Fee");
					int fee = sc.nextInt();
					System.out.println("CourseDescription");
					String courseDescription = sc.next();
					
					Course course = new Course();
					course.setCourseId(CourseId);
					course.setCourseName(CourseName);
					course.setFee(fee);
					course.setCourseDescription(courseDescription);
					
					try {
						String result = cdo.createCourse(course);
						System.out.println(result);
					} catch (CourseException e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 2:{
					System.out.println("Enter CourseFee");
					int courseFee = sc.nextInt();
					System.out.println("Enter CourseName");
					String couseName = sc.next();
					
					try {
						String result = cdo.updateCourse(courseFee, couseName);
						System.out.println(result);
					} catch (CourseException e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				
				case 3 : {
					try {
						List<Course> courseList = cdo.viewCourse();
						courseList.forEach(s -> System.out.println(s));
					} catch (CourseException e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				default: {
					System.out.println("Selected services not available");
				}
				
			}
//		}
	}

}
