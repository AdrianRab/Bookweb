package pl.arabowski.bookweb.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "publishers")
public class Publisher {

	public Publisher() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, max = 50)
	@NotNull
	private String name;
	
	@OneToMany(mappedBy= "publisher", cascade	= CascadeType.ALL, fetch= FetchType.EAGER)
	private Set<Book> books = new HashSet<>();
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
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

	@Override
	public String toString() {
		return "Publisher{" +
				"id=" + id +
				", name='" + name + '\'' +
				", books=" + books +
				", created=" + created +
				", updated=" + updated +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Publisher)) return false;
		Publisher publisher = (Publisher) o;
		return getId() == publisher.getId() && getName().equals(publisher.getName()) && Objects.equals(getBooks(), publisher.getBooks()) && Objects.equals(getCreated(), publisher.getCreated()) && Objects.equals(getUpdated(), publisher.getUpdated());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getBooks(), getCreated(), getUpdated());
	}
}
