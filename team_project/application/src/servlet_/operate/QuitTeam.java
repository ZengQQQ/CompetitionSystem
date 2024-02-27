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

//用户退出当前队伍
@WebServlet("/quitTeam")
public class QuitTeam extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String leader_id=req.getParameter("leader_id");
        String id=(String) req.getSession().getAttribute("Student_ID");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        //删除group_table表中相关的数据
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


        //成员退队后自动设置队伍空余位置+1
        try {
            String sql="update link_team.team_table set Exist_Num=Exist_Num+1 where Student_ID_leader=?";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,leader_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        //向队长发送通知
        try {
            String sql="insert into link_team.reply (send_id,receive_id,message) values (?,?,?)";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,leader_id);
            preparedStatement.setString(3,"已退出队伍");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        req.getRequestDispatcher("/showMyTeam_Student").forward(req,resp);
    }
}
