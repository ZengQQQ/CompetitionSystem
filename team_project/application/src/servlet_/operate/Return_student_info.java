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
import java.util.HashMap;
import java.util.Map;

/*
在个人信息修改界面的输入框显示之前填写的内容，
便于用户对照以前的内容进行修改
 */
@WebServlet("/return_student_info")

public class Return_student_info extends HttpServlet {
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=(String) req.getSession().getAttribute("Student_ID");
        String sql="select * from link_team.student_info where Student_ID='"+id+"'";
        System.out.println(sql);
        Map<String,String> student_info=new HashMap<>();
        try {
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            resultSet.next();
            student_info.put("UName",resultSet.getString("UName"));
            student_info.put("Tel",resultSet.getString("Tel"));
            student_info.put("requirement",resultSet.getString("requirement"));
            student_info.put("requirement_leader",resultSet.getString("requirement_leader"));
            student_info.put("Strong_point",resultSet.getString("Strong_point"));
            req.setAttribute("student_info",student_info);
            req.getRequestDispatcher("finish_info.jsp").forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
    }
}
