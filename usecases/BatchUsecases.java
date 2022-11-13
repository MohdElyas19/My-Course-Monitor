package com.cms.usecases;

import java.util.List;
import java.util.Scanner;

import com.cms.dao.BatchDao;
import com.cms.dao.BatchDaoImpl;
import com.cms.exceptions.BatchException;
import com.cms.model.Batch;

public class BatchUsecases {
	
	static Scanner sc = new Scanner(System.in);
	public static void fun() {
		
		System.out.println("\nPress '1' to create new Batch");
		System.out.println("Press '2' to update batch");
		System.out.println("Press '3' to view batch");
		System.out.println("Press '7' to go to admin role");
		int choice = sc.nextInt();
		BatchDao bdo = new BatchDaoImpl();
		
		switch(choice) {
			case 1:{
				System.out.println("Enter BatchId");
				int batchId = sc.nextInt();
				System.out.println("Enter CourseId");
				int courseId = sc.nextInt();
				System.out.println("Enter FacultyId");
				int facultyId = sc.nextInt();
				System.out.println("NumberOfStudent");
				int numberOfStudent = sc.nextInt();
				System.out.println("Enter BatchStartDate in YYYYmmdd");
				String batchStartDate = sc.next();
				System.out.println("Enter Duration");
				int duration = sc.nextInt();
				
				Batch batch = new Batch();
				batch.setBatchId(batchId);
				batch.setCourseId(courseId);
				batch.setFacultyId(facultyId);
				batch.setNumberOfStudents(numberOfStudent);
				batch.setBatchStartDate(batchStartDate);
				batch.setDuration(duration);
				
				try {
					String result = bdo.createBatch(batch);
					System.out.println(result);
				} catch (BatchException e) {
					System.out.println(e.getMessage());
				}
				fun();
				break;
			}
			case 2:{
				System.out.println("Enter new NumberOfStudents");
				int numOfStud = sc.nextInt();
				System.out.println("Enter CourseId");
				int courseId = sc.nextInt();
				
				try {
					String result = bdo.updateBatch(numOfStud, courseId);
					System.out.println(result);
				} catch (BatchException e) {
					System.out.println(e.getMessage());
				}
				fun();
				break;
			}
			
			case 3 : {
				try {
					List<Batch> batchList = bdo.viewBatch();
					batchList.forEach(s -> System.out.println(s));
				} catch (BatchException e) {
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
				System.out.println("Selected services not available\n");
				fun();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		fun();
	}

}
