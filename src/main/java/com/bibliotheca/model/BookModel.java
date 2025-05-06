	package com.bibliotheca.model;
	
	public class BookModel {
		private int bookId;
		private String bookName;
		private String author;
		private String genre;
		private String description;
		private String publisher;
		private String availability;
		private String bookImage;
		
		
		public BookModel() {}


		public BookModel(int bookId, String bookName, String author, String genre, String description, String publisher,
				String availability, String bookImage) {
			super();
			this.bookId = bookId;
			this.bookName = bookName;
			this.author = author;
			this.genre = genre;
			this.description = description;
			this.publisher = publisher;
			this.availability = availability;
			this.bookImage = bookImage;
		}


		public int getBookId() {
			return bookId;
		}


		public void setBookId(int bookId) {
			this.bookId = bookId;
		}


		public String getBookName() {
			return bookName;
		}


		public void setBookName(String bookName) {
			this.bookName = bookName;
		}


		public String getAuthor() {
			return author;
		}


		public void setAuthor(String author) {
			this.author = author;
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


		public String getPublisher() {
			return publisher;
		}


		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}


		public String getAvailability() {
			return availability;
		}


		public void setAvailability(String availability) {
			this.availability = availability;
		}


		public String getBookImage() {
			return bookImage;
		}


		public void setBookImage(String bookImage) {
			this.bookImage = bookImage;
		}
	
		
	}
	
