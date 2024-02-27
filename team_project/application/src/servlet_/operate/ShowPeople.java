package servlet_.operate;



import Logical_opt.domain.Student;
import druid_JDBC_utils.Druid_Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

@WebServlet("/showPeople")
public class ShowPeople extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> list= (List<String>) req.getAttribute("list");
        List<Student> people_list=new ArrayList<Student>();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
       for (String id:list) {
           try {
               String sql = "select * from link_team.Student_info where Student_ID=?";
               connection = Druid_Utils.getConnection();
               preparedStatement = connection.prepareStatement(sql);
               preparedStatement.setString(1, id);
               resultSet=preparedStatement.executeQuery();
               while (resultSet.next()){
                   people_list.add(new Student(resultSet.getString("Student_ID"),
                                               resultSet.getString("Student_Name"),
                                               resultSet.getString("Tel"),
                                               resultSet.getString("Strong_point"),
                                               resultSet.getString("Major")));
               }
           } catch (SQLException e) {
               throw new RuntimeException(e);
           } finally {
               Druid_Utils.close(resultSet, preparedStatement, connection);
           }
       }
     req.setAttribute("people_list",people_list);
     req.getRequestDispatcher("/myPeople.jsp").forward(req,resp);
    }
}
