package pl.arabowski.bookweb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "books")
public class Book {

	public Book() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id")
	private long id;
	
	@NotBlank
	@Size(max=100)
	private String title;

	@NotEmpty
	@ElementCollection
	private Set<String> genre  = new HashSet<>();
	
	@ElementCollection
	private List<Double> rating = new ArrayList<>();

	@Column(scale = 2, precision = 4)
	private double rate;
	
	@NotEmpty
	@ManyToMany
	@JoinTable(
			name = "Author_Book",
			joinColumns = @JoinColumn(name="book_id"),
			inverseJoinColumns = @JoinColumn(name="author_id")
		)
	private Set<Author> authors = new HashSet<>();
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="publisher_id", nullable = false)
	private Publisher publisher;
	
	@ISBN
	private String isbn;

	@Lob
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "cover_id", unique = true)
	private CoverImage cover;

	@CreationTimestamp
	@DateTimeFormat(iso=ISO.DATE)
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<String> getGenre() {
		return genre;
	}

	public void setGenre(Set<String> genre) {
		this.genre = genre;
	}


	public List<Double> getRating() {
		return rating;
	}

	public void setRating(List<Double> rating) {
		this.rating = rating;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public CoverImage getCover() {
		return cover;
	}

	public void setCover(CoverImage cover) {
		this.cover = cover;
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
	
	
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
