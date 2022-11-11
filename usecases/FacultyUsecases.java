package com.cms.usecases;

import java.util.List;
import java.util.Scanner;

import com.cms.dao.FacultyDao;
import com.cms.dao.FacultyDaoImpl;
import com.cms.exceptions.FacultyException;
import com.cms.model.Faculty;

public class FacultyUsecases {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER \n 1 to create Faculty \n 2 to update new Faculty \n 3 to view Faculty");
		int choice = sc.nextInt();
		FacultyDao fdo = new FacultyDaoImpl();
		
//		while(true) {
			switch(choice) {
				case 1:{
					System.out.println("Enter FacultyId");
					int facultyId = sc.nextInt();
					System.out.println("Enter FacultyName");
					String facultyName = sc.next();
					System.out.println("Enter FacultyAddress");
					String facultyAddress = sc.next();
					System.out.println("Mobile");
					String mobile = sc.next();
					System.out.println("Enter Email");
					String email = sc.next();
					System.out.println("Enter UserName");
					String userName = sc.next();
					System.out.println("Enter Password");
					String pass = sc.next();
					
					Faculty faculty = new Faculty();
					faculty.setFacultyId(facultyId);
					faculty.setFacultyName(facultyName);
					faculty.setFacultyAddress(facultyAddress);
					faculty.setMobile(mobile);
					faculty.setEmail(email);
					faculty.setUserName(userName);
					faculty.setPassword(pass);
					
					try {
						String result = fdo.createFaculty(faculty);
						System.out.println(result);
					} catch (FacultyException e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 2:{
					System.out.println("Enter password");
					String password = sc.next();
					System.out.println("Enter userName");
					String userName = sc.next();
					
					try {
						String result = fdo.updateFaculty(password, userName);
						System.out.println(result);
					} catch (FacultyException e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				
				case 3 : {
					try {
						List<Faculty> facultyList = fdo.viewFaculty();
						facultyList.forEach(s -> System.out.println(s));
					} catch (FacultyException e) {
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
