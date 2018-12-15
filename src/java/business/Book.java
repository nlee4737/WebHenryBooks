package business;

/*@author Nathan Lee*/
public class Book {

	private String bookid, title, author, publishercode, booktype;
	private double price;

	public Book() {
		bookid = "";
		title = "";
		author = "";
		publishercode = "";
		booktype = "";
		price = 0;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
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

	public String getPublishercode() {
		return publishercode;
	}

	public void setPublishercode(String publishercode) {
		this.publishercode = publishercode;
	}

	public String getBooktype() {
		return booktype;
	}

	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
