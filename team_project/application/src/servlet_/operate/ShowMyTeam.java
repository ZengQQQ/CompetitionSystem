package servlet_.operate;

import Logical_opt.dao.Team_dao;
import Logical_opt.domain.Team;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showMyTeam")
public class ShowMyTeam extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=(String)req.getSession().getAttribute("Student_ID");
        Team myTeam=Team_dao.showMyTeam(id);
        req.setAttribute("myTeam",myTeam);
        req.getRequestDispatcher("/myTeam.jsp").forward(req,resp);
    }
}
