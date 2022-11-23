package pl.arabowski.bookweb.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="user_roles")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserRole {
	
	@Id
	private long id;
	
	@OneToOne
	@MapsId
	private User user;
	
	@NotNull
	private String role;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserRole)) return false;
		UserRole userRole1 = (UserRole) o;
		return getId() == userRole1.getId() && Objects.equals(getRole(), userRole1.getRole());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getRole());
	}

	@Override
	public String toString() {
		return "UserRole{" +
				"id=" + id +
				", userRole='" + role + '\'' +
				'}';
	}
}
