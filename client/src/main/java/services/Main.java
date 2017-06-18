package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import models.Author;
import models.Post;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

/**
 * Created by David Stražovan on 19.06.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        boolean run = true;
        Scanner c = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (run) {
            System.out.println("Menu: " +
                    "\n1/Get all posts" +
                    "\n2/Get all authors" +
                    "\n3/Post post" +
                    "\n4/Exit");
            int choice = c.nextInt();
            switch (choice) {
                case 1:
                    List<Post> posts = getPosts();
                    for (Post p : posts) {
                        System.out.println("Header: " + p.getHeader());
                        System.out.println("Author: " + p.getAuthor().getName());
                        System.out.println("Text: " + p.getText());
                        System.out.println();
                    }
                    break;

                case 2:
                    List<Author> authors = getAuthors();
                    for(Author a : authors)
                    {
                        System.out.println("Id: " + a.getId());
                        System.out.println("Name: " + a.getName());
                    }
                    break;

                case 3:
                    System.out.println("Author id?");
                    long id = c.nextLong();
                    System.out.println("Header?");
                    String header = br.readLine();
                    System.out.println("Text?");
                    String text = br.readLine();
                    post(header, text, id);
                    System.out.println("Added.");
                    break;
                case 4:
                    run = false;

                    break;
            }
        }

    }


    private static List<Author> getAuthors()
    {
        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource("http://localhost:8080/getAuthors");

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            Type typeOfListOfFoo = new TypeToken<List<Author>>() {
            }.getType();
//            List<Post> toReturn = response.getEntity(typeOfListOfFoo.getClass());
            String responseAsString = response.getEntity(String.class);

            return new Gson().fromJson(responseAsString, typeOfListOfFoo);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }
    private static void post(String header, String text, long authorID) {
        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource("http://localhost:8080/addPost/" + authorID);

            Post post = new Post();
            post.setHeader(header);
            post.setText(text);
            String input = new Gson().toJson(post, Post.class);

            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, input);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private static List<Post> getPosts() {
        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource("http://localhost:8080/getPosts");

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            Type typeOfListOfFoo = new TypeToken<List<Post>>() {
            }.getType();
//            List<Post> toReturn = response.getEntity(typeOfListOfFoo.getClass());
            String responseAsString = response.getEntity(String.class);


            return new Gson().fromJson(responseAsString, typeOfListOfFoo);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }
}
