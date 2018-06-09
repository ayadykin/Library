package com.ayadykin.ggstars.test.library.init;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayadykin.ggstars.test.library.entity.Author;
import com.ayadykin.ggstars.test.library.entity.Book;
import com.ayadykin.ggstars.test.library.entity.Genre;
import com.ayadykin.ggstars.test.library.entity.Reward;
import com.ayadykin.ggstars.test.library.entity.Sex;
import com.ayadykin.ggstars.test.library.repository.AuthorRepository;
import com.ayadykin.ggstars.test.library.repository.BookRepository;
import com.ayadykin.ggstars.test.library.utils.Constants;

@Service
public class Init {

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;

	@Transactional
	@PostConstruct
	public void init() {

		Book book1 = createBook("Кавказский пленник", "1233", Genre.POEM);
		bookRepository.save(book1);
		Book book2 = createBook("Дубровский", "1234", Genre.PROSE);
		bookRepository.save(book2);
		Book book3 = createBook("Двенадцать", "1235", Genre.POEM);
		bookRepository.save(book3);

		Author author1 = createAuthor("Alexander", "Pushkin", Sex.MALE, "26.05.1799");
		author1.addBook(book1);
		author1.addBook(book2);
		author1.addReward(createReward(1937, "The town of Tsarskoye Selo was renamed Pushkin in his honour"));
		author1.addReward(createReward(2009, "Pushkin Monument was erected in Asmara"));
		authorRepository.save(author1);

		Author author2 = createAuthor("Александр", "Блок", Sex.MALE, "19.11.1880");
		author2.addBook(book2);
		author2.addBook(book3);
		author2.addReward(createReward(1939, "Улица в Ленинграде"));
		author2.addReward(createReward(2001, "Именем поэта назван астероид"));
		authorRepository.save(author2);

		Author author3 = createAuthor("Раиса", "Ахматова", Sex.FIMALE, "13.12.1928");
		authorRepository.save(author3);

		Author author4 = createAuthor("Владимир", "Беляев", Sex.MALE, "21.03.1909");
		authorRepository.save(author4);

		Author author5 = createAuthor("Юлий", "Бунин", Sex.MALE, "19.07.1857");
		authorRepository.save(author5);
	}

	private Author createAuthor(String firstName, String lastName, Sex sex, String birthDate) {
		Author author = new Author();
		author.setFirstName(firstName);
		author.setLastName(lastName);
		author.setSex(sex);
		author.setBirthDate(LocalDate.parse(birthDate, DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)));
		return author;
	}

	private Book createBook(String title, String isbn, Genre genre) {
		Book book = new Book();
		book.setTitle(title);
		book.setIsbn(isbn);
		book.setGenre(genre);
		return book;
	}

	private Reward createReward(int year, String title) {
		return new Reward(year, title);
	}
}