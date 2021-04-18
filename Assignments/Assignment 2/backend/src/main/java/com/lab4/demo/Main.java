package com.lab4.demo;

import com.lab4.demo.book.BookRepository;
import com.lab4.demo.book.BookService;
import com.lab4.demo.security.AuthService;
import com.lab4.demo.user.RoleRepository;
import com.lab4.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Main {
    //TODO: application.properties -> bootstrap = true
//    public static BookMapper bookMapper;
//    public static BookService bookService=new BookService(bookRepository,bookMapper);

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final BookRepository bookRepository;

    private final BookService bookService;

    public void main(String[] args){

//        Book book = Book.builder()
//                .name("Gone with the Wind")
//                .description("Fave book of all times")
//                .author("Margaret Mitchell")
//                .amount(100)
//                .genre("Drama, Romance")
//                .price(105.5f)
//                .publisher("Macmillan Publishers")
//                .build();
//        bookService.create(bookMapper.toDto(book));
    }
}
