package cz.cvut.fit.eja.semestralkablog.JPA;

import javax.persistence.*;

/**
 * Created by David Stražovan on 09.06.2017.
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private String text;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="POST_ID")
    private Post post;

    public Comment() {
    }

    public Comment(String text) {
        this.text = text;
    }

    public long getId() {
        return Id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
