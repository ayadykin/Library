package com.ayadykin.ggstars.test.library.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ayadykin.ggstars.test.library.entity.enums.Sex;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "AUTHOR")
public class Author implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Column(name = "first_name")
	private String firstName;

	@NotEmpty
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Sex sex;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "author_book", joinColumns = { @JoinColumn(name = "author_id") }, inverseJoinColumns = {
			@JoinColumn(name = "book_id") })
	private Collection<Book> books = new ArrayList<>();

	@NotNull
	@Column(name = "birth_date")
	private LocalDate birthDate;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "author_reward", joinColumns = @JoinColumn(name = "author_id"))
	private Collection<Reward> rewards = new ArrayList<>();

	public boolean addBook(Book book) {
		return books.add(book);
	}

	public boolean addReward(Reward reward) {
		return rewards.add(reward);
	}
}
