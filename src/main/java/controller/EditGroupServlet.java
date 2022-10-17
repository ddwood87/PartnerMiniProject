package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.PlayerGroupHelper;
import data.PlayerHelper;
import model.Player;
import model.PlayerGroup;

/**
 * Servlet implementation class EditGroupServlet
 */
@WebServlet("/editGroupServlet")
public class EditGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditGroupServlet() {
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
		PlayerHelper ph = new PlayerHelper();
		PlayerGroupHelper pgh = new PlayerGroupHelper();
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		PlayerGroup groupToEdit = pgh.getGroupById(id);
		try {
			String[] selectedPlayers = request.getParameterValues("allPlayers");
			List<Player> selectedPlayersInList = new ArrayList<Player>();
			for (int i = 0; i < selectedPlayers.length; i++) {
				Player p = ph.getPlayerById(Integer.parseInt(selectedPlayers[i]));
				selectedPlayersInList.add(p);
			}
			groupToEdit.setPlayers(selectedPlayersInList);
		} catch (NullPointerException ex) {
			List<Player> selectedPlayersInList = new ArrayList<Player>();
			groupToEdit.setPlayers(selectedPlayersInList);
		}
		pgh.updateGroup(groupToEdit);
		getServletContext().getRequestDispatcher("/listGroupsServlet").forward(request, response);
	}

}
