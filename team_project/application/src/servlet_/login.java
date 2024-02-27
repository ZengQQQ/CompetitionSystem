package servlet_;

import db.logical_op;
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



@WebServlet("/login_program")
public class login extends HttpServlet {
    public String Student_ID=null;
    public String pwd=null;
    public String MID=null;
    public String UID=null;
    public String UName=null;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户验证码
        String verifycode = req.getParameter("verifycode");
        //验证码校验操作
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码不正确
            //提示信息
            req.setAttribute("login_msg","验证码错误！");
            //跳转登录页面
            req.getRequestDispatcher("/login.jsp").forward(req,resp);

            return; //如果验证码错误，直接返回

        }
        //校验是不是管理员
        this.Student_ID=req.getParameter("Student_Id");
        this.pwd=req.getParameter("pwd");
        this.MID=logical_op.M_login(this.Student_ID,this.pwd);
        if(MID=="1"){
            session.setAttribute("Student_ID",Student_ID);
            session.setAttribute("UID",UID);
            //如果是管理员则登录
            resp.getWriter().print("<script>alert('管理员登录成功!');" +
                    "window.location.href='http://localhost:8080/_war_exploded/home_page(manager).jsp?id="+Student_ID+"';</script>");
            return;
        }
        //不是管理员，则继续下面的验证
        //System.out.println(this.Student_ID+"\n"+this.pwd);
        this.UID=logical_op.U_login(this.Student_ID,this.pwd);
        try {
            //如果输入错误重新跳转到登录页面
            if(UID.equals("0")){
                resp.getWriter().print("<script>alert('账号或密码错误，请重试!');" +
                        "window.location.href='http://localhost:8080/_war_exploded/login.jsp';</script>");
            }
            //如果账号密码正确，但是用户被封禁
            else if(UID.equals("-1")){
                resp.getWriter().print("<script>alert('账号已被封禁!');" +
                        "window.location.href='http://localhost:8080/_war_exploded/login.jsp';</script>");
            }
            else {
                Connection connection=null;
                PreparedStatement preparedStatement=null;
                ResultSet resultSet=null;
                //获取Session对象
                session.setAttribute("Student_ID",Student_ID);
                session.setAttribute("UID",UID);
                //根据学号查询昵称
                try {
                    String sql="select UName from link_team.student_info where Student_ID=?";
                    connection= Druid_Utils.getConnection();
                    preparedStatement=connection.prepareStatement(sql);
                    preparedStatement.setString(1,Student_ID);
                    resultSet=preparedStatement.executeQuery();
                    resultSet.next();
                    UName =resultSet.getString("UName");
                    session.setAttribute("UName",UName);
                }
                catch (SQLException e){
                    throw new RuntimeException(e);
                }
                finally {
                    Druid_Utils.close(resultSet,preparedStatement,connection);
                }
                //在跳转前显示弹窗，点击弹窗才会开始跳转
                resp.getWriter().print("<script>alert('登录成功!');" +
                        "window.location.href='http://localhost:8080/_war_exploded/home_page.jsp?id="+Student_ID+"';</script>");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
