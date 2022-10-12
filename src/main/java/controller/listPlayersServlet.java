package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.PlayerHelper;
import model.Player;

/**
 * Servlet implementation class listPlayersServlet
 */
@WebServlet("/listPlayersServlet")
public class listPlayersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PlayerHelper ph = new PlayerHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listPlayersServlet() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Player> players = ph.listAllPlayers();
        request.setAttribute("players", players);
        this.getServletContext().getRequestDispatcher("playerList.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
