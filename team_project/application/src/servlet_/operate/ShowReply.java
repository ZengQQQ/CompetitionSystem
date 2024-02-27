package servlet_.operate;

import Logical_opt.domain.Reply;
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
import java.util.List;

//根据用户学号遍历回复表，显示对方发出的回复
@WebServlet("/showReply")
public class ShowReply extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String id=(String) req.getSession().getAttribute("Student_ID");
        List<Reply> list=new ArrayList<Reply>();
       Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            String sql="select * from link_team.reply where receive_id=?";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Reply(resultSet.getString("time_reply"),
                        resultSet.getString("send_id"),
                        resultSet.getString("message")));
            }
            req.setAttribute("list",list);
            req.getRequestDispatcher("/showReply.jsp").forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
    }
}
