package com.cms.usecases;

import java.util.List;
import java.util.Scanner;

import com.cms.dao.CourseDao;
import com.cms.dao.CourseDaoImpl;
import com.cms.exceptions.CourseException;
import com.cms.model.Course;

public class CourseUsecases {
	
	static Scanner sc = new Scanner(System.in);
	public static void fun() {
		System.out.println("\nPress '1' to create new Course");
		System.out.println("Press '2' to update Course");
		System.out.println("Press '3' to view Course");
		System.out.println("Press '7' to go to admin role");
		int choice = sc.nextInt();
		CourseDao cdo = new CourseDaoImpl();
		
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
				fun();
				break;
			}
			case 2:{
				System.out.println("Enter new CourseFee");
				int courseFee = sc.nextInt();
				System.out.println("Enter CourseName");
				String couseName = sc.next();
				
				try {
					String result = cdo.updateCourse(courseFee, couseName);
					System.out.println(result);
				} catch (CourseException e) {
					System.out.println(e.getMessage());
				}
				fun();
				break;
			}
			
			case 3 : {
				try {
					List<Course> courseList = cdo.viewCourse();
					courseList.forEach(s -> System.out.println(s));
				} catch (CourseException e) {
					System.out.println(e.getMessage());
				}
				fun();
				break;
			}
			case 7: {
				System.out.println("Redirecting page to admin role");
				break;
			}
			default: {
				System.out.println("Selected services not available");
				fun();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		fun();
	}

}
