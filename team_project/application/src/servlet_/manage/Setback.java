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

//解除用户封禁状态
@WebServlet("/setback")
public class Setback extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Student_ID=req.getParameter("student_id");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        //解封账户
        try {
            String sql="update link_team.students set state=0 where Student_ID=?";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,Student_ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        //重置相关信息
        try {
            String sql="update link_team.student_info set UName=?,Strong_point=? where Student_ID=?";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,"NAN");
            preparedStatement.setString(2,"NAN");
            preparedStatement.setString(3,Student_ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/showStudentByPage'</script>");
    }
}
