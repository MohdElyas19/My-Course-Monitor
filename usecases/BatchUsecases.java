package com.cms.usecases;

import java.util.List;
import java.util.Scanner;

import com.cms.dao.BatchDao;
import com.cms.dao.BatchDaoImpl;
import com.cms.exceptions.BatchException;
import com.cms.model.Batch;

public class BatchUsecases {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER \n 1 to create \n 2 to update new batch \n 3 to view batch");
		int choice = sc.nextInt();
		BatchDao bdo = new BatchDaoImpl();
		
//		while(true) {
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
					
//					BatchDao bdo = new BatchDaoImpl();
					
					try {
						String result = bdo.createBatch(batch);
						System.out.println(result);
					} catch (BatchException e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 2:{
					System.out.println("Enter NumberOfStudents");
					int numOfStud = sc.nextInt();
					System.out.println("Enter CourseId");
					int courseId = sc.nextInt();
					
//					BatchDao bdo = new BatchDaoImpl();
					
					try {
						String result = bdo.updateCourse(numOfStud, courseId);
						System.out.println(result);
					} catch (BatchException e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				
				case 3 : {
					try {
						List<Batch> batchList = bdo.viewBatch();
						batchList.forEach(s -> System.out.println(s));
					} catch (BatchException e) {
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
