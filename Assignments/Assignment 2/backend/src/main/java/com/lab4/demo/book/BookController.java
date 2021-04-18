package com.lab4.demo.book;

import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import com.lab4.demo.report.ReportServiceFactory;
import com.lab4.demo.report.ReportType;
import com.lab4.demo.security.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(BOOKS)
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<BookDTO> findAll() {
        return bookService.findAll();
    }

    @PostMapping()
    public BookDTO create(@RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }

    @GetMapping(ENTITY)
    public BookDTO getBook(@PathVariable Long id) {
        return bookService.get(id);
    }

//    @PatchMapping(ENTITY)
//   // @PatchMapping(CHANGE_NAME)
//    public BookDTO changeName(@PathVariable Long id, @RequestBody String newName){
//        return bookService.changeName(id, newName);
//    }
//    @PatchMapping(CHANGE_DESCRIPTION)
//    public BookDTO changeDescription(@PathVariable Long id, @RequestBody String description){
//        return bookService.changeDescription(id, description);
//    }
    @PatchMapping(ENTITY)
    public BookDTO changeAmount(@PathVariable Long id, @RequestBody int amount){
        return bookService.changeAmount(id, amount);
    }
//    @PatchMapping(CHANGE_AUTHOR)
//    public BookDTO changeAuthor(@PathVariable Long id, @RequestBody String author){
//        return bookService.changeAuthor(id, author);
//    }
//    @PatchMapping(CHANGE_GENRE)
//    public BookDTO changeGenre(@PathVariable Long id, @RequestBody String genre){
//        return bookService.changeGenre(id, genre);
//    }

    @PutMapping(ENTITY)
    public BookDTO edit(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return bookService.edit(id, bookDTO);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){ bookService.deleteById(id);}

    @DeleteMapping()
    public void deleteAll(){ bookService.deleteAll(); }

    @GetMapping(EXPORT_REPORT)
    public String exportReport(@PathVariable ReportType type) { return reportServiceFactory.getReportService(type).export(); }

    @PostMapping(ENTITY+SELL_BOOK)
    public boolean sell(@PathVariable Long id, @PathVariable int amount) {
        return bookService.sell(id, amount);
    }

    @GetMapping("/out_stock")
    public List<BookDTO> findOutOfStock() {
        return bookService.findOutOfStock();
    }

    @GetMapping(DESCRIPTION)
    List<BookDTO> filterByDescription(@PathVariable String description) {
        return bookService.filterByDescription(description);
    }
}
