package br.ufrn.umbrella.caronasufrn.models.enums;

public enum UserRoles {
	ADMIN("ADMIN"),
	USER("USER");
	
	public final String value;
	
	UserRoles(String value) {
		this.value = value;
	}
}
