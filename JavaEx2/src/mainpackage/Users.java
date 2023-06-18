package mainpackage;

import java.util.Scanner;

public class Users {
	
	public Users() {
		//System.out.println("Super class constructor"); 
	}
	
	String name = "asdasd";
	String username;
	String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	static void login() {

	}
	
	static void logout() {

	}
	
	static class Customers extends Users
	{
			public Customers() {
				
			}
			
			public void showAvailableFilms(String[] tainies) {
				
			}
			
			public void makeReservation(String provoli,int theseis) {
				if(theseis < 6)
				{
					System.out.println("ekleises " + theseis + " theseis sthn provolh " + provoli);
				}else
				{
					System.out.println("sorry den exei alla eishthria gia thn tainia " + provoli);
				}
			}
			
			public void viewReservation() {
				
			}
	}

	static class ContentAdmins extends Users
	{
		public ContentAdmins() {

		}
		
		public void insertFilm(String film) {
			
		}
		
		public void deleteFilm(String film) {
			
		}
		
		public void assignFilmToCinema() {
			
		}
		
	}

	static class Admins extends Users
	{

		public Admins() {
			//System.out.println("Subclass constructor invoked"); 
		}
		
		public void createUser(String user) {
			
		}
		
		public void updateUser(String user) {
					
		}
		
		public void deleteUser(String userToDelete) {
			
		}
		
		public void searchUser(String userToSearch) {
			
		}
		
		public void viewAllUsers() {
			
		}
		
		public void registerAdmin() {
			
		}
		
	}
}
