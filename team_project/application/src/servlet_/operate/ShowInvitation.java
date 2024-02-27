package servlet_.operate;

import Logical_opt.domain.Team;
import druid_JDBC_utils.Druid_Utils;
import db.logical_op;
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
import java.util.List;


@WebServlet("/showInvitation")
public class ShowInvitation extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         Connection connection =null;
         PreparedStatement preparedStatement=null;
         ResultSet resultSet=null;
         String id=(String) req.getSession().getAttribute("Student_ID");
         try {
             String sql="select * from link_team.group_table where UID=? and state=0";
             connection= Druid_Utils.getConnection();
             preparedStatement=connection.prepareStatement(sql);
             preparedStatement.setString(1,id);
             resultSet=preparedStatement.executeQuery();
             if(!resultSet.next()){
                 resp.getWriter().print("<script>alert('您没有收到组队邀请');"+
                         "window.location.href='http://localhost:8080/_war_exploded/home_page.jsp';</script>");
             }
             else {
                 List<Team> list=logical_op.returnInvitation(id);
                 req.setAttribute("invitation",list);
                 req.getRequestDispatcher("/showInvitation.jsp").forward(req,resp);
             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }finally {
             Druid_Utils.close(resultSet,preparedStatement,connection);
         }
    }
}
