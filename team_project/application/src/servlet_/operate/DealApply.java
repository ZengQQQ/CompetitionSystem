package servlet_.operate;

import Logical_opt.domain.Application_information;
import Logical_opt.domain.Student;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/dealApply")

public class DealApply extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String receive_id=(String) req.getSession().getAttribute("Student_ID");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select num from link_team.reply where receive_id=? and message=?";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,receive_id);
            preparedStatement.setString(2,"申请加入队伍");
            resultSet=preparedStatement.executeQuery();
            if (!resultSet.next()){
                resp.getWriter().print("<script>alert('没有收到入队申请！');" +
                        "window.location.href='http://localhost:8080/_war_exploded/showMyTeam';</script>");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        //储存每条邀请的发送者学号和申请时间，以学号为key，时间为value
        Map<String,String> applications=new HashMap<String,String>();
        try {
            String sql="select time_reply,send_id from link_team.reply where receive_id=? and message=?";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,receive_id);
            preparedStatement.setString(2,"申请加入队伍");
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                applications.put(resultSet.getString("send_id"),resultSet.getString("time_reply"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        //根据申请者学号找到申请者其他信息

        //定义申请消息集合
       List<Application_information> application_list=new ArrayList<>();
        for(String id:applications.keySet()) {
            try {
                //String student_Name, String tel, String strong_point, String major
                String sql = "select Student_Name,Tel,Strong_point,Major from link_team.student_info where Student_ID=?";
                connection = Druid_Utils.getConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, id);
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()) {
                  Student student=new Student(id,
                                                    resultSet.getString("Student_Name"),
                                                    resultSet.getString("Tel"),
                                                    resultSet.getString("Strong_point"),
                                                    resultSet.getString("Major"));
                   String time=applications.get(id);
                   application_list.add(new Application_information(time,student));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                Druid_Utils.close(resultSet, preparedStatement, connection);
            }
        }
        req.setAttribute("application_list",application_list);
        req.getRequestDispatcher("/showApplication.jsp").forward(req,resp);
    }
}
