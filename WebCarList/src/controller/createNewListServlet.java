package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CarItem;
import model.ListDetails;
import model.Owner;

/**
 * Servlet implementation class createNewListServlet
 */
@WebServlet("/createNewListServlet")
public class createNewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createNewListServlet() {
        super();
        // TODO Auto-generated constructor stub   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		CarItemHelper cih = new CarItemHelper();

		String listName = request.getParameter("listName");
		System.out.println("List Name: " + listName);
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		String ownerName = request.getParameter("ownerName");
		LocalDate ld;
		
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch(NumberFormatException ex) {
			ld = LocalDate.now();
		}
		
		String[] selectedCars = request.getParameterValues("allCarsToAdd");
		
		List<CarItem> selectedCarsInList = new ArrayList<CarItem>();
		
		if (selectedCars != null && selectedCars.length > 0) {
			for(int i = 0; i<selectedCars.length; i++) {
				System.out.println(selectedCars[i]);
				CarItem c = cih.searchForItemById(Integer.parseInt(selectedCars[i]));
				selectedCarsInList.add(c);
			}
		}
		
		Owner owner = new Owner(ownerName);
		ListDetails old = new ListDetails(listName, ld, owner);
		old.setListOfCars(selectedCarsInList);
		ListDetailsHelper olh = new ListDetailsHelper();
		olh.insertNewListDetails(old);
		
		System.out.println("Success!");
		System.out.println(old.toString());
		
	getServletContext().getRequestDispatcher("/viewAllListsServlet").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
