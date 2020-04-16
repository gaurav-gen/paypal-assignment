/**
 * 
 */
package com.paypal.assignment.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.assignment.dto.request.CreateBookRequest;
import com.paypal.assignment.dto.request.UpdateBookRequest;
import com.paypal.assignment.dto.response.ListBookResponse;
import com.paypal.assignment.entities.Book;
import com.paypal.assignment.entities.Library;
import com.paypal.assignment.repositories.BookRepository;
import com.paypal.assignment.repositories.LibraryRepository;

/**
 * @author gaurav
 *
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private LibraryRepository libraryRepository;

	@Override
	public Book create(CreateBookRequest createBookRequest) {
		Book book = new Book(createBookRequest.getTitle(), createBookRequest.getAuthor(),
				fetchLibrary(createBookRequest.getLibraryId()));
		return bookRepository.save(book);
	}

	@Override
	public Book update(UpdateBookRequest updateBookRequest) {
		Optional<Book> optionalBook = bookRepository.findById(updateBookRequest.getId());
		if (optionalBook.isEmpty()) {
			throw new EntityNotFoundException("Could not find the Book to be edited");
		} else {
			Book book = optionalBook.get();
			book.setTitle(updateBookRequest.getTitle());
			book.setAuthor(updateBookRequest.getAuthor());
			book.setLibrary(fetchLibrary(updateBookRequest.getLibraryId()));
			return bookRepository.save(book);
		}
	}

	private Library fetchLibrary(Long id) {
		Optional<Library> optionalLibrary = libraryRepository.findById(id);
		if (optionalLibrary.isPresent())
			return optionalLibrary.get();
		else {
			throw new EntityNotFoundException("Could not find the library");
		}
	}

	@Override
	public Book get(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isEmpty()) {
			throw new EntityNotFoundException("Could not find the Book");
		} else {
			return optionalBook.get();
		}
	}

	@Override
	public ListBookResponse getAllByLibrary(Long libraryId) {
		ListBookResponse listBookResponse = new ListBookResponse();
		listBookResponse.setBooks(bookRepository.findAllByLibraryId(libraryId));
		return listBookResponse;
	}

}
