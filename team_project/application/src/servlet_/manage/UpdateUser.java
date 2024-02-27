package servlet_.manage;

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

//编辑认证名单的用户信息
@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String id=req.getParameter("id");
        String major=req.getParameter("major");
        try {
            String sql="update link_team.students set Major=? where Student_ID=?";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,major);
            preparedStatement.setString(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        try {
            String sql="update link_team.student_info set Major=? where Student_ID=?";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,major);
            preparedStatement.setString(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/showStudent_Manager'</script>");
    }
}
