package com.ayadykin.ggstars.test.library.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.ayadykin.ggstars.test.library.entity.enums.Genre;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "BOOK")
public class Book implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;

	@Column(name = "ISBN", unique = true)
	private String isbn;

	@Enumerated(EnumType.STRING)
	private Genre genre;

	@ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
	private Collection<Author> authors = new ArrayList<>();

	public boolean addAuthor(Author author) {
		return authors.add(author);
	}
}
