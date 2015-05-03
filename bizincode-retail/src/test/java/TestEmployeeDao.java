/**
 * This test tests EmployeeDao.java and all its CRUID operations. One-to-one/One-to-many
 * cascade tested.
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.exolade.bizincode.retail.EmfProvider;
import com.exolade.bizincode.retail.dao.EmployeeDaoImpl;
import com.exolade.bizincode.retail.entity.Customer;
import com.exolade.bizincode.retail.entity.Employee;
import com.exolade.bizincode.retail.entity.EmployeeDetail;
import com.exolade.bizincode.retail.entity.Merchandise;
import com.exolade.bizincode.retail.entity.PurchaseProfile;
import com.exolade.bizincode.retail.misc.EmployeePosition;
import com.exolade.bizincode.retail.misc.EmployeeType;
import com.exolade.bizincode.retail.misc.MerchandiseType;

public class TestEmployeeDao {
	private static EmfProvider provider;
	private static EmployeeDaoImpl dao;
	private static ArrayList<Object> items;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		provider = new EmfProvider().getInstanceOf();
		provider.setTest(true);
		dao = new EmployeeDaoImpl(provider);
		items = new ArrayList<>();
		
		Employee mnger = new Employee(30, 1, EmployeeType.FULL_TIME, null, EmployeePosition.MANAGER);
		Employee mnger2 = new Employee(35, 1, EmployeeType.FULL_TIME, null, EmployeePosition.MANAGER);
		Employee staff1 = new Employee(18, 1, EmployeeType.PART_TIME, mnger, EmployeePosition.STAFF);
		EmployeeDetail mngerDetail = new EmployeeDetail(35, 123456789, "John Doe", "234 Apple St.", 'm');
		EmployeeDetail mngerDetail2 = new EmployeeDetail(40, 123456789, "Josh", "123 Apple St", 'm');
		EmployeeDetail staffDetail = new EmployeeDetail(25, 123456789, "Joseph", "123 Apple St.", 'm');
		
		mnger.setDetail(mngerDetail);
		mngerDetail.setEmployee(mnger);
		mnger.getSubordinates().add(staff1);
		
		mnger2.setDetail(mngerDetail2);
		mngerDetail2.setEmployee(mnger2);
		
		staff1.setDetail(staffDetail);
		staffDetail.setEmployee(staff1);
		staff1.setManager(mnger);
		
		items.add(mnger);
		items.add(mnger2);
		items.add(staff1);
		items.add(mngerDetail);
		items.add(staffDetail);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
	public void testFindAdd() {
		dao.save(items);
		
		String q = "SELECT e FROM Employee e WHERE e.position = :pos";
		List<String> tag = new ArrayList<>();
		tag.add("pos");
		
		List<Object> condition = new ArrayList<>();
		condition.add(EmployeePosition.MANAGER);
		
		assertEquals(true, dao.find(q, tag, condition));
	}
	
	@Test
	public void testGet() {
		String q = "SELECT e FROM Employee e WHERE e.position = :pos";
		List<String> tag = new ArrayList<>();
		tag.add("pos");
		
		List<Object> condition = new ArrayList<>();
		condition.add(EmployeePosition.MANAGER);
		
		List<Object> result = dao.get(q, tag, condition);
		Employee retrieved = (Employee) result.get(0); 
		Employee original = (Employee) items.get(0);
		
		assertEquals(original.getId(), retrieved.getId());
		assertEquals(original.getSalary(), retrieved.getSalary(), 0.0);
		assertEquals(original.getHours(), retrieved.getHours());
		assertEquals(original.getEType(), retrieved.getEType());
		assertEquals(original.getPosition(), retrieved.getPosition());
		assertEquals(original.getStartDate(), retrieved.getStartDate());
	}
	
	@Test
	public void testDelete() {
		String q = "SELECT e FROM Employee AS e INNER JOIN e.detail AS d WHERE d.name = :name AND e.position = :pos";
		List<String> tag = new ArrayList<>();
		tag.add("pos");
		tag.add("name");
		
		List<Object> condition = new ArrayList<>();
		condition.add(EmployeePosition.MANAGER);
		condition.add("John Doe");
		
		int r = dao.deleteSelected(q, tag, condition);
		System.out.println("SLATED FOR DELETE: " + r);
		assertEquals(false, dao.find(q, tag, condition));
	}
	
	@Test
	public void testUpdate() {
		String q = "UPDATE EmployeeDetail AS d SET d.name = :name WHERE d.id = (SELECT e.id FROM Employee AS e WHERE d.age = :age AND e.salary = :salary)";
		List<String> tag = new ArrayList<>();
		tag.add("age");
		tag.add("name");
		tag.add("salary");
		
		List<Object> condition = new ArrayList<>();
		condition.add(40);
		condition.add("Josh2");
		condition.add(35.0);
		
		List<Object> result = dao.get(q, tag, condition);
		Employee retrieved = (Employee) result.get(0);
		
		/*retrieved.getDetail().setName("Josh2");
		
		String q2 = "SELECT d.name FROM EmployeeDetail AS d INNER JOIN d.employee AS e WHERE d.name = :name AND e.position = :pos";
		List<String> tag2 = new ArrayList<>();
		tag2.add("pos");
		tag2.add("name");
		
		List<Object> condition2 = new ArrayList<>();
		condition2.add(EmployeePosition.MANAGER);
		condition2.add("Josh2");*/
		
		//retrieved = (Employee) dao.get(q, tag, condition2);
		assertEquals(true, dao.find(q, tag, condition));
		
	}
}
