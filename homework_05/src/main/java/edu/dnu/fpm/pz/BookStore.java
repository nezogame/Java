package edu.dnu.fpm.pz;

import java.util.*;
import java.util.stream.Collectors;

public class BookStore {

    private List<Book> books;
    private Map<String, Integer> authorBooks;

    public BookStore() {
        this.books = new ArrayList<Book>();
        this.authorBooks = new HashMap<String, Integer>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book){
        books.add(book);
        updateAuthorBooks();
    }
    public void removeBook(Book book){
        books.remove(book);
        updateAuthorBooks();
    }

    protected void  updateAuthorBooks(){
        authorBooks = books.stream()
                .collect(Collectors.groupingBy(
                    Book::getAuthor, Collectors.summingInt(e -> 1) ));
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Map<String, Integer> getAuthorBooks() {
        return authorBooks;
    }

    public void setAuthorBooks(Map<String, Integer> authorBooks) {
        this.authorBooks = authorBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookStore bookStore = (BookStore) o;
        return books.equals(bookStore.books) && authorBooks.equals(bookStore.authorBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books, authorBooks);
    }
}
