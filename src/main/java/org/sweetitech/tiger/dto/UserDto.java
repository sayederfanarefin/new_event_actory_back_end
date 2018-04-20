package org.sweetitech.tiger.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sweetitech.tiger.model.Image;

//@PasswordMatches
public class UserDto {
    public UserDto(String firstName, String lastName, String password, String email, String userName, String phoneNumber, Integer role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
	}

	@NotNull
    @Size(min = 1, message = "first name")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "last name")
    private String lastName;
    
    private String phoneNumber;

    private Image profilePicture;
    
    private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
  public Image getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(Image profilePicture) {
		this.profilePicture = profilePicture;
	}

public UserDto() {
		super();
		
	}

public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	//  @ValidPassword
    private String password;


  //  @ValidEmail
    @NotNull
    @Size(min = 1, message = "email")
    private String email;

    //private boolean isUsing2FA;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(final Integer role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

	@Override
	public String toString() {
		return "UserDto [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", email="
				+ email + ", role=" + role + "]";
	}

    

}
