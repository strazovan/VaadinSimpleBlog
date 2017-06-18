package cz.cvut.fit.eja.semestralkablog.pages;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import cz.cvut.fit.eja.semestralkablog.JPA.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * Created by David Stražovan on 09.06.2017.
 */
@UIScope
@SpringUI
@PreserveOnRefresh
public class IndexUI extends UI {
    private VerticalLayout root;
    private VerticalLayout postLayout;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    AuthorRepository authorRepository;


    @Override
    protected void init(VaadinRequest vaadinRequest) {

//        getSession().setAttribute("user", authorRepository.findOne((long) 1));

        root = new VerticalLayout();
        postLayout = new VerticalLayout();
        addHeader();
        root.addComponent(postLayout);


        getPosts();

        setContent(root);
    }

    private void addHeader() {
        HorizontalLayout tools = new HorizontalLayout();
        tools.setWidth("100%");

        Label header = new Label("Welcome on my blog!");
        header.setStyleName(ValoTheme.LABEL_H1);


        if (getSession().getAttribute("user") != null) {

            Window editor = createEditorWindow();
            HorizontalLayout rightPanel = new HorizontalLayout();

            Button newPost = new Button("New post");
            newPost.addClickListener(clickEvent -> {
                if (!editor.isAttached())
                    addWindow(editor);
            });
            newPost.setWidth("20%");
            Label user = new Label("User: " + ((Author) getSession().getAttribute("user")).getName());

            Button logout = new Button("Logout");
            logout.addClickListener(clickEvent -> {
               getSession().setAttribute("user", null);
               Notification.show("Logged out.");
               root.removeAllComponents();
               init(null);

            });

            rightPanel.addComponent(newPost);
            rightPanel.addComponent(logout);
            tools.addComponent(user);
            tools.addComponent(rightPanel);
            tools.setComponentAlignment(user, Alignment.MIDDLE_LEFT);
            tools.setComponentAlignment(rightPanel, Alignment.MIDDLE_RIGHT);
        } else {
            Button login = new Button("Login");

            login.addClickListener(clickEvent -> {
                addWindow(createLoginWindow());
            });

            tools.addComponent(login);
            tools.setComponentAlignment(login, Alignment.MIDDLE_RIGHT);

        }

        root.addComponent(tools);
        root.addComponent(header);
        root.setComponentAlignment(header, Alignment.MIDDLE_CENTER);


    }

    private Window createEditorWindow() {
        Window editor = new Window();
        editor.setWidth("60%");
        editor.setHeight("60%");
        VerticalLayout editorContent = new VerticalLayout();
        editor.setHeight("60%");
        TextField postHeader = new TextField("Header:");
        editorContent.addComponent(postHeader);
        RichTextArea textEditor = new RichTextArea();
        textEditor.setWidth("90%");
        textEditor.setHeight("90%");
        editorContent.addComponent(textEditor);
        Button saveButton = new Button("Publish");

        saveButton.addClickListener(clickEvent ->
        {
            postsRepository.save(new Post(postHeader.getValue(), textEditor.getValue(), (Author) getSession().getAttribute("user")));
            editor.close();
            postHeader.setValue("");
            textEditor.setValue("");
            refresh(null);
            Notification.show("Post published!");

        });
        editorContent.addComponent(saveButton);

        editor.setContent(editorContent);
        return editor;
    }

    private Window createEditorWindow(Post p) {
        Window editor = new Window();
        editor.setWidth("60%");
        editor.setHeight("60%");
        VerticalLayout editorContent = new VerticalLayout();
        editor.setHeight("60%");
        TextField postHeader = new TextField("Header:");
        editorContent.addComponent(postHeader);
        RichTextArea textEditor = new RichTextArea();
        textEditor.setWidth("90%");
        textEditor.setHeight("90%");
        editorContent.addComponent(textEditor);

        postHeader.setValue(p.getHeader());
        textEditor.setValue(p.getText());
        Button saveButton = new Button("Publish");

        saveButton.addClickListener(clickEvent ->
        {
            p.setHeader(postHeader.getValue());
            p.setText(textEditor.getValue());
            postsRepository.save(p);
            editor.close();
            refresh(null);
            Notification.show("Post edited!");

        });
        editorContent.addComponent(saveButton);

        editor.setContent(editorContent);
        return editor;
    }

    @Override
    protected void refresh(VaadinRequest request) {
        postLayout.removeAllComponents();

        getPosts();

    }

    private void getPosts() {
        List<Post> posts = postsRepository.findAll();
        Collections.reverse(posts);
        for (Post p : posts) {
            PostLayout c = new PostLayout(p);
            if (getSession().getAttribute("user") != null) {


                HorizontalLayout buttons = new HorizontalLayout();

                Button editButton = new Button("Edit");
                editButton.addClickListener(clickEvent ->
                {
                    Window editWindow = createEditorWindow(p);
                    addWindow(editWindow);

                });

                buttons.addComponent(editButton);

                Button deleteButton = new Button("Delete");
                deleteButton.addClickListener(clickEvent ->
                {
                    postsRepository.delete(p);
                    Notification.show("Post deleted.");
                    refresh(null);
                    Notification.show("Post deleted.");
                });
                buttons.addComponent(deleteButton);
                postLayout.addComponent(buttons);
            }
            postLayout.addComponent(c);

        }
    }

    private Window createLoginWindow() {
        Window login = new Window();
        login.setWidth("20%");
        login.setHeight("25%");

        VerticalLayout loginLayout = new VerticalLayout();
        login.setPositionX((int) (Page.getCurrent().getBrowserWindowWidth() * 0.4));
        login.setPositionY((int) (Page.getCurrent().getBrowserWindowHeight() * 0.15));

        TextField username = new TextField("Username: ");
        TextField password = new PasswordField("Password: ");

        Button confirm = new Button("Login");
        confirm.addClickListener(clickEvent -> {
            String hashed = DigestUtils.sha256Hex(password.getValue());

            Author user = authorRepository.findByName(username.getValue());
            if(user != null)
            {
                if(user.getPassword().equals(hashed))
                {
                    getSession().setAttribute("user", user);
                    root.removeAllComponents();
                    init(null);
                    login.close();
                }
            }


        });

        loginLayout.addComponent(username);
        loginLayout.addComponent(password);
        loginLayout.addComponent(confirm);

        login.setContent(loginLayout);

        return login;
    }
}
