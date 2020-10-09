package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CarItem;

/**
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/navigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		CarItemHelper dao = new CarItemHelper();
		String act = request.getParameter("doThisToItem");

		// String path = "/viewAllCarsServlet";

		if (act == null) {
			// no button has been selected
			getServletContext().getRequestDispatcher("/viewAllCarsServlet").forward(request, response);

		} else if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				CarItem carToDelete = dao.searchForItemById(tempId);
				dao.deleteItem(carToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a car");
			} finally {
				getServletContext().getRequestDispatcher("/viewAllCarsServlet").forward(request, response);
			}
		} else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				CarItem carToEdit = dao.searchForItemById(tempId);
				request.setAttribute("carToEdit", carToEdit);
				getServletContext().getRequestDispatcher("/edit-car.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a car");
			}
		} else if (act.equals("add")) {
			getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}

	}

}
