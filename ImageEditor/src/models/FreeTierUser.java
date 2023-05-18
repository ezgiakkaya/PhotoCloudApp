package models;



public class FreeTierUser extends User {
	 public FreeTierUser(String nickname, String password, String realName, String surname, int age, String email) {
	        super(nickname, password, realName, surname, age, email);
	    }
	    
	    // Methods specific to FreeTierUser
	    public void applyBlurFilter(Photo photo) {
	        // Apply blur filter to the photo
	    }
	    
	    public void applySharpenFilter(Photo photo) {
	        // Apply sharpen filter to the photo
	    }
	}



