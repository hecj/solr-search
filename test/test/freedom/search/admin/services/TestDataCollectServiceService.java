package test.freedom.search.admin.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.freedom.search.admin.entity.DataCollectParams;
import com.freedom.search.admin.services.DataCollectService;
import com.freedom.search.admin.services.MenuTreeService;
import com.freedom.search.admin.vo.MenuTree;
import com.freedom.search.util.EasyGridData;
import com.freedom.search.util.Pagination;
import com.freedom.search.util.Result;

public class TestDataCollectServiceService {
	
	private DataCollectService dataCollectService ;
	private SessionFactory sessionFactory ;
	private MenuTreeService menuTreeService ;
	
	@Before
	public void init(){
		System.out.println(sessionFactory);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:test/bean/applicationContext.xml");
		dataCollectService = (DataCollectService) ctx.getBean("dataCollectService");
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		menuTreeService = (MenuTreeService) ctx.getBean("menuTreeService");
		
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
		List<DataCollectParams> list = (List<DataCollectParams>) result.getData();
		for(DataCollectParams d:list){
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
		
		DataCollectParams data = dataCollectService.searchDataCollectParams("14189731644485622357");
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
		
	}
	
	
}
