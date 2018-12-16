
package business;

/**
 *
 * @author Nathan Lee
 */
public class BookInventory {
	private String bookcode;
	private String booktitle;
	private Double bookprice;
	private int store;
	private int quantity;
	
	public BookInventory() {
		String bookcode = "";
		String booktitle = "";
		Double bookprice = 0.0;
		store = 0;
		quantity = 0;
	}
	

	public int getStore() {
		return store;
	}

	public void setStore(int store) {
		this.store = store;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
 
	public String getBookcode() {
		return bookcode;
	}

	public void setBookcode(String bookcode) {
		this.bookcode = bookcode;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}

	public Double getBookprice() {
		return bookprice;
	}

	public void setBookprice(Double bookprice) {
		this.bookprice = bookprice;
	}
}
