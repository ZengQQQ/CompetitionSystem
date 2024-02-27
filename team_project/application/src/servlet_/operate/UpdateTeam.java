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

@WebServlet("/updateTeam")
public class UpdateTeam extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String TName=req.getParameter("TName");
        String Introduction=req.getParameter("Introduction");
        String Exist_Num=req.getParameter("Exist_Num");
        String Student_ID_leader=(String) req.getSession().getAttribute("Student_ID");
        String sql="update link_team.team_table set TName=?,Introduction=?,Exist_Num=? where Student_ID_leader=?";
        System.out.println(TName+Introduction+Exist_Num+Student_ID_leader);
        System.out.println(sql);
        try {
           connection= Druid_Utils.getConnection();
           preparedStatement=connection.prepareStatement(sql);
           preparedStatement.setString(1,TName);
           preparedStatement.setString(2,Introduction);
           preparedStatement.setString(3,Exist_Num);
           preparedStatement.setString(4,Student_ID_leader);
           preparedStatement.executeUpdate();
           resp.getWriter().print("<script>alert('队伍信息修改成功！');"+
                   "window.location.href='http://localhost:8080/_war_exploded/showMyTeam'</script>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
    }
}
