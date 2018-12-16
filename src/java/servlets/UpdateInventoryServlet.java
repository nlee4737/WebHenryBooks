
package servlets;

import business.BookInventory;
import business.ConnectionPool;
import business.Store;
import business.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* @author Nathan Lee */

//Coming here from ViewInventory
//Handle UpdateButton
//Handle CancelButton

public class UpdateInventoryServlet extends HttpServlet {
	private RequestDispatcher disp = null;
	private String bookcode = null;
	private User loggedInUser = null;
	private Store selectedStore = null;
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
		String intent = request.getParameter("intent"); if (intent == null) { intent = "init"; }		
		ConnectionPool connectionpool = ConnectionPool.getInstance();
		Connection connection = connectionpool.getConnection();
		String msg = "";
		
		switch (intent) {
			case "init":						
				bookcode = request.getParameter("bookcode");
				loggedInUser = (User) request.getSession().getAttribute("user");
				selectedStore = (Store) request.getSession().getAttribute("store");
				
				disp = getServletContext().getRequestDispatcher("/UpdateInventory.jsp");
				disp.forward(request, response);
			break;
			case "update":				
				try {
					int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
					if (newQuantity < 0) {
						msg = "Enter something not negative";
					} else {
					updateBook(connection, newQuantity);
					disp = getServletContext().getRequestDispatcher("/StoreSelection.jsp");
					msg = "Quantity Updated Successfully!";
					}
				} catch (NumberFormatException e) {
					msg = "Enter a number: " + e.getMessage();
					disp = getServletContext().getRequestDispatcher("/UpdateInventory.jsp");																					
				} catch ( SQLException e) {
					msg = "SQL Error: " + e.getMessage();
					disp = getServletContext().getRequestDispatcher("/UpdateInventory.jsp");																					
				} catch (NullPointerException e) {
					msg = "Nothing's in the box: " +e.getMessage();
					disp = getServletContext().getRequestDispatcher("/UpdateInventory.jsp");																					
				}
				disp.forward(request, response);
			break;
			case "cancel":
				disp = getServletContext().getRequestDispatcher("/StoreSelection.jsp");
				msg = "Operation Cancelled";
				disp.forward(request, response);
			break;
		}
		request.getSession().setAttribute("msg",msg);
		connectionpool.freeConnection(connection);
    }
	
	private void updateBook(Connection connection, int newQuantity) throws SQLException{
		String sql = "UPDATE bookinv SET OnHand = ? WHERE storeID = ? and bookID = ?";
		PreparedStatement prepstate = connection.prepareStatement(sql);
		
		prepstate.setInt(1, newQuantity);
		prepstate.setInt(2, selectedStore.getStoreid());
		prepstate.setString(3, bookcode);
		
		prepstate.executeUpdate();
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
