package cz.cvut.fit.eja.semestralkablog.pages;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import cz.cvut.fit.eja.semestralkablog.JPA.Post;
import cz.cvut.fit.eja.semestralkablog.JPA.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by David Stražovan on 10.06.2017.
 */

public class PostLayout extends VerticalLayout {

    private Panel content;
    private Label author;
    private Label text;




    public PostLayout(Post post) {
        content = new Panel(post.getHeader());
        content.setWidth("100%");

        author = new Label("Author: " + post.getAuthor().getName());

        text = new Label(post.getText());
        text.setWidth("100%");
        text.setContentMode(ContentMode.HTML);
        author.setStyleName(ValoTheme.LABEL_BOLD);

        VerticalLayout data = new VerticalLayout();
        data.setWidth("100%");
        data.addComponent(author);
        ;
        data.addComponent(text);
        content.setContent(data);

        this.addComponent(content);
    }


}
