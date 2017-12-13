package eu.forkedbranch.jug.apiexposure.models;

public class User {
	private String firstName;
	private String lastName;
	private String id;

	public User() {
	}
	
	public User(String firstName, String lastName, String id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getId() {
		return id;
	}
		
}
