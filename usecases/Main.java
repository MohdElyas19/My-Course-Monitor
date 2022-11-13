package com.cms.usecases;

import java.util.List;
import java.util.Scanner;

import com.cms.dao.CoursePlanDao;
import com.cms.dao.CoursePlanDaoImpl;
import com.cms.dao.FacultyDao;
import com.cms.dao.FacultyDaoImpl;
import com.cms.dao.LoginDao;
import com.cms.dao.LoginDaoImpl;
import com.cms.exceptions.CoursePlanException;
import com.cms.exceptions.FacultyException;
import com.cms.exceptions.LoginException;
import com.cms.model.CoursePlan;
import com.cms.model.Faculty;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void startMenu() {
		System.out.println("\n	********* WELCOME USER TO THE COURSE MONITORING SYSTEM *********\n");
		System.out.println("		 	------- Press" +" '1' "+ "FOR ADMIN -------   \n	  	 	------- Press"+" '2' "+ "FOR FACULTY ------- ");
		System.out.println("		 	------- Press '11' to stop -------   ");
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1: {
				admin();
				break;
			}
			case 2: {
				faculty();
				break;
			}
			case 11: {
				System.out.println("\n\n		   THANKS FOR USING COURSE MONITORING SYSTEM");
				break;
			}
			default: {
				System.out.println("Selected Services Not Applicable");
				startMenu();
			}
		}
		
	}
	
	public static void admin() {
		System.out.println("			------- WELCOME TO ADMIN -------\n");
		System.out.println("Enter Admin Username");
		String userName = sc.next();
		System.out.println("Enter Admin Password");
		String password = sc.next();
		LoginDao ldo = new LoginDaoImpl();
		try {
			ldo.signInAdmin(userName, password);
			adminRole();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void adminRole() {
		System.out.println("\nPress '1' for Course");
		System.out.println("Press '2' for Batch");
		System.out.println("Press '3' for Faculty");
		System.out.println("Press '4' for Allocate Faculty to a Batch");
		System.out.println("Press '5' for CoursePlan");
		System.out.println("Press '6' for the View of Day wise update of every batch");
		System.out.println("Press '7' for generating report for every batch");
		System.out.println("Press '9' to go to the main menu");
		System.out.println("Press '11' to stop");
		int adminChoice = sc.nextInt();
		
		switch(adminChoice) {
			case 1: {
				adminCourse();
				break;
			}
			case 2: {
				adminBatch();
				break;
			}
			case 3: {
				adminFaculty();
				break;
			}
			case 4: {
				adminFacultyToBatch();
				break;
			}
			case 5: {
				adminCoursePlan();
				break;
			}
			case 6 : {
				adminDayWiseUpdate();
				break;
			}
			case 7: {
				adminGenerateReport();
				break;
			}
			case 9: {
				System.out.println("Welcome to the Main Menu");
				startMenu();
				break;
			}
			case 11: {
				System.out.println("\n\n		   THANKS FOR USING COURSE MONITORING SYSTEM");
				break;
			}
			default: {
				System.out.println("Invalid option");
				adminRole();
			}
		}
	}
	
	public static void adminCourse() {
		System.out.println("\n			------- WELCOME TO COURSE -------\n");
		CourseUsecases cu = new CourseUsecases();
		cu.fun();
		
		adminRole();
	}
	public static void adminBatch() {
		System.out.println("\n			------- WELCOME TO BATCH -------\n");
		BatchUsecases bu = new BatchUsecases();
		bu.fun();
		
		adminRole();
	}
	public static void adminFaculty() {
		System.out.println("\n			------- WELCOME TO FACULTY -------\n");
		FacultyUsecases fu = new FacultyUsecases();
		fu.fun();
		
		adminRole();
	}
	public static void adminFacultyToBatch() {
		System.out.println("\n			------- WELCOME TO ADMIN TO FACULTY -------\n");
		
		adminRole();
	}
	public static void adminCoursePlan() {
		System.out.println("\n			------- WELCOME TO COURSE PLAN -------\n");
		CoursePlanUsecases cpu = new CoursePlanUsecases();
		cpu.fun();
		
		adminRole();
	}
	public static void adminDayWiseUpdate() {
		System.out.println("\n			------- WELCOME TO UPDATE DAYWISE -------\n");
		
		adminRole();
	}
	public static void adminGenerateReport() {
		System.out.println("\n			------- WELCOME TO GENERATING REPORT -------\n");
		
		adminRole();
	}
	
	public static void faculty() {
		System.out.println("\n			------- WELCOME TO FACULTY -------\n");
		System.out.println("Enter Faculty Username");
		String username = sc.next();
		System.out.println("Enter Faculty Password");
		String password = sc.next();
		
		LoginDao ldo = new LoginDaoImpl();
		try {
			Faculty faculty = ldo.signInFaculty(username, password);
			System.out.println("	--------- Welcome Mr/Mrs "+faculty.getUserName().toUpperCase() +" to the COURSE MONITORING SYSTEM --------- ");
			facultyRole();
		} catch (LoginException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void facultyRole() {
//		System.out.println("Welcome to faculty role");
		
		System.out.println("\nEnter '1' to view Course Plan");
		System.out.println("Enter '2' to fill up the day wise planner");
		System.out.println("Enter '3' to update password");
		System.out.println("Enter '9' to go to main menu");
		System.out.println("Enter '11' to stop");
		
		int facultyChoice = sc.nextInt();
		CoursePlanDao cpdo = new CoursePlanDaoImpl();
		FacultyDao fdo = new FacultyDaoImpl();
		
		switch(facultyChoice) {
			case 1: {
				try {
					List<CoursePlan> coursePlanList = cpdo.viewCoursePlan();
					coursePlanList.forEach(s -> System.out.println(s));
				} catch (CoursePlanException e) {
					System.out.println(e.getMessage());
				}
//				System.out.println("\n\nTo Continue....");
				facultyRole();
				break;
			}
			case 2: {
				System.out.println("Enter PlanId");
				int planId = sc.nextInt();
				System.out.println("Enter BatchId");
				int batchId = sc.nextInt();
				System.out.println("Enter DayNumber");
				int dayNumber = sc.nextInt();
				System.out.println("topic");
				String topic = sc.next();
				System.out.println("Enter status- Completed/Pending ");
				String status = sc.next();
				
				CoursePlan coursePlan = new CoursePlan();
				coursePlan.setPlanId(planId);
				coursePlan.setBatchId(batchId);
				coursePlan.setDayNumber(dayNumber);
				coursePlan.setTopic(topic);
				coursePlan.setStatus(status);
				
				try {
					String result = cpdo.createCoursePlan(coursePlan);
					System.out.println(result);
				} catch (CoursePlanException e) {
					System.out.println(e.getMessage());
				}
//				System.out.println("\n\nTo Continue....");
				facultyRole();
				break;
			}
			case 3: {
				System.out.println("Enter new Faculty password");
				String facultypass = sc.next();
				System.out.println("Enter Username");
				String facultyuserName = sc.next();
				
				try {
					String result = fdo.updateFaculty(facultypass, facultyuserName);
					System.out.println(result);
				} catch (FacultyException e) {
					System.out.println(e.getMessage());
				}
//				System.out.println("\n\nTo Continue....");
				facultyRole();
				break;
			}
			case 9: {
				System.out.println("Welcome to the Main Menu");
				startMenu();
				break;
			}
			case 11: {
				System.out.println("\n\n		   THANKS FOR USING COURSE MONITORING SYSTEM");
				break;
			}
			default: {
				System.out.println("Invalid option");
				facultyRole();
			}
		}
	}
	
	public static void main(String[] args) {
		startMenu();
	}

}
