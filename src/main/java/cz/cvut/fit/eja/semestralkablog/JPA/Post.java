package cz.cvut.fit.eja.semestralkablog.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by David Stražovan on 09.06.2017.
 */
@Entity
public class Post {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private String header;
    private String text;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="AUTHOR_ID")
    private Author author;


//    @JsonIgnore
//    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER   )
//    private List<Comment> commets;

    public Post() {
    }

    public Post(String header, String text, Author author) {
        this.header = header;
        this.text = text;
        this.author = author;
    }

    public long getId() {
        return Id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    //    public List<Comment> getCommets() {
//        return commets;
//    }
}
