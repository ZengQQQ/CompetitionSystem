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


@WebServlet("/removePeople")
//队长将队员移出队伍
public class RemovePeople extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String leader_id=(String) req.getSession().getAttribute("Student_ID");
        String id=req.getParameter("id");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        //从队员表中删除队员
        try {
            String sql="delete from link_team.group_table where TID=? and UID=?";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,leader_id);
            preparedStatement.setString(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        //向被删除的队员发起通知
        try {
            String sql="insert into link_team.reply (send_id,receive_id,message) values (?,?,?)";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,leader_id);
            preparedStatement.setString(2,id);
            preparedStatement.setString(3,"您已被移出队伍");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        resp.getWriter().print("<script> if(confirm('您刚才移除了一名队员，是否更新队伍的空缺人数？')){" +
                "window.location.href='http://localhost:8080/_war_exploded/updateExist?id="+leader_id +
                "'}" +
                "else{window.location.href='http://localhost:8080/_war_exploded/checkPeople'}</script>" );
    }
}
