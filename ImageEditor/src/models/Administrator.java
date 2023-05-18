package models;


public class Administrator extends ProfessionalTierUser {
	    public Administrator(String nickname, String password, String realName, String surname, int age, String email) {
	        super(nickname, password, realName, surname, age, email);
	    }

	    public void removePhotoFromDiscover(Photo photo) {
	        // Code to remove the photo from the Discover page
	    }

	
	   /* public void removePhotoFromProfile(Photo photo) {
	        super.removePhotoFromProfile(photo);
	        // Additional code to remove the photo from the owner's profile page
	    }*/
	}

