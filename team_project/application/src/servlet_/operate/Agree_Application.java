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

@WebServlet("/agree_Application")
public class Agree_Application extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String leader_id=(String) req.getSession().getAttribute("Student_ID");
        String id=req.getParameter("id");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        //校验空余位置，如果无空位，则提示并返回上一步
        try{
            String sql="select Exist_Num from link_team.team_table where Student_ID_leader=?";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,leader_id);
            resultSet=preparedStatement.executeQuery();
            resultSet.next();
            int num=resultSet.getInt("Exist_Num");
            if(num<=0){
                resp.getWriter().print("<script>alert('队伍空余位置不足，请扩充！');" +
                        "window.location.href='http://localhost:8080/_war_exploded/dealApply';</script>");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        //队长将申请者加入队伍
        try {
            String sql="insert into link_team.group_table (TID,UID,state) values (?,?,?)";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,leader_id);
            preparedStatement.setString(2,id);
            preparedStatement.setString(3,"1");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        //设置队伍的空余位置-1
        try {
            String sql="update link_team.team_table set Exist_Num=Exist_Num-1 where Student_ID_leader=?";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,leader_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        //删除reply表中的申请信息
        try {
            String sql="delete from link_team.reply where send_id=? and receive_id=? and message=?";
            connection=Druid_Utils.getConnection();
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
        //队长向申请者发出通知
        try {
            String sql="insert into  link_team.reply (send_id,receive_id,message) values (?,?,?)";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,leader_id);
            preparedStatement.setString(2,id);
            preparedStatement.setString(3,"入队申请通过");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/dealApply';</script>");
    }
}
