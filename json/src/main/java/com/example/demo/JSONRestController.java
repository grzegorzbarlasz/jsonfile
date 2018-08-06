package com.example.demo;

import com.example.demo.models.Author;
import com.example.demo.models.IndustryIdentifiers;
import com.example.demo.models.Item;
import com.example.demo.models.VolumeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class JSONRestController {

    JSON json;
    ObjectMapper mapper = new ObjectMapper();

    public JSONRestController() throws IOException {
        json = mapper.readValue(new File("books.json"), JSON.class);
    }

    @RequestMapping(path = "/api/book/{isbn}", method = RequestMethod.GET)
    public Item book(@PathVariable("isbn") String isbn) {
        if (findByIsbn(isbn) == null){
            throw new notFoundException(isbn);
        } else {
            return findByIsbn(isbn);
        }
    }

    @RequestMapping(path = "/api/category/{categoryName}/books", method = RequestMethod.GET)
    public List<Item> category(@PathVariable("categoryName") String categoryName){
        return findByCategoryName(categoryName);
    }

    @RequestMapping(path = "/api/rating", method = RequestMethod.GET)
    public List<Author> rating(){
        return authorRating();
    }

//    public List<Item> mapping() throws IOException{
//        return json.getItems();
//    }


    public Item findByIsbn(String isbn){
        Item itemToReturn = null;
        List<Item> items = json.getItems();

        for (Item item : items) {
            if(item.getId().equals(isbn)){
                itemToReturn = item;
            } else {
                for(IndustryIdentifiers id : item.getVolumeInfo().getIndustryIdentifiers()){
                    if(id.getType().equals("ISBN_13") && id.getIdentifier().equals(isbn)){
                        itemToReturn = item;
                    }
                }
            }
        }
        return itemToReturn;
    }


    public List<Item> findByCategoryName(String categoryName){
        List<Item> booksByCategoryReturn = new ArrayList<>();
        List<Item> items = json.getItems();

        for (Item item : items) {
            if (item.getVolumeInfo().getCategories() != null) {
                for (String category : item.getVolumeInfo().getCategories()) {
                    if (category.equals(categoryName)) {
                        booksByCategoryReturn.add(item);
                    }
                }
            }
        }
        return booksByCategoryReturn;
    }

    public List<Author> authorRating(){
        List<String> authorsNames = new ArrayList<>();
        List<Item> items = json.getItems();
        List<Author> authorsToReturn = new LinkedList<>();
        for(Item item : items ) {
            if(item.getVolumeInfo().getAuthors() != null) {
                for(String name : item.getVolumeInfo().getAuthors()) {
                    authorsNames.add(name);
                }
            }
        }

        authorsNames = new ArrayList<>(new HashSet<>(authorsNames));

        for(String name : authorsNames) {
            Double rating = 0.0;
            int ratingCount = 0;

            for(Item item : items) {
                if(item.getVolumeInfo().getAverageRating() != null && item.getVolumeInfo().getAuthors() != null) {
                    for(String name2 : item.getVolumeInfo().getAuthors()) {
                        if(name2.equals(name)) {
                            rating += item.getVolumeInfo().getAverageRating();
                            ratingCount++;
                        }
                    }
                }
            }
            ratingCount = ratingCount == 0 ? 1 : ratingCount;
            rating = rating / ratingCount;
            authorsToReturn.add(new Author(name, rating));

        }
        authorsToReturn = authorsToReturn.stream().sorted(Comparator.comparing(Author::getAverageRating)).collect(Collectors.toList());
        return authorsToReturn;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class notFoundException extends RuntimeException {
        public notFoundException(String isbn){
            super("Error: 404! No results found with: " + isbn + " isbn/id number.");
        }
    }
}