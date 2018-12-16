
package servlets;

import business.ConnectionPool;
import business.Store;
import business.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nathan Lee
 */
public class LogonServlet extends HttpServlet {
    private String msg="";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        msg="";
        response.setContentType("text/html;charset=UTF-8");
        String URL = "/Logon.jsp";        
        int userid = 0;
        int pattempt;
        ArrayList<Store> stores = new ArrayList();
        User u = null;
        
        try {
            ConnectionPool connectionpool = ConnectionPool.getInstance();
            Connection connection = connectionpool.getConnection();
            
            userid=Integer.parseInt(request.getParameter("userid").trim());
            pattempt = Integer.parseInt(request.getParameter("password").trim());
            
            u = userLogin(connection, userid, pattempt);
            if (u != null) {       
                request.getSession().setAttribute("user",u);
                
                stores = getStoresForUser(connection, u.getUserid());                
                if (stores.size() > 0) {
                    request.getSession().setAttribute("stores", stores);
                    URL ="/StoreSelection.jsp";
                } else {
                    msg += "No stores read from stores table<br>";
                }
                connectionpool.freeConnection(connection);
            }
         } catch (SQLException e) {
             msg += "SQL Exception: " + e.getMessage() + "<br>";
         } catch (NumberFormatException e) {
             msg += "Password not numeric. <br>";
         }
		Cookie acct = new Cookie("acct",String.valueOf(u.getUserid()));
			acct.setMaxAge(60*2);
			acct.setPath("/");
			response.addCookie(acct);
        request.getSession().setAttribute("msg",msg);
        RequestDispatcher disp = getServletContext().getRequestDispatcher(URL);
        disp.forward(request,response);
    }

	private User userLogin(Connection connection, int userid, int pattempt) throws SQLException {
        User u = new User();
        
        String sql = "SELECT * FROM users WHERE userID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, userid);
        ResultSet results = ps.executeQuery();
		
        if (results.next()) {   
            u.setUserid(userid);
            u.setPassword(results.getInt("userPassword"));
            u.setPwdattempt(pattempt);
            if (u.isAuthenticated()) {
                u.setUsername(results.getString("userName"));
                u.setStoreid(results.getInt("storeID"));
                u.setAdminlevel(results.getString("adminLevel"));
            } else {
                msg += "User " + userid + " found but not authenticated.<br>";
                return null;
            } 
        } else {
            msg += "User not found in database.<br>";
            return null;
        }

        results.close();
        return u;
    }
    
    private ArrayList<Store> getStoresForUser(Connection connection, int userid) throws SQLException {
        ArrayList stores = new ArrayList();
        
        //Statement s = connection.createStatement();
        String sql = "SELECT * FROM stores ORDER BY StoreName ";
        PreparedStatement prepstate = connection.prepareStatement(sql);
        ResultSet r = prepstate.executeQuery(sql);
        while (r.next()) {
            Store st = new Store();
            st.setStoreid(r.getInt("storeID"));
            st.setStorename(r.getString("storeName"));
            st.setStoreaddr(r.getString("storeAddr"));
            st.setStoreemp(r.getInt("storeEmp"));
            stores.add(st);                
        }
        r.close();
        return stores;
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
