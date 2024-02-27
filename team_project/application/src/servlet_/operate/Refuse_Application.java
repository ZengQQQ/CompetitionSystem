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

@WebServlet("/refuse_Application")
public class Refuse_Application extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String leader_id=(String) req.getSession().getAttribute("Student_ID");
        String id=req.getParameter("id");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        //删除reply表中的申请
        try {
            String sql="delete from link_team.reply where send_id=? and receive_id=? and message=?";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,leader_id);
            preparedStatement.setString(3,"申请加入队伍");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        //向对方发送拒绝入队通知
        try {
            String sql="insert into  link_team.reply (send_id,receive_id,message) values (?,?,?)";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,leader_id);
            preparedStatement.setString(2,id);
            preparedStatement.setString(3,"入队申请被拒");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/dealApply';</script>");
    }
}
