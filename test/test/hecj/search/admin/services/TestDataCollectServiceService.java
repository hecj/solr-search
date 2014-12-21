package test.hecj.search.admin.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.hecj.search.admin.entity.DataCollectParams;
import com.hecj.search.admin.entity.DataField;
import com.hecj.search.admin.services.DataCollectService;
import com.hecj.search.util.EasyGridData;
import com.hecj.search.util.Pagination;
import com.hecj.search.util.Result;

public class TestDataCollectServiceService {
	
	private DataCollectService dataCollectService ;
	private SessionFactory sessionFactory ;
	
	@Before
	public void init(){
		System.out.println(sessionFactory);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:test/bean/applicationContext.xml");
		dataCollectService = (DataCollectService) ctx.getBean("dataCollectService");
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		
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
		
		DataCollectParams data = dataCollectService.searchDataCollectParams("14189167070324304295");
		System.out.println(data.getDataFields().size());
		for(DataField d:data.getDataFields()){
			System.out.println(d.getId());
		}
		System.out.println(data.getId());
	}
}
