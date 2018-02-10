package pers.yicong.foodvenger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


@WebServlet(name = "FoodListServlet",
        urlPatterns = {"/FoodList"})

public class FoodListServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        //SQL Setup Connection Info
        String loginUser = "root";
        String loginPassword = "950929huang";
        String loginUrl = "jdbc:mysql://localhost:3306/restdb";

        //Content type
        response.setContentType("text/html");


        //Output stream to webpage
        PrintWriter out = response.getWriter();

        //CSS
        out.println("<head>"
                + "<title>Top 20 Rating Restaurants</title>"
                + "<style>"
                + "td {"
                + "	padding:20px"
                + "}"
                + "</style>"
                + "</head>");

        //Body
        out.println("<body>");

        //Title
        out.println("<h2>Top 20 Rating Restaurants</h2>");

        try {
            //SQL Setup JDBC Connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPassword);

            //SQL statement object and query
            Statement statement = dbcon.createStatement();
            String query = "SELECT  rs.name, rs.addr, c.type AS cuisine, d.name AS dish, r.rating FROM restdb.restaurants AS rs, cuisine AS c,cuisine_in_restau AS cr, ratings AS r , dishes AS d, dishes_in_restau AS dr WHERE d.id = dr.did AND dr.rid = rs. id AND rs.id = cr.rid AND cr.cid = c.id AND  r.rid = rs.id ORDER BY r.rating DESC LIMIT 20;";
            ResultSet resultSet = statement.executeQuery(query);
            out.println("<TABLE border>");
            out.println("<thead><tr><th>Restaurant</th><th>Address</th><th>Cuisine</th>"
                    + "<th>Dish List</th><th>Ratings</th></tr></thead>");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String addr = resultSet.getString("addr");
                String cuisine = resultSet.getString("cuisine");
                String dish = resultSet.getString("dish");
                String rating = resultSet.getString("rating");
                out.println("<tr style='border: 15px solid gray'>"
                        + "<td>" + name + "</td>"
                        + "<td>" + addr + "</td>"
                        + "<td>" + cuisine + "</td>"
                        + "<td>" + dish + "</td>"
                        + "<td>" + rating + "</td>"
                        + "</tr>");

            }

            out.println("</table>");
            resultSet.close();


            statement.close();
            dbcon.close();


        } catch (Exception e) {
            out.println("<html><head><title>WEB ERROR</title></head></body></html>");
            return;
        }
        out.println("</body>");
        out.close();


    }


}
