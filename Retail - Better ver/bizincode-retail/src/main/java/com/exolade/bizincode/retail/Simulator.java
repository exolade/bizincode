package com.exolade.bizincode.retail;

import java.util.ArrayList;

import com.exolade.bizincode.retail.dao.DaoImpl;
import com.exolade.bizincode.retail.entity.Customer;
import com.exolade.bizincode.retail.entity.Employee;
import com.exolade.bizincode.retail.entity.EmployeeDetail;
import com.exolade.bizincode.retail.entity.Merchandise;
import com.exolade.bizincode.retail.entity.PurchaseProfile;
import com.exolade.bizincode.retail.misc.EmployeeType;
import com.exolade.bizincode.retail.misc.EmployeePosition;
import com.exolade.bizincode.retail.misc.MerchandiseType;

public class Simulator {

	public static void main(String[] args) {
		ArrayList<Object> items = new ArrayList<>();
		Employee mnger = new Employee(30, 1, EmployeeType.FULL_TIME, null, EmployeePosition.MANAGER);
		Employee em1 = new Employee(18, 1, EmployeeType.PART_TIME, mnger, EmployeePosition.STAFF);
		EmployeeDetail mngerD = new EmployeeDetail(35, 123456789, "John Doe", "234 Apple St.", 'm');
		Merchandise item1 = new Merchandise("Banana", 1.0, MerchandiseType.FOOD, 10);
		Customer cus1 = new Customer(null, 30, 12345698, "Jane", "234 Apple St.", 'f');
		PurchaseProfile pf1 = new PurchaseProfile();
		
		cus1.setProfile(pf1);
		pf1.setCustomer(cus1);
		
		mnger.setDetail(mngerD);
		mngerD.setEmployee(mnger);
		
		items.add(mnger);
		items.add(em1);
		items.add(item1);
		items.add(pf1);
		
		EmfProvider provider = new EmfProvider().getInstanceOf();
		DaoImpl dao = new DaoImpl(provider);
		boolean all_saved = dao.save(items);
		if (all_saved) {
			dao.closeDao();
			provider.closeEmf();
			System.out.println("ALL SYSTEM STOP");
		}
		//dao.closeDao();
		//System.out.println("Is EntityManagerFactory ALIVE? " + provider.getEntityManagerFactory().isOpen());
		//System.out.println("END");
		
		
		
	}

}
