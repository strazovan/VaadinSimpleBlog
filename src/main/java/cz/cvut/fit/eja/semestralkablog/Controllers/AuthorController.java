package cz.cvut.fit.eja.semestralkablog.Controllers;

import com.vaadin.spring.annotation.SpringComponent;
import cz.cvut.fit.eja.semestralkablog.JPA.Author;
import cz.cvut.fit.eja.semestralkablog.JPA.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by David Stražovan on 19.06.2017.
 */
@SpringComponent
public class AuthorController {


    @Autowired
    AuthorRepository authorRepository;


    public Author findByName(String value) {
        return  authorRepository.findByName(value);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findOne(long id) {
        return authorRepository.findOne(id);
    }
}
