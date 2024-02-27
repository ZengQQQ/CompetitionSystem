package servlet_.operate;

import Logical_opt.domain.Team;
import druid_JDBC_utils.Druid_Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showMyTeam_Student")
//显示作为成员加入的队伍
public class ShowMyTeam_Student extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=(String)req.getSession().getAttribute("Student_ID");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select TID from link_team.group_table where UID=? and state=1";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            resultSet=preparedStatement.executeQuery();
            if(!resultSet.next()){
                resp.getWriter().print("<script> alert('您没有以成员的身份加入任何队伍');"
                        +"window.location.href='http://localhost:8080/_war_exploded/home_page.jsp'</script>");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        //定义队长学号列表,用于储存加入的队伍的队长的学号
        List<String> leader_list=new ArrayList<String>();
        try {
            String sql="select TID from link_team.group_table where UID=? and state=1";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                leader_list.add(resultSet.getString("TID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        //按照队长的学号依次查出相应的队伍
        List<Team> team_list=new ArrayList<Team>();
       for (String leader_id:leader_list) {
           try {
               String sql = "select * from link_team.team_table where Student_ID_leader=?";
               connection = Druid_Utils.getConnection();
               preparedStatement = connection.prepareStatement(sql);
               preparedStatement.setString(1, leader_id);
               resultSet = preparedStatement.executeQuery();
               while (resultSet.next()) {
                   Team team=new Team(resultSet.getString("TName"),
                           resultSet.getString("Introduction"),
                           resultSet.getString("Start_time"),
                           Integer.parseInt(resultSet.getString("Exist_Num")),
                           resultSet.getString("Student_ID_leader"));
                   team_list.add(team);
               }
           } catch (SQLException e) {
               throw new RuntimeException(e);
           } finally {
               Druid_Utils.close(resultSet, preparedStatement, connection);
           }
       }

       req.setAttribute("team_list",team_list);
       req.getRequestDispatcher("/showTeam(Student).jsp").forward(req,resp);
    }
}
