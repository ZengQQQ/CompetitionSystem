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

@WebServlet("/apply")
public class Apply extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String leader_id=req.getParameter("leader_id");
        String id=(String) req.getSession().getAttribute("Student_ID");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        //校验是否已经发送过申请
        try {
            String sql="select num from link_team.reply where send_id=? and receive_id=? and message=?";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,leader_id);
            preparedStatement.setString(3,"申请加入队伍");
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                resp.getWriter().print("<script>alert('您已经发送过申请，请等待对方回复！');" +
                        "window.location.href='http://localhost:8080/_war_exploded/showTeamByPage';</script>");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        //校验是否已经在队伍中
        try {
            String sql="select state from link_team.group_table where TID=? and UID=? ";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,leader_id);
            preparedStatement.setString(2,id);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                int state= resultSet.getInt("state");
                if(state==1) {
                    resp.getWriter().print("<script>alert('您已经在队伍中！');" +
                            "window.location.href='http://localhost:8080/_war_exploded/showTeamByPage';</script>");
                    return;
                }
                else {
                    resp.getWriter().print("<script>alert('该队伍已向您发出邀请，请查看邀请列表！');" +
                            "window.location.href='http://localhost:8080/_war_exploded/showInvitation';</script>");
                    return;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        //校验对方是否被封禁
        try{
            String sql_2="select state from link_team.students where Student_ID=?";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql_2);
            preparedStatement.setString(1,leader_id);
            resultSet=preparedStatement.executeQuery();
            resultSet.next();
            if(Integer.parseInt(resultSet.getString("state"))==1){
                resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/showStudentByPage';"
                        +"alert('该用户已被封禁，无法提交申请！')</script>");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        //向队长发送申请消息
        try {
            String sql="insert into link_team.reply (send_id,receive_id,message) values(?,?,?)";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,leader_id);
            preparedStatement.setString(3,"申请加入队伍");
            preparedStatement.executeUpdate();
            resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/showTeamByPage';</script>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
    }

}
