package model.enums;

public enum Gender {
	MALE("Мужской"),
	FEMALE("Женский");

	final String gender;

	Gender(String gender) {
		this.gender = gender;
	}

	public final String getGender() {
		return gender;
	}
}
