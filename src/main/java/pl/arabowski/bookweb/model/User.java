package pl.arabowski.bookweb.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users")
public class User {
	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;

	@Size(min = 2)
	@Column(name = "username", nullable = false)
	private String username;

	private boolean enabled;

	@NotNull
	@Size(min = 2)
	private String password;

	@Email
	@NotNull
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
	private Map<Long, Integer> rating = new HashMap<>();

	@CreationTimestamp
	@DateTimeFormat(iso=ISO.DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@Transient
	private String passwordConfirmed;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Set<Book> getRead() {
		return read;
	}

	public void setRead(Set<Book> read) {
		this.read = read;
	}

	public Set<Book> getReading() {
		return reading;
	}

	public void setReading(Set<Book> reading) {
		this.reading = reading;
	}

	public Set<Book> getWannaRead() {
		return wannaRead;
	}

	public void setWannaRead(Set<Book> wannaRead) {
		this.wannaRead = wannaRead;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getPasswordConfirmed() {
		return passwordConfirmed;
	}

	public void setPasswordConfirmed(String passwordConfirmed) {
		this.passwordConfirmed = passwordConfirmed;
	}

	public Set<Book> getOwned() {
		return owned;
	}

	public void setOwned(Set<Book> owned) {
		this.owned = owned;
	}

	public Map<Long, Integer> getRating() {
		return rating;
	}

	public void setRating(Map<Long, Integer> rating) {
		this.rating = rating;
	}

}
