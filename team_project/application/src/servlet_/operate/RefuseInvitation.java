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

@WebServlet("/refuseInvitation")
public class RefuseInvitation extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=(String) req.getSession().getAttribute("Student_ID");
        String leader_id=req.getParameter("leader_id");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        //拒绝邀请
        try {
            String sql="delete from link_team.group_table where TID=? and UID=?";
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

        //拒绝邀请后,将对方的队伍空缺人数调回原来的值
        try{
            String sql_1="update link_team.team_table set Exist_Num=Exist_Num+1 where Student_ID_leader=?";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql_1);
            preparedStatement.setString(1,leader_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(null,preparedStatement,connection);
        }


        //通知队长
        try {
            String sql="insert into link_team.reply (send_id,receive_id,message) values(?,?,?)";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,leader_id);
            preparedStatement.setString(3,"拒绝了组队邀请");
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
