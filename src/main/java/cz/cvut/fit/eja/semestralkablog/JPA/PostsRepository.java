package cz.cvut.fit.eja.semestralkablog.JPA;

import com.vaadin.spring.annotation.SpringComponent;
import cz.cvut.fit.eja.semestralkablog.JPA.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by David Stražovan on 09.06.2017.
 */
@SpringComponent
public interface PostsRepository extends JpaRepository<Post, Long> {
}
