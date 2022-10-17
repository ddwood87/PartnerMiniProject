package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.PlayerGroupHelper;
import data.PlayerHelper;
import model.PlayerGroup;

/**
 * Servlet implementation class GroupNavigationServlet
 */
@WebServlet("/groupNavigationServlet")
public class GroupNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupNavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PlayerGroupHelper pgh = new PlayerGroupHelper();
		PlayerHelper ph = new PlayerHelper();
		
		String action = request.getParameter("action");
		if (action == null) {
			getServletContext().getRequestDispatcher("/listGroupsServlet").forward(request, response);
		} else if (action.equals("edit")) {
			try {
				Integer id = Integer.parseInt(request.getParameter("id"));
				PlayerGroup groupToEdit = pgh.getGroupById(id);
				request.setAttribute("groupToEdit", groupToEdit);
				request.setAttribute("allPlayers", ph.listAllPlayers());
				if (ph.listAllPlayers().isEmpty()) {
					request.setAttribute("allPlayers", " ");
				}
				getServletContext().getRequestDispatcher("/editgroup.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				getServletContext().getRequestDispatcher("/viewAllListsServlet").forward(request, response);
			}
		} else if (action.equals("delete")) {
			try {
				Integer id = Integer.parseInt(request.getParameter("id"));
				PlayerGroup groupToDelete = pgh.getGroupById(id);
				pgh.deleteGroup(groupToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to click a button");
			} finally {
				getServletContext().getRequestDispatcher("/listGroupsServlet").forward(request, response);
			}
		}
	}
}
