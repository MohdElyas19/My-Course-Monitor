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

public class loginUsecases {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		LoginDao ldo = new LoginDaoImpl();
		CoursePlanDao cpdo = new CoursePlanDaoImpl();
		FacultyDao fdo = new FacultyDaoImpl();
		
		System.out.println("\n	********* WELCOME USER TO THE LOGIN PAGE OF COURSE MONITORING SYSTEM *********\n\n");
		System.out.println("		 	------- Press" +" '1' "+ "FOR ADMIN -------   \n	  	 	------- Press"+" '2' "+ "FOR FACULTY ------- ");
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1: {
				System.out.println("Enter Admin Username");
				String userName = sc.next();
				System.out.println("Enter Admin Password");
				String password = sc.next();
				try {
					ldo.signInAdmin(userName, password);
					int adminChoice = sc.nextInt();
					
					System.out.println("Press '1' for Course");
					System.out.println("Press '2' for Batch");
					System.out.println("Press '3' for Faculty");
					System.out.println("Press '4' for Allocate Faculty to a Batch");
					System.out.println("Press '5' for CoursePlan");
					System.out.println("Press '6' for the View of Day wise update of every batch");
					System.out.println("Press '7' for generating report for every batch");
					System.out.println("Press '9' to go to the main menu");
					
					switch(adminChoice) {
					 case 1: {
						 System.out.println("Welcome to Course");
						 break;
					 }
					 case 2: {
						 System.out.println("Welcome to Batch");
						 break;
					 }
					 case 3: {
						 System.out.println("Welcome to Faculty");
						 break;
					 }
					 case 4: {
						 System.out.println("Welcome to Allocating Faculty to batch");
						 break;
					 }
					 case 5: {
						 System.out.println("Welcome to CoursePlan");
						 break;
					 }
					 case 6: {
						 System.out.println("Welcome to daywise update");
						 break;
					 }
					 case 7: {
						 System.out.println("Welcome to Generating report");
						 break;
					 }
					 case 9: {
						 System.out.println("Welcome to Main menu");
						 break;
					 }
					 default: {
						 System.out.println("Invalid service applied");
					 }
					}
					
				} catch (LoginException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case 2: {
				System.out.println("Enter Faculty Username");
				String userName = sc.next();
				System.out.println("Enter Faculty Password");
				String pass = sc.next();
				
				try {
					Faculty faculty = ldo.signInFaculty(userName, pass);
					System.out.println("	--------- Welcome Mr/Mrs "+faculty.getUserName().toUpperCase() +" to the COURSE MONITORING SYSTEM --------- ");
					
					System.out.println("\n\nPress '1' to view Course Plan");
					System.out.println("Press '2' to fill up the day wise planner");
					System.out.println("Press '3' to update his/her password");
					System.out.println("Press '9' to go to the main menu");
					int facultyChoice = sc.nextInt();
					switch(facultyChoice) {
						case 1: {
							try {
								List<CoursePlan> coursePlanList = cpdo.viewCoursePlan();
								coursePlanList.forEach(s -> System.out.println(s));
							} catch (CoursePlanException e) {
								System.out.println(e.getMessage());
							}
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
							break;
						}
						case 3: {
							System.out.println("Enter new password");
							String facultypass = sc.next();
							System.out.println("Enter Username");
							String facultyuserName = sc.next();
							
							try {
								String result = fdo.updateFaculty(facultypass, facultyuserName);
								System.out.println(result);
							} catch (FacultyException e) {
								System.out.println(e.getMessage());
							}
							break;
						}
						case 9: {
							loginUsecases lc = new loginUsecases();
							System.out.println("Welcome to the main menu");
							break;
						}
						default: {
							System.out.println("Invalid option");
						}
					}
					
				} catch (LoginException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			}
			default: {
				System.out.println("Selected Services Unavailable");
			}
		}
		
		
	}

}
