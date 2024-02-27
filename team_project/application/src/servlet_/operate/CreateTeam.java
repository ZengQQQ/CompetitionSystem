package servlet_.operate;

import db.logical_op_2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/createTeam")
public class CreateTeam extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String TName=req.getParameter("TName");
       String Introduction=req.getParameter("Introduction");
       String Max_Num=req.getParameter("Max_Num");
       HttpSession session= req.getSession();
       String Student_ID_leader=(String) session.getAttribute("Student_ID");
       System.out.println("队长的id为"+Student_ID_leader);
        logical_op_2.createTeam(TName,Max_Num,Introduction,Student_ID_leader);
        resp.getWriter().print("<script>alert('创建队伍成功！');"+
                "window.location.href='http://localhost:8080/_war_exploded/home_page.jsp'</script>");
    }
}
