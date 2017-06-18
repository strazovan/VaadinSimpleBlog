package cz.cvut.fit.eja.semestralkablog.REST;

import cz.cvut.fit.eja.semestralkablog.JPA.Author;
import cz.cvut.fit.eja.semestralkablog.JPA.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by David Stražovan on 11.06.2017.
 */
@RestController
public class AuthorResource {
    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping("/getAuthors")
    public List<Author> getAuthors()
    {
        return authorRepository.findAll();
    }


}
