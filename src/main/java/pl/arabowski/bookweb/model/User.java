package pl.arabowski.bookweb.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;

	@Column(name = "username", nullable = false)
	private String username;
	private boolean enabled;
	private String password;
	private String email;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserRole role;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name="users_owned_books", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="book_id"))
	private Set<Book> owned = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name="users_read_books", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="book_id"))
	private Set<Book> read = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name="users_reading_books", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="book_id"))
	private Set<Book> reading = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name="users_wannaread_books", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="book_id"))
	private Set<Book> wannaRead = new HashSet<>();

	@ElementCollection
	private Map<Long, Double> rating = new HashMap<>();

	@CreationTimestamp
	@DateTimeFormat(iso=ISO.DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	public static User fromDto(UserDto userDto) {
		return User.builder()
				.username(userDto.getUsername())
				.email(userDto.getEmail())
				.password(new BCryptPasswordEncoder().encode(userDto.getPassword()))
				.build();
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return getId() == user.getId() && isEnabled() == user.isEnabled() && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getRole(), user.getRole()) && Objects.equals(getOwned(), user.getOwned()) && Objects.equals(getRead(), user.getRead()) && Objects.equals(getReading(), user.getReading()) && Objects.equals(getWannaRead(), user.getWannaRead()) && Objects.equals(getRating(), user.getRating()) && Objects.equals(getCreated(), user.getCreated()) && Objects.equals(getUpdated(), user.getUpdated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUsername(), isEnabled(), getPassword(), getEmail(), getRole(), getOwned(), getRead(), getReading(), getWannaRead(), getRating(), getCreated(), getUpdated());
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", enabled=" + enabled +
				", email='" + email + '\'' +
				", role=" + role +
				", owned=" + owned +
				", read=" + read +
				", reading=" + reading +
				", wannaRead=" + wannaRead +
				", rating=" + rating +
				", created=" + created +
				", updated=" + updated +
				'}';
	}
}
