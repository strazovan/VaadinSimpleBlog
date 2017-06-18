package cz.cvut.fit.eja.semestralkablog.JPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by David Stražovan on 09.06.2017.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT t FROM Author t WHERE t.name = ?1")
    public Author findByName(String name);
}
