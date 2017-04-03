package com.rueggerllc.restlib2.beans;


import java.util.Date;

public class Book {

    private String title;
    private int numberOfPages;
    private Date publicationDate;

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Book.title: " + title);
        buffer.append("\nBook.numberOfPages: " + numberOfPages);
        buffer.append("\nBook.publicationDate: " + publicationDate);
        return buffer.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
