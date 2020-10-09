package controller;

import java.io.IOException;
import java.lang.reflect.Array;
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
 * Servlet implementation class EditListDetailsServlet
 */
@WebServlet("/editListDetailsServlet")
public class EditListDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditListDetailsServlet() {
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
		ListDetailsHelper dao = new ListDetailsHelper();
		CarItemHelper cih = new CarItemHelper();
		OwnerHelper oh = new OwnerHelper();
		
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		ListDetails listToUpdate = dao.searchForListDetailsById(tempId);
		
		String newListName = request.getParameter("listName");
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		
		String ownerName = request.getParameter("ownerName");
		//find the add the new shopper
		Owner newOwner = oh.findOwner(ownerName);
		
		LocalDate ld;
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch (NumberFormatException ex) {
			ld = LocalDate.now();
		}
		 
		try {
			//cars are selected in list to add
			String[] selectedCars = request.getParameterValues("allCarsToAdd");
			List<CarItem> selectedCarsInList = new ArrayList<CarItem>();
			
			for (int i=0; i <selectedCars.length; i++) {
				System.out.println(selectedCars[i]);
				CarItem c = cih.searchForItemById(Integer.parseInt(selectedCars[i]));
				selectedCarsInList.add(c);
			} 
			listToUpdate.setListOfCars(selectedCarsInList);
		} catch (NullPointerException ex) {
			//no cars selected in list - set to an empty list
			List<CarItem> selectedCarsInList = new ArrayList<CarItem>();
			listToUpdate.setListOfCars(selectedCarsInList);
		}
		
		listToUpdate.setListName(newListName);
		listToUpdate.setPurchaseDate(ld);
		listToUpdate.setOwner(newOwner);
		
		dao.updateList(listToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllListsServlet").forward(request, response);
		//doGet(request, response);
	}

}
