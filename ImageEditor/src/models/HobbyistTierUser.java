package models;


public class HobbyistTierUser extends FreeTierUser {
	public HobbyistTierUser(String nickname, String password, String realName, String surname, int age, String email) {
		
        super(nickname, password, realName, surname, age, email);
        System.out.println("hobby user is created");
    }
    
    // Methods specific to HobbyistTierUser
    public void applyBrightnessFilter(Photo photo, int brightness) {
        // Apply brightness filter to the photo
    }
    
    public void applyContrastFilter(Photo photo, int contrast) {
        // Apply contrast filter to the photo
    }

}
