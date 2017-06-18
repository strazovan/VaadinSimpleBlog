package cz.cvut.fit.eja.semestralkablog.Controllers;

import com.vaadin.spring.annotation.SpringComponent;
import cz.cvut.fit.eja.semestralkablog.JPA.Author;
import cz.cvut.fit.eja.semestralkablog.JPA.Post;
import cz.cvut.fit.eja.semestralkablog.JPA.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by David Stražovan on 19.06.2017.
 */

@SpringComponent
public class PostController {

    @Autowired
    PostsRepository postsRepository;

    public List<Post> getPosts()
    {
        return postsRepository.findAll();
    }

    public void deletePost(Post p)
    {
        postsRepository.delete(p);
    }

    public void addPost(String header, String text, Author h)
    {
        postsRepository.save(new Post(header, text, (Author) h));
    }

    public void editPost(Post p)
    {
        postsRepository.save(p);
    }

    public void addPost(Post post) {
        postsRepository.save(post);
    }
}
