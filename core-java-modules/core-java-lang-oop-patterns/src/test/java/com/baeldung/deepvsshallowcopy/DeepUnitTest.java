package com.baeldung.deepvsshallowcopy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.baeldung.deepvsshallowcopy.deep.Book;

public class DeepUnitTest {

    @Test
    public void whenCopyingObject_thenCopyShouldNotBeSame() {
        Author author = new Author("Dan", "Brown");
        Book book = new Book("Da Vinci Code", author);

        Book copy = new Book(book);

        assertThat(copy).isNotSameAs(book);
    }

    @Test
    public void whenChangingOriginal_thenCopyShouldNotChange() {
        Author originalAuthor = new Author("Dan", "Brown");
        Book originalBook = new Book("Da Vinci Code", originalAuthor);

        Book bookCopy = new Book(originalBook);
        Author authorCopy = bookCopy.getAuthor();
        authorCopy.setFirstName("Bob");

        assertThat(originalAuthor.getFirstName()).isNotEqualTo(authorCopy.getFirstName());
    }
}
