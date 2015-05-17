import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.exolade.bizincode.retail.EmfProvider;
import com.exolade.bizincode.retail.dao.CustomerDaoImpl;
import com.exolade.bizincode.retail.dao.EmployeeDaoImpl;
import com.exolade.bizincode.retail.entity.Customer;
import com.exolade.bizincode.retail.entity.Employee;
import com.exolade.bizincode.retail.entity.PurchaseHistory;


public class TestCustomerDao {
	private static EmfProvider provider;
	private static CustomerDaoImpl dao;
	private static ArrayList<Object> items;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		provider = new EmfProvider().getInstanceOf();
		provider.setTest(true);
		dao = new CustomerDaoImpl(provider);
		items = new ArrayList<>();
		
		Customer cust1 = new Customer(25, 142456789, "Johanna", "543 Peach St.", 'f');
		Customer cust2 = new Customer(42, 132486789, "Lora", "443 Peach St.", 'f');
		Customer cust3 = new Customer(48, 132456709, "George", "343 Peach St.", 'm');
		PurchaseHistory pf1 = new PurchaseHistory();
		PurchaseHistory pf2 = new PurchaseHistory();
		PurchaseHistory pf3 = new PurchaseHistory();
		
		cust1.setProfile(pf1);
		pf1.setCustomer(cust1);
		
		cust2.setProfile(pf2);
		pf2.setCustomer(cust2);
		
		cust3.setProfile(pf3);
		pf3.setCustomer(cust3);
		
		items.add(cust1);
		items.add(cust2);
		items.add(cust3);
		items.add(pf1);
		items.add(pf2);
		items.add(pf3);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao.rollback();
		items = null;
		dao.closeDao();
		provider.closeEmf();
	}

	@Before
	public void setUp() throws Exception {
		dao.save(items);
	}

	@After
	public void tearDown() throws Exception {
		dao.rollback();
	}

	@Test
	public void testGetByAge() {
		List<Object> retrieved = dao.getByAge(42);
		Customer result = (Customer) retrieved.get(0);
		assertEquals(((Customer) items.get(1)).getId(), result.getId());
	}
	
	@Test
	public void testGetByName() {
		List<Object> retrieved = dao.getByName("Johanna");
		Customer result = (Customer) retrieved.get(0);
		assertEquals(((Customer) items.get(0)).getId(), result.getId());
	}

	@Test
	public void testGetByAddress() {
		List<Object> retrieved = dao.getByName("George");
		Customer result = (Customer) retrieved.get(0);
		assertEquals(((Customer) items.get(2)).getId(), result.getId());
	}
	
	@Test
	public void testGetByGender() {
		List<Object> retrieved = dao.getByGender('f');
		List<Customer> original = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) instanceof Customer) {
				Customer tmp = (Customer) items.get(i);
				if(tmp.getGender() == 'f') {
					original.add((Customer)items.get(i));
				}
			}
		}
		
		boolean getAllFemales = false;
		for (int i = 0; i < original.size(); i++) {
			Customer origin = original.get(i);
			getAllFemales = false;
			for (int j = 0; j < retrieved.size(); j++) {
				Customer result = (Customer) retrieved.get(j);
				if (origin.getId() == result.getId()) {
					getAllFemales = true;
					break;
				}
				
			}
		}
		assertEquals(true, getAllFemales);
	}
	
} //class
