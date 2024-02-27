package servlet_.operate;

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
import java.util.List;

@WebServlet("/showTeammate")
public class ShowTeammate extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String leader_id=req.getParameter("leader_id");
        String id=(String) req.getSession().getAttribute("Student_ID");
        List<String> list_id=new ArrayList<String>();
        List<Student> list_teammate=new ArrayList<Student>();
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        System.out.println("准备查询队友id");
        //查找队友的id
        try {
            String sql="select * from link_team.group_table where TID=? and UID!=? and state=1";
            connection= Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,leader_id);
            preparedStatement.setString(2,id);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                list_id.add(resultSet.getString("UID"));
                while (resultSet.next()){
                    list_id.add(resultSet.getString("UID"));
                }
            }else {
                //如果没查到，则只查出队长并且返回
                System.out.println("没查到其他队友");
                Student leader=showLeader(leader_id);
                System.out.println("查到了队长");
                req.setAttribute("leader",leader);
                req.getRequestDispatcher("/showTeammate_2.jsp").forward(req,resp);
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }


        //通过队友id查出所有队友的信息
        for (String teammate_id: list_id){
            System.out.println("遍历开始");
            try {
                String sql="select * from link_team.student_info where Student_ID=?";
                connection= Druid_Utils.getConnection();
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,teammate_id);
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    list_teammate.add(new Student(resultSet.getString("Student_ID"),
                            resultSet.getString("Student_Name"),
                            resultSet.getString("Tel"),
                            resultSet.getString("Strong_point"),
                            resultSet.getString("Major")));
                  }
                } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                Druid_Utils.close(resultSet,preparedStatement,connection);
            }
        }
        Student leader=showLeader(leader_id);
        req.setAttribute("leader",leader);
        req.setAttribute("teammates",list_teammate);
        req.getRequestDispatcher("/showTeammate.jsp").forward(req,resp);
    }

    public Student showLeader(String leader_id){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String sql="select * from link_team.student_info where Student_ID=?";
        try {
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,leader_id);
            resultSet=preparedStatement.executeQuery();
            //String student_ID, String student_Name, String tel,
            // String strong_point,String major
            resultSet.next();
            return new Student(resultSet.getString("Student_ID"),
                               resultSet.getString("Student_Name"),
                               resultSet.getString("Tel"),
                               resultSet.getString("Strong_point"),
                               resultSet.getString("Major"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }
    }
}
