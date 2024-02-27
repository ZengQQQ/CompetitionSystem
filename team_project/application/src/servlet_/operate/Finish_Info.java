package servlet_.operate;
import db.logical_op_2;
import druid_JDBC_utils.Druid_Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Finish_Info")
public class Finish_Info extends HttpServlet {
    String Student_ID=null;
    String UID=null;
    String requirement=null;
    String requirement_leader=null;
    String Strong_point=null;
    String Grade=null;
    String Major=null;
    String UName=null;
    String Tel=null;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String id=(String) req.getSession().getAttribute("Student_ID");
        this.Student_ID= (String) req.getSession().getAttribute("Student_ID");
        this.UID=(String) req.getSession().getAttribute("UID");
        this.requirement=req.getParameter("requirement");
        this.requirement_leader=req.getParameter("requirement_leader");
        this.Strong_point=req.getParameter("Strong_point");
        this.Grade=id.substring(0,2);
        this.UName=req.getParameter("UName");
        this.Tel=req.getParameter("Tel");
        //覆盖昵称的session
        HttpSession session=req.getSession();
        session.setAttribute("UName",UName);
        //通过原始信息表同步学生专业
        try {
            String sql="select Major from link_team.students where Student_ID=?";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.executeQuery();
            resultSet=preparedStatement.executeQuery();
            resultSet.next();
            String Major=resultSet.getString("Major");
            this.Major=Major;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
        logical_op_2.U_Finish(UID,requirement,requirement_leader,Strong_point,Grade,Major,UName,Tel);
        resp.getWriter().print("<script>alert('信息完善成功！');"+"window.location.href=`http://localhost:8080/_war_exploded/home_page.jsp`</script>");
    }
}
