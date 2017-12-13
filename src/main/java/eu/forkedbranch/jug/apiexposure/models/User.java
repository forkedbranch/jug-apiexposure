package eu.forkedbranch.jug.apiexposure.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
		description = "User resource model"
		)
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

	@ApiModelProperty(
			value = "user first name",
			required = true
			)
	public String getFirstName() {
		return firstName;
	}

	@ApiModelProperty(
			value = "user last name"
			)
	public String getLastName() {
		return lastName;
	}

	@ApiModelProperty(
			value = "user ID",
			dataType = "string",
			example = "905a9d3f-87e8-499e-9d56-25472abf4e1c"
			)
	public String getId() {
		return id;
	}		
}

