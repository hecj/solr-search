package test.hecj.search.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SolrJTest {

	private static final String DEFAULT_URL = "http://localhost:8080/solr";
	private HttpSolrServer server = null;

	@Before
	public void init() {

		server = new HttpSolrServer(DEFAULT_URL);
//		server.setMaxRetries(1);
//		server.setConnectionTimeout(5000);
//		server.setSoTimeout(1000); // socket read timeout
//		server.setDefaultMaxConnectionsPerHost(100);
//		server.setMaxTotalConnections(100);
//		server.setFollowRedirects(false);
//		server.setAllowCompression(true);
	}

	@After
	public void destory() {
		server = null;
		System.runFinalization();
		System.gc();
	}
	
	@Test
    public void addDoc() {
        // 创建doc文档
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", 1222222222);
//        doc.addField("webTitle", "测试标题");
//        doc.addField("webTime", new java.util.Date());
//        doc.addField("webContent", "这是一条测试内容");
        try {
            // 添加一个doc文档
            UpdateResponse response = server.add(doc);
            // commit后才保存到索引库
            server.commit();
            // 输出统计信息
            System.out.println("Query Time：" + response.getQTime());
            System.out.println("Elapsed Time：" + response.getElapsedTime());
            System.out.println("Status：" + response.getStatus());
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------");
 
    }
	
	
}
