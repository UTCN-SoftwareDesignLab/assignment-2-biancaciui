package com.lab4.demo.book;

import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookService(bookRepository, bookMapper);
    }

    @Test
    void findAll() {
        List<Book> books = TestCreationFactory.listOf(Book.class);
        when(bookRepository.findAll()).thenReturn(books);

        List<BookDTO> all = bookService.findAll();

        Assertions.assertEquals(books.size(), all.size());
    }
    //TODO:
//    @Test
//    void findById() {
//
//    }

    @Test
    void findOutOfStock(){
        //TODO
        BookDTO bookDTO = BookDTO.builder()
                .name("Crime and Punishment")
                .description("Dosto's second best book")
                .author("Feodor Dostoievski")
                .amount(0)
                .genre("Drama, Philosophical Fiction")
                .price(65.5f)
                .publisher("Biblioteca Adevarul")
                .build();
        BookDTO bookDTO1 = BookDTO.builder()
                .name("Gone with the Wind")
                .description("Fave book of all times")
                .author("Margaret Mitchell")
                .amount(0)
                .genre("Drama, Romance")
                .price(105.5f)
                .publisher("Macmillan Publishers")
                .build();
        BookDTO bookDTO2 = BookDTO.builder()
                .name("Crime and Punishment")
                .description("Dosto's second best book")
                .author("Feodor Dostoievski")
                .amount(40)
                .genre("Drama, Philosophical Fiction")
                .price(65.5f)
                .publisher("Biblioteca Adevarul")
                .build();

        bookService.create(bookDTO);
        bookService.create(bookDTO1);
        bookService.create(bookDTO2);

        List<BookDTO> outOfStock = bookService.findOutOfStock();
        Assertions.assertEquals(2, outOfStock.size());
    }

    @Test
    void findByDescription(){
        //TODO:
        BookDTO bookDTO = BookDTO.builder()
                .name("Crime and Punishment")
                .description("Dosto's second best book")
                .author("Gone with the Wind")
                .amount(0)
                .genre("Drama, Philosophical Fiction")
                .price(65.5f)
                .publisher("Biblioteca Adevarul")
                .build();
        BookDTO bookDTO1 = BookDTO.builder()
                .name("Gone with the Wind")
                .description("Fave book of all times")
                .author("Margaret Mitchell")
                .amount(0)
                .genre("Drama, Romance")
                .price(105.5f)
                .publisher("Macmillan Publishers")
                .build();
        BookDTO bookDTO2 = BookDTO.builder()
                .name("Crime and Punishment")
                .description("Gone with the Wind")
                .author("Feodor Dostoievski")
                .amount(40)
                .genre("Drama, Philosophical Fiction")
                .price(65.5f)
                .publisher("Biblioteca Adevarul")
                .build();
        BookDTO bookDTO3 = BookDTO.builder()
                .name("Crime and Punishment")
                .description("Blablabla")
                .author("Feodor Dostoievski")
                .amount(40)
                .genre("Drama, Philosophical Fiction")
                .price(65.5f)
                .publisher("Biblioteca Adevarul")
                .build();

        bookRepository.save(bookMapper.fromDto(bookDTO));
        bookRepository.save(bookMapper.fromDto(bookDTO1));
        bookRepository.save(bookMapper.fromDto(bookDTO2));
        bookRepository.save(bookMapper.fromDto(bookDTO3));
//        bookService.create(bookDTO);
//        bookService.create(bookDTO1);
//        bookService.create(bookDTO2);
//        bookService.create(bookDTO3);

        //List<BookDTO> bookByDescription = bookService.filterByDescription("Gone with the Wind");

        Assertions.assertEquals(3, bookService.filterByDescription("Gone with the Wind").size());
    }

    @Test
    void create() {
        //here we need to test the toDTO and fromDTO transformations too

        Book book = Book.builder()
            .name("Gone with the Wind")
            .description("Fave book of all times")
            .author("Margaret Mitchell")
            .amount(100)
            .genre("Drama, Romance")
            .price(105.5f)
            .publisher("Macmillan Publishers")
            .build();
        BookDTO bookDTO_id = BookDTO.builder()
                .name("The Idiot")
                .description("Dosto's second best book")
                .author("Feodor Dostoievski")
                .amount(20)
                .genre("Drama, Philosophical Fiction")
                .price(65.5f)
                .publisher("Biblioteca Adevarul")
                .id(168L)
                .build();
        BookDTO bookDTO = BookDTO.builder()
                .name("Crime and Punishment")
                .description("Dosto's second best book")
                .author("Feodor Dostoievski")
                .amount(40)
                .genre("Drama, Philosophical Fiction")
                .price(65.5f)
                .publisher("Biblioteca Adevarul")
                .build();

        when(bookMapper.toDto(book)).thenReturn(bookDTO_id);
        when(bookMapper.fromDto(bookDTO)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);

        Assertions.assertEquals(bookDTO_id.getId(), bookService.create(bookDTO).getId());
    }

    @Test
    void edit() {
        //TODO: make this work
        BookDTO bookDTO = BookDTO.builder()
                .name("Gone with the Wind")
                .description("Fave book of all times")
                .author("Margaret Mitchell")
                .amount(100)
                .genre("Drama, Romance")
                .price(105.5f)
                .publisher("Macmillan Publishers")
                .build();

        bookRepository.save(bookMapper.fromDto(bookDTO));

        bookDTO = bookService.create(bookDTO);
        bookDTO.setPrice(90.5f);
        Assertions.assertEquals(90.5f, bookService.edit(bookDTO.getId(), bookDTO).getPrice());
    }

    @Test
    void deleteAll() {
        Book book = Book.builder()
                .name("Gone with the Wind")
                .description("Fave book of all times")
                .author("Margaret Mitchell")
                .amount(100)
                .genre("Drama, Romance")
                .price(105.5f)
                .publisher("Macmillan Publishers")
                .id(169L)
                .build();
        when(bookRepository.save(book)).thenReturn(book);

        bookRepository.deleteAll();

        List<BookDTO> all = bookService.findAll();
        Assertions.assertEquals(all.size(), 0);
    }

    @Test
    void delete() {
        Book book = Book.builder()
                .name("Gone with the Wind")
                .description("Fave book of all times")
                .author("Margaret Mitchell")
                .amount(100)
                .genre("Drama, Romance")
                .price(105.5f)
                .publisher("Macmillan Publishers")
                .id(169L)
                .build();
        when(bookRepository.save(book)).thenReturn(book);

        bookRepository.delete(book);

        List<BookDTO> all = bookService.findAll();
        Assertions.assertEquals(all.size(), 0);
    }

    @Test
    void sell(){
        //TODO: do this :)
        BookDTO bookDTO = BookDTO.builder()
                .name("Gone with the Wind")
                .description("Fave book of all times")
                .author("Margaret Mitchell")
                .amount(100)
                .genre("Drama, Romance")
                .price(105.5f)
                .publisher("Macmillan Publishers")
                //.id(169L)
                .build();
        bookRepository.save(bookMapper.fromDto(bookDTO));
        Assertions.assertTrue(bookService.sell(bookDTO.getId(), 3));
        Assertions.assertFalse(bookService.sell(bookDTO.getId(), 101));
    }
}
