package com.cms.usecases;

import java.util.List;
import java.util.Scanner;

import com.cms.dao.CoursePlanDao;
import com.cms.dao.CoursePlanDaoImpl;
import com.cms.exceptions.CoursePlanException;
import com.cms.model.CoursePlan;

public class CoursePlanUsecases {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER \n 1 to create coursePlan \n 2 to update new coursePlan \n 3 to view coursePlan");
		int choice = sc.nextInt();
		CoursePlanDao cpdo = new CoursePlanDaoImpl();
		
//		while(true) {
			switch(choice) {
				case 1:{
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
				case 2:{
					System.out.println("Enter topic");
					String topic = sc.next();
					System.out.println("Enter status - Completed/Pending");
					String status = sc.next();
					
					try {
						String result = cpdo.updateCoursePlan(topic, status);
						System.out.println(result);
					} catch (CoursePlanException e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				
				case 3 : {
					try {
						List<CoursePlan> coursePlanList = cpdo.viewCoursePlan();
						coursePlanList.forEach(s -> System.out.println(s));
					} catch (CoursePlanException e) {
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
