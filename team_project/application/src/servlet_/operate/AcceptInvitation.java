package servlet_.operate;

import druid_JDBC_utils.Druid_Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//执行接受操作
@WebServlet("/acceptInvitation")
public class AcceptInvitation extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=(String) req.getSession().getAttribute("Student_ID");
        String leader_id=req.getParameter("leader_id");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        //用户接受队长的邀请
        try {
            String sql="update link_team.group_table set state=1 where TID=? and UID=? ";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,leader_id);
            preparedStatement.setString(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

       //队长发出邀请后已经默认设置队伍的空缺人数-1,所以这里不需要设置

        //通知队长
        try {
            String sql="insert into link_team.reply (send_id,receive_id,message) values(?,?,?)";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,leader_id);
            preparedStatement.setString(3,"接受了组队邀请");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        resp.sendRedirect("http://localhost:8080/_war_exploded/showInvitation");
    }
}
