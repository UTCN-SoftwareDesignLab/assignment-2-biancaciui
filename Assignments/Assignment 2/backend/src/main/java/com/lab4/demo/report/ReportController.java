package com.lab4.demo.report;

import com.lab4.demo.book.BookService;
import com.lab4.demo.book.model.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(API_PATH)
@RequiredArgsConstructor
public class ReportController {

    private final ReportServiceFactory reportServiceFactory;

    @GetMapping(EXPORT_REPORT)
    public String export(@PathVariable ReportType type) {
        System.out.println("Export "+type);
        return reportServiceFactory.getReportService(type).export();
    }

}
