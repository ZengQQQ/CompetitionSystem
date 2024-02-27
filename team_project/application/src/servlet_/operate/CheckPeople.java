package servlet_.operate;

import druid_JDBC_utils.Druid_Utils;
import Logical_opt.dao.Group_dao;
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
import java.util.List;

@WebServlet("/checkPeople")
public class CheckPeople extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=(String) req.getSession().getAttribute("Student_ID");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
         String sql="select * from link_team.group_table where TID=?";
         connection= Druid_Utils.getConnection();
         preparedStatement=connection.prepareStatement(sql);
         preparedStatement.setString(1,id);
         resultSet=preparedStatement.executeQuery();
         if(!resultSet.next()){
             resp.getWriter().print("<script>alert('队伍目前没有成员!');"
                     +"window.location.href='http://localhost:8080/_war_exploded/showMyTeam'</script>");
         }
         else{
             List<String> list =Group_dao.return_people_id(id);
             req.setAttribute("list",list);
             req.getRequestDispatcher("/showPeople").forward(req,resp);
         }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
    }
}
