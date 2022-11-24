package pl.arabowski.bookweb.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="user_roles")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserRole {
	
	@Id
	private Long id;
	
	@OneToOne
	@MapsId
	private User user;
	
	@NotNull
	@Column(name = "user_role")
	private String role;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserRole)) return false;
		UserRole userRole = (UserRole) o;
		return getId() == userRole.getId() && getRole().equals(userRole.getRole());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getRole());
	}

	@Override
	public String toString() {
		return "UserRole{" +
				"id=" + id +
				", role='" + role + '\'' +
				'}';
	}
}
