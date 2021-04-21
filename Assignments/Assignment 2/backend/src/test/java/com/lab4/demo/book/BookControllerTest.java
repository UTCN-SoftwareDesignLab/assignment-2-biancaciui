package com.lab4.demo.book;

import com.lab4.demo.BaseControllerTest;
import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import com.lab4.demo.report.CSVReportService;
import com.lab4.demo.report.PdfReportService;
import com.lab4.demo.report.ReportServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.lab4.demo.TestCreationFactory.*;
import static com.lab4.demo.UrlMapping.*;
import static com.lab4.demo.report.ReportType.CSV;
import static com.lab4.demo.report.ReportType.PDF;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest extends BaseControllerTest {

    @InjectMocks
    private BookController controller;

    @Mock
    private BookService bookService;

    @Mock
    private ReportServiceFactory reportServiceFactory;

    @Mock
    private CSVReportService csvReportService;

    @Mock
    private PdfReportService pdfReportService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        controller = new BookController(bookService, reportServiceFactory);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allBooks() throws Exception {
        List<BookDTO> items = TestCreationFactory.listOf(Book.class);
        when(bookService.findAll()).thenReturn(items);

        ResultActions response = mockMvc.perform(get(BOOKS));

        response.andExpect(status().isOk())
                .andExpect(jsonContentToBe(items));
    }

    @Test
    void exportReport() throws Exception {
        when(reportServiceFactory.getReportService(PDF)).thenReturn(pdfReportService);
        when(reportServiceFactory.getReportService(CSV)).thenReturn(csvReportService);
        String pdfResponse = "PDF!";
        when(pdfReportService.export()).thenReturn(pdfResponse);
        String csvResponse = "CSV!";
        when(csvReportService.export()).thenReturn(csvResponse);

        ResultActions pdfExport = mockMvc.perform(get(BOOKS + EXPORT_REPORT, PDF.name()));
        ResultActions csvExport = mockMvc.perform(get(BOOKS + EXPORT_REPORT, CSV.name()));

        pdfExport.andExpect(status().isOk())
                .andExpect(content().string(pdfResponse));
        csvExport.andExpect(status().isOk())
                .andExpect(content().string(csvResponse));

    }

    @Test
    void create() throws Exception {
        BookDTO reqItem = BookDTO.builder().name(randomString())
                .description(randomString())
                .build();

        when(bookService.create(reqItem)).thenReturn(reqItem);

        ResultActions result = performPostWithRequestBody(BOOKS, reqItem);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqItem));
    }

    @Test
    void edit() throws Exception {
        long id = randomLong();
        BookDTO reqItem = BookDTO.builder()
                .id(id)
                .name(randomString())
                .description(randomString())
                .build();

        when(bookService.edit(id, reqItem)).thenReturn(reqItem);

        ResultActions result = performPutWithRequestBodyAndPathVariable(BOOKS + ENTITY, reqItem, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqItem));
    }
    @Test
    void changeName() throws Exception {
        long id = randomLong();
        String newName = randomString();
        BookDTO reqItem = BookDTO.builder()
                .id(id)
                .name(newName)
                .description(randomString())
                .amount(200)
                .build();

        when(bookService.changeName(id, newName)).thenReturn(reqItem);

        ResultActions result = performPatchWithRequestBodyAndPathVariable(BOOKS+ENTITY, newName, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqItem));
    }


    @Test
    void changeAmount() throws Exception {
        long id = randomLong();
        int newAmount = randomBoundedInt(1500);
        BookDTO reqItem = BookDTO.builder()
                .id(id)
                .name(randomString())
                .description(randomString())
                .amount(newAmount)
                .build();

        when(bookService.changeAmount(id, newAmount)).thenReturn(reqItem);

        ResultActions result = performPatchWithRequestBodyAndPathVariable(BOOKS+ENTITY, newAmount, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqItem));
    }
    @Test
    void changeAuthor() throws Exception {
        long id = randomLong();
        String newName = randomString();
        BookDTO reqItem = BookDTO.builder()
                .id(id)
                .name(newName)
                .description(randomString())
                .build();

        when(bookService.changeName(id, newName)).thenReturn(reqItem);

        ResultActions result = performPatchWithRequestBodyAndPathVariable(BOOKS + ENTITY, newName, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqItem));
    }
    @Test
    void changeGenre() throws Exception {
        long id = randomLong();
        String newName = randomString();
        BookDTO reqItem = BookDTO.builder()
                .id(id)
                .name(newName)
                .description(randomString())
                .build();

        when(bookService.changeName(id, newName)).thenReturn(reqItem);

        ResultActions result = performPatchWithRequestBodyAndPathVariable(BOOKS + ENTITY, newName, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqItem));
    }
    @Test
    void changeDescription() throws Exception {
        long id = randomLong();
        String newName = randomString();
        BookDTO reqItem = BookDTO.builder()
                .id(id)
                .name(newName)
                .description(randomString())
                .build();

        when(bookService.changeName(id, newName)).thenReturn(reqItem);

        ResultActions result = performPatchWithRequestBodyAndPathVariable(BOOKS + ENTITY, newName, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqItem));
    }

    @Test
    void getBook() throws Exception {
        long id = randomLong();
        BookDTO reqBook = BookDTO.builder()
                .id(id)
                .name(randomString())
                .description(randomString())
                .build();
        when(bookService.get(id)).thenReturn(reqBook);

        ResultActions result = performGetWithPathVariable(BOOKS + ENTITY, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqBook));
    }
    @Test
    void delete() throws Exception {
        long id = randomLong();
        doNothing().when(bookService).deleteById(id);

        ResultActions result = performDeleteWithPathVariable(BOOKS + ENTITY, id);
        result.andExpect(status().isOk());
        verify(bookService, times(1)).deleteById(id);
    }

    @Test
    void deleteAll() throws Exception {
        doNothing().when(bookService).deleteAll();

        ResultActions result = performDelete(BOOKS);
        result.andExpect(status().isOk());
        verify(bookService, times(1)).deleteAll();
    }

    @Test
    void sell() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .id(randomLong())
                .name(randomString())
                .author(randomString())
                .genre(randomString())
                .price(25.5f)
                .amount(randomBoundedInt(200))
                .build();
        int quantity = randomBoundedInt(bookDTO.getAmount());

        when(bookService.sell(bookDTO.getId(),quantity)).thenReturn(true);
        ResultActions result = performSell(BOOKS+ENTITY+SELL_BOOK,bookDTO.getId().toString(),quantity);
        result.andExpect(status().isOk());

//        when(bookService.sell(bookDTO.getId(),300)).thenReturn(false);
//        ResultActions resultBad = performSell(BOOKS+ENTITY+SELL_BOOK,bookDTO.getId().toString(),300);
//        resultBad.andExpect(status().isBadRequest());
    }

    //TODO:
    @Test
    public void findOutOfStock(){}
    @Test
    public void filterByDescription() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .id(randomLong())
                .name(randomString())
                .author("nice descript")
                .genre(randomString())
                .price(25.5f)
                .amount(randomBoundedInt(200))
                .build();

//        when(bookService.filterByDescription("nice descript")).thenReturn(true);

        List<BookDTO> books = TestCreationFactory.listOf(Book.class);
        when(bookService.filterByDescription("nice descript")).thenReturn(books);

        ResultActions response = mockMvc.perform(get(BOOKS));

        response.andExpect(status().isOk())
                .andExpect(jsonContentToBe(books));
    }


}