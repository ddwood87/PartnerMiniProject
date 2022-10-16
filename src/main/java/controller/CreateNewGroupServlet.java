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
 * Servlet implementation class CreateNewGroupServlet
 */
@WebServlet("/createNewGroupServlet")
public class CreateNewGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PlayerHelper ph = new PlayerHelper();
		PlayerGroupHelper pgh = new PlayerGroupHelper();
		
		String[] selectedPlayers = request.getParameterValues("allPlayersToAdd");
		List<Player> selectedPlayersInList = new ArrayList<Player>();
		// make sure something was selected – otherwise we get a null pointer exception
		if (selectedPlayers != null && selectedPlayers.length > 0) {
			for (int i = 0; i < selectedPlayers.length; i++) {
				Player p = ph.getPlayerById(Integer.parseInt(selectedPlayers[i]));
				selectedPlayersInList.add(p);
			}
		}
		System.out.println(selectedPlayersInList);
		PlayerGroup group = new PlayerGroup(selectedPlayersInList);
//		group.setPlayers(selectedPlayersInList);
		pgh.addGroup(group);
		getServletContext().getRequestDispatcher("/listGroupsServlet").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
