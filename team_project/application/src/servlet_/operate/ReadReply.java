package servlet_.operate;

import druid_JDBC_utils.Druid_Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/readReply")
public class ReadReply extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String send_id=req.getParameter("send_id");
       String receive_id=(String) req.getSession().getAttribute("Student_ID");
       String message=(String) req.getParameter("message") ;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
       try{
           String sql="delete from link_team.reply where send_id=? and receive_id=? and message=?";
           connection= Druid_Utils.getConnection();
           preparedStatement=connection.prepareStatement(sql);
           preparedStatement.setString(1,send_id);
           preparedStatement.setString(2,receive_id);
           preparedStatement.setString(3,message);
           preparedStatement.executeUpdate();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }finally {
           Druid_Utils.close(resultSet,preparedStatement,connection);
       }

       resp.sendRedirect("http://localhost:8080/_war_exploded/checkReply");
    }
}
