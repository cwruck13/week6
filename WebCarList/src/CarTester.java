import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.ListDetailsHelper;
import controller.OwnerHelper;
import model.CarItem;
import model.ListDetails;
import model.Owner;

public class CarTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Owner bill = new Owner("Bill");
		
		Owner mary = new Owner("Mary");
		
		//OwnerHelper oh = new OwnerHelper();
		
		//oh.insertOwner(bill);
		
		ListDetailsHelper ldh = new ListDetailsHelper();
		//ListDetails billList = new ListDetails("Bill's List", LocalDate.now(), bill);
		
		CarItem car1 = new CarItem("Chevrolet", "2020", "Silverado");
		CarItem car2 = new CarItem("Chevrolet", "1969", "El Camino");
		
		List<CarItem> marysCars = new ArrayList<CarItem>();
		marysCars.add(car1);
		marysCars.add(car2);
		
		//ldh.insertNewListDetails(billList);
		
		ListDetails marysList = new ListDetails("Mary's Car List", LocalDate.now(), mary);
		
		marysList.setListOfCars(marysCars);
		
		ldh.insertNewListDetails(marysList);
		
		List<ListDetails> allLists = ldh.getList();
		
		for (ListDetails a : allLists) {
			System.out.println(a.toString());
		}
		
		//List<Owner> allOwners = oh.showAllOwners();
		//for(Owner a: allOwners) {
		//	System.out.println(a.toString());
		//}

	}

}
