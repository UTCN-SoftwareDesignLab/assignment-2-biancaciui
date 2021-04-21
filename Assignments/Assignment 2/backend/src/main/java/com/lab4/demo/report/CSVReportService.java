package com.lab4.demo.report;

import com.lab4.demo.book.BookMapper;
import com.lab4.demo.book.BookRepository;
import com.lab4.demo.book.BookService;
import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.lab4.demo.report.ReportType.CSV;

@Service
@AllArgsConstructor
public class CSVReportService implements ReportService {

    private BookService bookService;

    @Override
    public String export() {
        List<BookDTO> books = new ArrayList<>(bookService.findByAmount(0));

        try{
            FileWriter fileWriter = new FileWriter("OutOfStock_Books.csv");
            for(BookDTO b: books){
                fileWriter.append(b.getName());
                fileWriter.append(",");
                fileWriter.append(b.getAuthor());
                fileWriter.append(",");
                fileWriter.append(b.getGenre());
                fileWriter.append(",");
                fileWriter.append(b.getDescription());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(b.getPrice()));
                fileWriter.append("\n");
            }
           fileWriter.flush();
        }catch(IOException e){
            return "Error at generating the CSV report";
        }
        return "OutOfStock_Books.csv";
    }

    @Override
    public ReportType getType() {
        return CSV;
    }
}
