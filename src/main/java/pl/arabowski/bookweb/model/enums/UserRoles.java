package pl.arabowski.bookweb.model.enums;

public enum UserRoles {
	USER{
		public String toString() {
			return "ROLE_USER";
		}
	},
	
	ADMIN{
		public String toString() {
			return "ROLE_ADMIN";
		}
	}
}
