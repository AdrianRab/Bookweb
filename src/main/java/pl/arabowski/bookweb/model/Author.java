package pl.arabowski.bookweb.model;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, max=30)
	private String firstName;
	
	@Size(min=1, max = 40)
	private String lastName;
	
	@ManyToMany(cascade = {CascadeType.ALL} , mappedBy="authors")
	private Set<Book> books = new HashSet<>();
	
	@Size(max= 2000)
	@Column(columnDefinition = "VARCHAR(2000)")
	private String biography;
	
	@CreationTimestamp
	@DateTimeFormat(iso=ISO.DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	@Lob
	private byte[] authorPicture;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Author)) return false;
		Author author = (Author) o;
		return Objects.equals(getId(), author.getId()) && Objects.equals(getFirstName(), author.getFirstName()) && Objects.equals(getLastName(), author.getLastName()) && Objects.equals(getBooks(), author.getBooks()) && Objects.equals(getBiography(), author.getBiography()) && Objects.equals(getCreated(), author.getCreated()) && Objects.equals(getUpdated(), author.getUpdated()) && Arrays.equals(getAuthorPicture(), author.getAuthorPicture());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getId(), getFirstName(), getLastName(), getBooks(), getBiography(), getCreated(), getUpdated());
		result = 31 * result + Arrays.hashCode(getAuthorPicture());
		return result;
	}

	@Override
	public String toString() {
		return "Author{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", books=" + books +
				", biography='" + biography + '\'' +
				", created=" + created +
				", updated=" + updated +
				", authorPicture=" + Arrays.toString(authorPicture) +
				'}';
	}
}
