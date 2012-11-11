package structures;

import play.data.validation.Constraints.Required;

public class CustomerForm {
	
	public static int PASSWORD_MIN_VALUE = 4;

	@Required
	public String login;
	
	public String email;
	
	@Required
	public String password; // Temporary (md5 hash or better should be used)
	
	public String password_confirmation; // Temporary (md5 hash or better should be used)

	public CustomerForm() {
		
	}
	
	public CustomerForm( String login, String email, String password ) {
		this.login = login;
		this.email = email;
		this.password = password;
	}

	/**
	 * Used during registration. Checks if password re-typing was correct
	 * @return
	 */
	public boolean check_password_confirmation() {
		return ( this.password.length() >= PASSWORD_MIN_VALUE && this.password.equals(this.password_confirmation) );
	}

	
}
