package test.freedom.search.entity;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;

	private String usercode ;
	private String username ;
	
	@Override
	public String toString() {
		return "User []";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User(String usercode, String username) {
		super();
		this.usercode = usercode;
		this.username = username;
	}
	
	
	
	
}
