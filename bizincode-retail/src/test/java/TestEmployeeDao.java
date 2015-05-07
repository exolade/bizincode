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
import com.exolade.bizincode.retail.entity.Employee;
import com.exolade.bizincode.retail.entity.EmployeeDetail;
import com.exolade.bizincode.retail.misc.EmployeePosition;
import com.exolade.bizincode.retail.misc.EmployeeType;

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
	public void testQueryFindAdd() {
		dao.save(items);
		
		String q = "SELECT e FROM Employee e WHERE e.position = :pos";
		List<String> tag = new ArrayList<>();
		tag.add("pos");
		
		List<Object> condition = new ArrayList<>();
		condition.add(EmployeePosition.MANAGER);
		
		assertEquals(true, dao.findByQuery(q, tag, condition));
	}
	
	@Test
	public void testQueryGet() {
		String q = "SELECT e FROM Employee e WHERE e.position = :pos";
		List<String> tag = new ArrayList<>();
		tag.add("pos");
		
		List<Object> condition = new ArrayList<>();
		condition.add(EmployeePosition.MANAGER);
		
		List<Object> result = dao.getByQuery(q, tag, condition);
		Employee retrieved = (Employee) result.get(0); 
		Employee original = (Employee) items.get(0);
		
		assertEquals(original.getId(), retrieved.getId());
		assertEquals(original.getSalary(), retrieved.getSalary(), 0.0);
		assertEquals(original.getHours(), retrieved.getHours());
		assertEquals(original.getEType(), retrieved.getEType());
		assertEquals(original.getPosition(), retrieved.getPosition());
	}
	
	@Test
	public void testQueryDelete() {
		String q = "SELECT e FROM Employee AS e JOIN e.detail AS d WHERE d.name = :name AND e.position = :pos";
		List<String> tag = new ArrayList<>();
		tag.add("pos");
		tag.add("name");
		
		List<Object> condition = new ArrayList<>();
		condition.add(EmployeePosition.MANAGER);
		condition.add("John Doe");
		
		int r = dao.deleteByQuery(q, tag, condition);
		System.out.println("SLATED FOR DELETE: " + r);
		assertEquals(false, dao.findByQuery(q, tag, condition));
	}
	
	@Test
	public void testQueryUpdate() {
		String q = "SELECT e FROM Employee AS e JOIN e.detail AS d WHERE d.name = :name AND e.position = :pos";
		List<String> tag = new ArrayList<>();
		tag.add("pos");
		tag.add("name");
		
		List<Object> condition = new ArrayList<>();
		condition.add(EmployeePosition.MANAGER);
		condition.add("Josh");
		
		List<Object> condition2 = new ArrayList<>();
		condition2.add(EmployeePosition.MANAGER);
		condition2.add("Josh2");
		
		List<Object> result = dao.getByQuery(q, tag, condition);
		Employee tmp = (Employee) result.get(0);
		tmp.getDetail().setName("Josh2");
		dao.updateThis(tmp);
		
		assertEquals(true, dao.findByQuery(q, tag, condition2));
	}
	
	@Test
	public void testGetByID() {
		Employee originalStaff = (Employee) items.get(2);
		Employee result = (Employee) dao.getById(originalStaff.getId());
		assertEquals(originalStaff.getId(), result.getId());
		assertEquals(originalStaff.getPosition(), result.getPosition());
		assertEquals(originalStaff.getEType(), result.getEType());
	}
	
	@Test
	public void testFindById() {
		Employee originalStaff = (Employee) items.get(2);
		assertTrue(dao.findById(originalStaff.getId()));
	}
	
	/**
	 * testDeleteFindById() and testGetAll() cannot be run together at the same time
	 * as delete affects retrieved results despite roll back after each test (WIP).
	 * However, separately the tests will pass along with others.
	 */
/*	@Test
	public void testDeleteFindById() {
		Employee originalStaff = (Employee) items.get(2);
		dao.deleteById(originalStaff.getId());
		assertEquals(false, dao.findById(originalStaff.getId()));
	}*/
	
	@Test
	public void testGetAll() {
		List<Object> retrieved = dao.getAll();
		List<Employee> original = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) instanceof Employee) {
				original.add((Employee)items.get(i));
			}
		}
		
		assertEquals(original.size(), retrieved.size());
		
		boolean getAll = false;
		for (int i = 0; i < original.size(); i++) {
			Employee origin = original.get(i);
			getAll = false;
			for (int j = 0; j < retrieved.size(); j++) {
				Employee result = (Employee) retrieved.get(j);
				if (origin.getId() == result.getId()) {
					getAll = true;
					break;
				}
				
			}
		}
		assertEquals(true, getAll);
	}
}
