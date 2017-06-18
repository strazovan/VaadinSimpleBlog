package cz.cvut.fit.eja.semestralkablog.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David Stražovan on 09.06.2017.
 */
public interface CommentsRepository extends JpaRepository<Comment, Long> {
}
