package servlet_.operate;

import Logical_opt.dao.Student_dao;
import Logical_opt.domain.PageBean;
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
import java.util.Map;


    @WebServlet("/showStudentByPage")
    public class ShowStudentByPage extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");

            //获取操作者的id，在将来的查询列表中将操作者的信息排除
            String id=(String) request.getSession().getAttribute("Student_ID");

            //1.获取参数
            String currentPage = request.getParameter("currentPage");//当前页码
            String rows = request.getParameter("rows");//每页显示条数

            if(currentPage == null || "".equals(currentPage)){
                currentPage = "1";
            }
            if(rows == null || "".equals(rows)){
                rows = "5";
            }

            //获取条件查询的参数
            Map<String, String[]> condition = request.getParameterMap();

            System.out.println(currentPage+"\n"+rows+"\n"+condition.toString());
            //2.调用查询
            PageBean<Student> pb = Student_dao.findUserByPage(currentPage,rows,condition,id);

            //System.out.println(pb);//测试用

            if(pb==null){
                response.getWriter().print("<script>alert('查询失败，没有匹配的信息!');" +
                                               "window.location.href='http://localhost:8080/_war_exploded/showStudentByPage';</script>");
               return;
            }
            //3.将PageBean出入request
            request.setAttribute("pb",pb);
            request.setAttribute("condition",condition);//将查询条件也存入
            //验证操作者是不是管理员
            try {
                String sql="select num from link_team.manager where id=?";
                Connection connection= Druid_Utils.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,id);
                ResultSet resultSet=preparedStatement.executeQuery();
                if (resultSet.next()){
                    //如果是管理员,则跳转到管理员界面
                    request.getRequestDispatcher("/showStudents(manager).jsp").forward(request,response);
                    return;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //4.转发到list.jsp
            request.getRequestDispatcher("/showUser.jsp").forward(request,response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request, response);
        }
    }


