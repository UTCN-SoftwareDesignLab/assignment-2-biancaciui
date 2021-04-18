package com.lab4.demo.book;

import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDTO> findOutOfStock(){
        return bookRepository.findNoStockBooks().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    List<BookDTO> filterByDescription(String s){
//        return bookRepository.findAll().stream()
//                .filter(b -> ( b.getAuthor().equals(s) || b.getDescription().equals(s) || b.getGenre().equals(s))) //find out the books that are in stock
//                .map(bookMapper::toDto)
//                .collect(Collectors.toList());
        return bookRepository.findByDescription(s).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDTO create(BookDTO book) {
        return bookMapper.toDto(bookRepository.save(
                bookMapper.fromDto(book)
        ));
    }

    public BookDTO edit(Long id, BookDTO bookDTO) {
        Book actBook = findById(id);
        actBook.setName(bookDTO.getName());
        actBook.setDescription(bookDTO.getDescription());
        actBook.setAuthor(bookDTO.getAuthor());
        actBook.setPublisher(bookDTO.getPublisher());
        actBook.setAmount(bookDTO.getAmount());
        actBook.setGenre(bookDTO.getGenre());
        actBook.setPrice(bookDTO.getPrice());
        return bookMapper.toDto(
                bookRepository.save(actBook)
        );
    }

    public BookDTO changeName(Long id, String newName) {
        Book actBook = findById(id);
        actBook.setName(newName);
        return bookMapper.toDto(bookRepository.save(actBook));
    }

    public void deleteAll(){
        bookRepository.deleteAll();
    }

    public void delete(BookDTO book){ bookRepository.delete(bookMapper.fromDto(book)); }

    public void deleteById(Long id){bookRepository.deleteById(id);}

    public boolean sell(Long id, int amount){
        Book book = findById(id);
        if(book.getAmount() - amount >= 0){
            book.setAmount(book.getAmount() - amount);
            bookMapper.toDto(bookRepository.save(book));
            return true;
        }
        return false;
    }

    public BookDTO get(Long id) {
        return bookMapper.toDto(findById(id));
    }

    public BookDTO changeDescription(Long id, String description) {
        Book actBook = findById(id);
        actBook.setDescription(description);
        return bookMapper.toDto(bookRepository.save(actBook));
    }

    public BookDTO changeAmount(Long id, int amount) {
        Book actBook = findById(id);
        actBook.setAmount(amount);
        return bookMapper.toDto(bookRepository.save(actBook));
    }

    public BookDTO changeAuthor(Long id, String author) {
        Book actBook = findById(id);
        actBook.setAuthor(author);
        return bookMapper.toDto(bookRepository.save(actBook));
    }

    public BookDTO changeGenre(Long id, String genre) {
        Book actBook = findById(id);
        actBook.setGenre(genre);
        return bookMapper.toDto(bookRepository.save(actBook));
    }
}
