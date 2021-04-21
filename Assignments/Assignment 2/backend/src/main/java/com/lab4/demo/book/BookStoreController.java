package com.lab4.demo.book;

import com.lab4.demo.book.model.dto.BookDTO;
import com.lab4.demo.report.ReportServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(API_PATH+SELL_BOOK)
@RequiredArgsConstructor
public class BookStoreController {

    private final BookService bookService;

    @PostMapping(ENTITY+QUANTITY)
    public boolean sell(@PathVariable Long id, @PathVariable int amount) {
        bookService.changeAmount(id, amount);
        return bookService.sell(id, amount);
    }

    @GetMapping(DESCRIPTION)
    List<BookDTO> filterByDescription(@PathVariable String description) {
        return bookService.filterByDescription(description);
    }

}
