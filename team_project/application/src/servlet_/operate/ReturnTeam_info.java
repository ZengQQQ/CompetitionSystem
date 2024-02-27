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
import java.util.HashMap;
import java.util.Map;

/*
在队伍信息修改界面的输入框显示之前填写的内容，
便于用户对照以前的内容进行修改
 */
@WebServlet("/returnTeam_info")
public class ReturnTeam_info extends HttpServlet {
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String id=(String) req.getSession().getAttribute("Student_ID");
       String sql="select * from link_team.team_table where Student_ID_leader='"+id+"'";
       System.out.println(sql);
        Map<String,String> team_info=new HashMap<>();
       try {
           connection= Druid_Utils.getConnection();
           preparedStatement=connection.prepareStatement(sql);
           resultSet=preparedStatement.executeQuery();
           resultSet.next();
           team_info.put("TName",resultSet.getString("TName"));
           team_info.put("Introduction",resultSet.getString("Introduction"));
           team_info.put("Exist_Num",resultSet.getString("Exist_Num"));
           req.setAttribute("team_info",team_info);
           req.getRequestDispatcher("/updateTeam.jsp").forward(req,resp);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       finally {
           Druid_Utils.close(resultSet,preparedStatement,connection);
       }
    }
}
