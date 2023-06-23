package com.umaru.evstats.service;

import com.umaru.evstats.dto.BookDto;
import com.umaru.evstats.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    public List<BookDto> getBooks();
    public List<BookDto> searchBooks(String searhTerm);
    public BookDto getBook(Long bookId);
    public Page<Book> findPaginated(Pageable pageable);
    public void updateBook(BookDto bookDto);
    public void deleteBook(Long bookId);
    public void pinjam(long id);
    public void saveBook(BookDto bookDto);
    public int countBooks();
}
