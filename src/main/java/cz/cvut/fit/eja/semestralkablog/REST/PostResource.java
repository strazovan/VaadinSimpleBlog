package cz.cvut.fit.eja.semestralkablog.REST;

import cz.cvut.fit.eja.semestralkablog.Controllers.AuthorController;
import cz.cvut.fit.eja.semestralkablog.Controllers.PostController;
import cz.cvut.fit.eja.semestralkablog.JPA.Author;
import cz.cvut.fit.eja.semestralkablog.JPA.AuthorRepository;
import cz.cvut.fit.eja.semestralkablog.JPA.Post;
import cz.cvut.fit.eja.semestralkablog.JPA.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by David Stražovan on 11.06.2017.
 */

@RestController
public class PostResource {

    @Autowired
    PostsRepository postController;

    @Autowired
    AuthorRepository authorController;

    @RequestMapping(value = "/getPosts", method = RequestMethod.GET)
    public List<Post> getPosts()
    {
        return postController.findAll();
    }

    @RequestMapping(value = "/addPost/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> addPost(@PathVariable long id,
                                     @RequestBody Post post)
    {
        Author h = authorController.findOne(id);
        post.setAuthor(h);
        postController.save(post);
        return ResponseEntity.status(200).build();
    }

}
