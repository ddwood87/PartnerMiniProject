package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.PlayerGroupHelper;
import model.PlayerGroup;

/**
 * Servlet implementation class listGroupsServlet
 */
@WebServlet("/listGroupsServlet")
public class listGroupsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PlayerGroupHelper pgh = new PlayerGroupHelper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listGroupsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());	
		List<PlayerGroup> allGroups = pgh.listAllGroups();
        request.setAttribute("allGroups", allGroups);
        getServletContext().getRequestDispatcher("/groupList.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
