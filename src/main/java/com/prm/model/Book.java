package com.prm.model;

import java.io.Serializable;

public class Book implements Serializable {
  private int id;
  private String title;
  private String author;
  private String publisher;
  private boolean isAvailable;
  private String genre;
  private String description;
  private int quantity;
  private boolean leaf;

  public Book() {
  }

  public Book(String title, String author, String publisher,
              boolean isAvailable, int quantity, String genre, String description ) {
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.isAvailable = isAvailable;
    this.genre = genre;
    this.description = description;
    this.quantity = quantity;
  }

  public Book(int id, String title, String author, String publisher,
              boolean isAvailable, int quantity, String genre, String description ) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.isAvailable = isAvailable;
    this.genre = genre;
    this.description = description;
    this.quantity = quantity;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public boolean isLeaf() {
    return leaf;
  }

  public void setLeaf(boolean leaf) {
    this.leaf = leaf;
  }
}
