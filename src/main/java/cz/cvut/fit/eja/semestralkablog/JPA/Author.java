package cz.cvut.fit.eja.semestralkablog.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by David Stražovan on 09.06.2017.
 */
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    private String name;

    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public long getId() {
        return authorid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
