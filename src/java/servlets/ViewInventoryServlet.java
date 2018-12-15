
package servlets;

import business.Book;
import business.ConnectionPool;
import business.Store;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nathan Lee
 */
public class ViewInventoryServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String sql = "", msg="";
        String URL="/ViewInventory.jsp";
        int storeid=0;
        Store st = null;   
        
        ArrayList<Store> stores;
        try {
			ConnectionPool connectionpool = ConnectionPool.getInstance();
            Connection connection = connectionpool.getConnection();
            stores = (ArrayList<Store>) request.getSession().getAttribute("stores");
            storeid = Integer.parseInt(request.getParameter("storeid"));
            for (Store store: stores) {
                if (store.getStoreid() == storeid) {
                    request.getSession().setAttribute("store",store);
                }
            }
            //make inventory, fill it			
			request.setAttribute("books", getBooksForUser(connection));
			connection.close();
        } catch (Exception e) {
            msg = "Inventory error: " + e.getMessage();
            URL="/StoreSelection.jsp";
        }
        request.setAttribute("msg",msg);
        RequestDispatcher disp = getServletContext().getRequestDispatcher(URL);
        disp.forward(request,response);
    }
	
	    private ArrayList<Book> getBooksForUser(Connection connection) throws SQLException {
        ArrayList books = new ArrayList();
        String sql = "SELECT * FROM booklist ORDER BY bookID ";
        PreparedStatement prepstate = connection.prepareStatement(sql);
        ResultSet r = prepstate.executeQuery(sql);
        while (r.next()) {
            Book b = new Book();
            b.setBookid(r.getString("bookID"));
            b.setTitle(r.getString("title"));
            b.setAuthor(r.getString("author"));
            b.setPublishercode(r.getString("publisher_Code"));
			b.setBooktype(r.getString("booktype"));
			b.setPrice(r.getDouble("price"));
            books.add(b);                
        }
        r.close();
        return books;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
