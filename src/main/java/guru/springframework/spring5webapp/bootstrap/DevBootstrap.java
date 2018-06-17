package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component /* makes this a Spring bean */
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    /* add repos as member variables, autowiring*/
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    /* ctor */
    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Publisher publisher1 = new Publisher();
        publisher1.setName("EA");
        publisher1.setAddress("LA");
        Publisher publisher2 = new Publisher();
        publisher2.setName("DontNod");
        publisher2.setAddress("SF");

        publisherRepository.save(publisher1);
        publisherRepository.save(publisher2);

        Author erin = new Author("Erin", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", publisher1);
        erin.getBooks().add(ddd);
        ddd.getAuthors().add(erin);

        /* autowiring */
        authorRepository.save(erin);
        bookRepository.save(ddd);

        Author reika = new Author("Reika", "Jo");
        Book noEJB = new Book("J2EE Development without EJB", "23444", publisher2);
        reika.getBooks().add(noEJB);
        noEJB.getAuthors().add(reika);

        /* autowiring */
        authorRepository.save(reika);
        bookRepository.save(noEJB);
    }

}
