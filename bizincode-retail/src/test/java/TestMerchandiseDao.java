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
import com.exolade.bizincode.retail.dao.MerchandiseDaoImpl;
import com.exolade.bizincode.retail.entity.Customer;
import com.exolade.bizincode.retail.entity.Merchandise;
import com.exolade.bizincode.retail.misc.MerchandiseType;


public class TestMerchandiseDao {
	private static EmfProvider provider;
	private static MerchandiseDaoImpl dao;
	private static ArrayList<Object> items;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		provider = new EmfProvider().getInstanceOf();
		provider.setTest(true);
		dao = new MerchandiseDaoImpl(provider);
		items = new ArrayList<>();
		
		Merchandise apple = new Merchandise("Apple", 2.50, MerchandiseType.FOOD, 100);
		Merchandise banana = new Merchandise("Banana", 0.99, MerchandiseType.FOOD, 50);
		Merchandise orange = new Merchandise("Orange", 0.99, MerchandiseType.FOOD, 100);
		Merchandise mug = new Merchandise("Mug", 1.99, MerchandiseType.OTHER, 50);
		Merchandise pen = new Merchandise("Pen", 0.50, MerchandiseType.OTHER, 30);
		
		items.add(apple);
		items.add(banana);
		items.add(orange);
		items.add(mug);
		items.add(pen);
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
	public void testGetByType() {
		List<Object> retrieved = dao.getByType(MerchandiseType.FOOD);
		List<Merchandise> original = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			Merchandise tmp = (Merchandise) items.get(i);
			if(tmp.getType() == MerchandiseType.FOOD) {
				original.add((Merchandise)items.get(i));
			}
		}
		
		boolean getAllFoods = false;
		for (int i = 0; i < original.size(); i++) {
			Merchandise origin = original.get(i);
			getAllFoods = false;
			for (int j = 0; j < retrieved.size(); j++) {
				Merchandise result = (Merchandise) retrieved.get(j);
				if (origin.getId() == result.getId()) {
					getAllFoods = true;
					break;
				}
				
			}
		}
		assertEquals(true, getAllFoods);
	}
}
