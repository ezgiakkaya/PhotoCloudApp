package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import filters.Filter;
import utils.ImageMatrix;

public class Photo implements Serializable {

	private User owner;
	private ImageMatrix image;
	private List<Filter> appliedFilters;
	private boolean isPrivate;
	private int likes;
	// private String filePath;
	private String name;
	//private static int id = 0;
	//private String extension;

	public Photo(User owner, ImageMatrix image, boolean isPrivate, int likes, String name) {
		this.owner = owner;
		this.image = image;
		this.isPrivate = isPrivate;
		this.appliedFilters = new ArrayList<>();
		this.likes = likes;
		this.name = name;
		//this.extension = extension;
	}
	
	public Photo(User owner) {
		this.owner=owner;
	}

	/*public Photo(User owner) {
		id++;
		this.owner = owner;
		name = id+"_" + owner.getNickname();
	}*/
	
	/*public static String generateName(String nickname)  {
		return id +"_" + nickname;
		 
	}

	
	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Photo.id = id;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}


	public void setName(String name) {
		this.name = name;
	}*/

	public String getName() {
		return name;
	}

	public User getOwner() {
		return owner;
	}

	public ImageMatrix getImage() {
		return image;
	}

	public List<Filter> getAppliedFilters() {
		return appliedFilters;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void upload() {
		// Logic for uploading the photo
	}

	public void modify() {
		// Logic for modifying the photo
	}

	public void save() {
		// Logic for saving the photo
	}

	public void applyFilter(Filter filter) {
		appliedFilters.add(filter);
		// Logic for applying the filter to the photo
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setImage(ImageMatrix image) {
		this.image = image;
	}

	public void setAppliedFilters(List<Filter> appliedFilters) {
		this.appliedFilters = appliedFilters;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

}
