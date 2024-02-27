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

@WebServlet("/invite")
public class Invite extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id_leader=(String) req.getSession().getAttribute("Student_ID");
        String id_student=req.getParameter("student_id");
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            connection= Druid_Utils.getConnection();
            String sql="select * from link_team.team_table where Student_ID_leader=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,id_leader);
            resultSet=preparedStatement.executeQuery();
            if(!resultSet.next()){
                resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/showStudentByPage';"+
                        "alert('您还不是队长，无法操作！');</script>");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }


        try{
            String sql_0="select * from link_team.group_table where TID=? and UID=? and state=1";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql_0);
            preparedStatement.setString(1,id_leader);
            preparedStatement.setString(2,id_student);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/showStudentByPage';"
                        +"alert('该用户已加入队伍！')</script>");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        try{
            String sql_0="select * from link_team.reply where send_id=? and receive_id=? and message=?";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql_0);
            preparedStatement.setString(1,id_student);
            preparedStatement.setString(2,id_leader);
            preparedStatement.setString(3,"申请加入队伍");
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/dealApply';"
                        +"alert('该用户已提交入队申请，请处理！')</script>");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }


        try{
          String sql_0="select * from link_team.group_table where TID=? and UID=?";
          connection=Druid_Utils.getConnection();
          preparedStatement=connection.prepareStatement(sql_0);
          preparedStatement.setString(1,id_leader);
          preparedStatement.setString(2,id_student);
          resultSet=preparedStatement.executeQuery();
          if(resultSet.next()){
              resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/showStudentByPage';"
                      +"alert('邀请已发出，请勿重复邀请！')</script>");
              return;
          }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
             Druid_Utils.close(resultSet,preparedStatement,connection);
        }


        try{
            String sql_2="select Exist_Num from link_team.team_table where Student_ID_leader=?";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql_2);
            preparedStatement.setString(1,id_leader);
            resultSet=preparedStatement.executeQuery();
            resultSet.next();
            if(Integer.parseInt(resultSet.getString("Exist_Num"))<=0){
                resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/showStudentByPage';"
                        +"alert('邀请超过队伍空缺人数，无法邀请！')</script>");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }

        //校验对方是否被封禁
        try{
            String sql_2="select state from link_team.students where Student_ID=?";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql_2);
            preparedStatement.setString(1,id_student);
            resultSet=preparedStatement.executeQuery();
            resultSet.next();
            if(Integer.parseInt(resultSet.getString("state"))==1){
                resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/showStudentByPage';"
                        +"alert('该用户已被封禁，无法邀请！')</script>");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_Utils.close(resultSet,preparedStatement,connection);
        }


        try{
             String sql_1="insert into link_team.group_table (TID,UID) values(?,?) ";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql_1);
            preparedStatement.setString(1,id_leader);
            preparedStatement.setString(2,id_student);
            preparedStatement.executeUpdate();
            resp.getWriter().print("<script>window.location.href='http://localhost:8080/_war_exploded/showStudentByPage';"
                    +"alert('邀请已发出，请等待对方确认')</script>");
        } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         finally {
             Druid_Utils.close(null,preparedStatement,connection);
         }



        //每次发出一个邀请，先默认队伍空缺位置-1,如果对方拒绝邀请,就再恢复原来的值
        try{
            String sql_1="update link_team.team_table set Exist_Num=Exist_Num-1 where Student_ID_leader=?";
            connection=Druid_Utils.getConnection();
            preparedStatement=connection.prepareStatement(sql_1);
            preparedStatement.setString(1,id_leader);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Druid_Utils.close(null,preparedStatement,connection);
        }
    }
}
