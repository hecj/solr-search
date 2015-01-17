package test.freedom.search.admin.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.freedom.search.admin.entity.LzDataCollectParams;
import com.freedom.search.admin.entity.LzRole;
import com.freedom.search.admin.entity.LzUser;
import com.freedom.search.admin.services.DataCollectService;
import com.freedom.search.admin.services.ModuleService;
import com.freedom.search.admin.services.RoleService;
import com.freedom.search.admin.services.UserService;
import com.freedom.search.util.EasyGridData;
import com.freedom.search.util.Pagination;
import com.freedom.search.util.Result;

public class TestDataCollectServiceService {
	
	private DataCollectService dataCollectService ;
	private SessionFactory sessionFactory ;
	private ModuleService moduleService ;
	private UserService userService ;
	private RoleService roleService ;
	
	@Before
	public void init(){
		System.out.println(sessionFactory);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:test/bean/applicationContext.xml");
		dataCollectService = (DataCollectService) ctx.getBean("dataCollectService");
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		moduleService = (ModuleService) ctx.getBean("moduleService");
		userService = (UserService) ctx.getBean("userService");
		roleService = (RoleService) ctx.getBean("roleService");
		
		System.out.println(sessionFactory);
	}
	
	@Test
	public void testSearchArticleList(){
		
		Pagination mPagination = new Pagination();
		mPagination.setPageSize(2);
		Map mMap = new HashMap();
		mMap.put("pagination", mPagination);
		
		Result result = dataCollectService.searchDataCollectByPagination(mMap);
		System.out.println(result.isSuccess());
		System.out.println(result.getPagination().getCountSize());
		List<LzDataCollectParams> list = (List<LzDataCollectParams>) result.getData();
		for(LzDataCollectParams d:list){
			System.out.println(d.getDataFields().size());
		}
		
//		System.out.println(ObjectToJson.object2json(list));
		
//		EasyUIData data = new EasyUIData();
//		data.setRows(list);
//		data.setTotal(list.size()+0l);
//		
//		System.out.println(data.toJSON());
		System.out.println(new EasyGridData().toJSON());
//		
//		System.out.println(ObjectToJson.object2json(data));
//		String json = JSONObject.fromObject(list).toString();
//		JSONArray json1 =	JSONArray.fromObject(list);
//		System.out.println("json:"+json1);
//		String json2 = JSONUtils.valueToString(list);
//		System.out.println("json:"+json2);
//		String json = JSONSerializer.toJSON(list).toString();
//		System.out.println("json:"+json);
	}
	@Test
	public void test02(){
		
		LzDataCollectParams data = dataCollectService.searchDataCollectParams("14189731644485622357");
//		System.out.println(data.getDataFields().size());
//		for(DataField d:data.getDataFields()){
//			System.out.println(d.getId());
//		}
//		System.out.println(data.getId());
	}
	
	@Test
	public void menu01(){
		
		//MenuTree tree = menuTreeService.searchMenuTree(10,"http://localhost:8080/search");
//		System.out.println(tree.getVoTrees().size());
		
		
//		String s = ObjectToJson.object2json(voTree);
//		System.out.println(s);
		
		//System.out.println(tree.toJSON());
		
	}
	
	@Test
	public void test03(){
		
		LzUser u = userService.searchUserByCode("admin");
//		System.out.println(u.getUserRole().getRole());
		LzRole role = roleService.searchRole("P11112");
		
	}
	
	@Test
	public void test04(){
		System.out.println("==================");
		LzUser u = userService.searchUserByCode("z10");
		System.out.println(u.getRole().getRolename());
		
//		LzRole r = new LzRole();
//		r.setrolecode("P111111");
		
		LzRole r = roleService.searchRole("p");
		u.setRole(r);
//		
		userService.editUser(u);
		
//		userService.deleteUser(u.getUsercode());
	}
	
	@Test
	public void test05(){
		System.out.println("==================");
		//LzUser u = userService.searchUserByCode("admin");
		
		LzUser u = new LzUser();
		u.setUsercode("z16");
		
		LzRole r = roleService.searchRole("p");
		u.setRole(r);
		
		userService.addUser(u);
	}
	
}
