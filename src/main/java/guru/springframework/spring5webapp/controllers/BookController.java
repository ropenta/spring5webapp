package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/* a Spring bean */
@Controller
public class BookController {

    private BookRepository bookRepository;

    /* ctor, controller is autowired */
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")  /* books path */
    public String getBooks(Model model){

        model.addAttribute("books", bookRepository.findAll());

        return "books";
    }
}