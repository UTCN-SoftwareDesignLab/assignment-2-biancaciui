package com.lab4.demo.report;

import com.lab4.demo.book.BookRepository;
import com.lab4.demo.book.BookService;
import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static com.lab4.demo.report.ReportType.PDF;

@Service
@AllArgsConstructor
public class PdfReportService implements ReportService {

    private BookService bookService;

    @Override
    public String export() {
        List<BookDTO> books = new ArrayList<>(bookService.findByAmount(0));

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try{
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 11);
            contentStream.setLeading(16f);
            contentStream.newLineAtOffset(45,750);
            contentStream.showText("Out of stock books: ");
            contentStream.newLine();
            contentStream.newLine();

            for(BookDTO book: books){
                contentStream.showText("Title: " + book.getName());
                contentStream.newLine();
                contentStream.showText("Author: "+ book.getAuthor());
                contentStream.newLine();
                contentStream.showText("Description: "+ book.getDescription());
                contentStream.newLine();
                contentStream.showText("Author: "+ book.getAuthor());
                contentStream.newLine();
                contentStream.showText("Genre: "+ book.getGenre());
                contentStream.newLine();
                contentStream.showText("Price: "+book.getPrice());
                contentStream.newLine();
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();
            document.save("OutOfStock_Books.pdf");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "OutOfStock_Books.pdf";
    }


    @Override
    public ReportType getType() {
        return PDF;
    }
}
