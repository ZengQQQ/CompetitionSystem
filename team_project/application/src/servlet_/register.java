package servlet_;

import db.logical_op;
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


@WebServlet("/register_program")
public class register extends HttpServlet {
    String Student_Name=null;
    String pwd1=null;
    String pwd2=null;
    String Student_ID=null;
    String phone=null;
    String UName=null;
    String Major=null;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String Student_ID=req.getParameter("Student_ID");
        try {
            String sql="select Student_Name,Major from link_team.students where Student_ID=?";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,Student_ID);
            resultSet=preparedStatement.executeQuery();
            if(!resultSet.next()){
                //注册学号不在授权名单中
                resp.getWriter().print("<script>alert('注册学号未授权');" +
                        "window.location.href=`http://localhost:8080/_war_exploded/register.jsp`</script>");
                return;
            }
            this.Student_Name=resultSet.getString("Student_Name");
            this.Major=resultSet.getString("Major");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
       this.pwd1=req.getParameter("pwd1");
       this.pwd2=req.getParameter("pwd2");
       this.Student_ID=req.getParameter("Student_ID");
       this.phone=req.getParameter("phone");
       this.UName=req.getParameter("UName");
       //校验两次密码是否一致
        if(! pwd1.equals(pwd2)){
            resp.getWriter().print("<script>alert(`两次设置的密码不一致！请重新设置`);" +
                    "window.location.href=`http://localhost:8080/_war_exploded/register.jsp`</script>");
        }
        //校验用户是否已经存在并完成注册
        else if(logical_op.U_signUp(pwd1,Student_Name,Student_ID,UName,phone,Major)) {
            //注册成功后，使用req的sendRedirect再次跳转到登录页
            resp.getWriter().print("<script>alert(`注册成功！点击跳转到登录页`);" +
                    "window.location.href=`http://localhost:8080/_war_exploded/login.jsp`</script>");
        }
        else{
            //注册失败,重新跳转到注册界面
            resp.getWriter().print("<script>alert(`注册失败！用户已经存在，请重试`);" +
                    "window.location.href=`http://localhost:8080/_war_exploded/register.jsp`</script>");
        }
    }
}
