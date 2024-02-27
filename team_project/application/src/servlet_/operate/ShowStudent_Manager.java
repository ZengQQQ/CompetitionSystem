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

@WebServlet("/showStudent_Manager")
public class ShowStudent_Manager extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        PageBean<Student> pb = Student_dao.findUserByPage_Manager(currentPage,rows,condition);

        //System.out.println(pb);//测试用

        if(pb==null){
            response.getWriter().print("<script>alert('查询失败，没有匹配的信息!');" +
                    "window.location.href='http://localhost:8080/_war_exploded/showStudentByPage';</script>");
            return;
        }
        //3.将PageBean出入request
        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);//将查询条件也存入
        //4.转发到list.jsp
        request.getRequestDispatcher("/showUser(manager).jsp").forward(request,response);
    }

     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
     }
 }
