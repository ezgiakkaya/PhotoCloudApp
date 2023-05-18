package models;



public class ProfessionalTierUser extends HobbyistTierUser {
	public ProfessionalTierUser(String nickname, String password, String realName, String surname, int age, String email) {
        super(nickname, password, realName, surname, age, email);
    }
    
    // Methods specific to ProfessionalTierUser
    public void applyGrayscaleFilter(Photo photo) {
        // Apply grayscale filter to the photo
    }
    
    public void applyEdgeDetectionFilter(Photo photo) {
        // Apply edge detection filter to the photo
    }
}

