package com.example.elasticsearch;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ESBaseOptionsTests {
	
	private final RestHighLevelClient restHighLevelClient;
	
	@Autowired
	ESBaseOptionsTests(RestHighLevelClient restHighLevelClient) {
		this.restHighLevelClient = restHighLevelClient;
	}
	
	/**
	 * 创建索引 创建映射
	 */
	@Test
	void testIndexAndMapping() throws IOException {
		CreateIndexRequest createIndexRequest = new CreateIndexRequest("products"); // 索引名称
		createIndexRequest.mapping("{\n" +
				"    \"properties\": {\n" +
				"      \"title\": {\n" +
				"        \"type\": \"keyword\"\n" +
				"      },\n" +
				"      \"price\": {\n" +
				"        \"type\": \"double\"\n" +
				"      },\n" +
				"      \"create_at\": {\n" +
				"        \"type\": \"date\"\n" +
				"      },\n" +
				"      \"description\": {\n" +
				"        \"type\": \"text\",\n" +
				"        \"analyzer\": \"ik_max_word\"\n" +
				"      }\n" +
				"    }\n" +
				"  }", XContentType.JSON); // 指定映射，参数 1：指定映射 json 结构，参数 2：指定数据类型
		// 参数 1：创建索引的请求对象 参数 2：请求配置对象
		CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
		System.out.println("创建状态：" + createIndexResponse.isAcknowledged());
		restHighLevelClient.close(); // 关闭资源
	}
	
	/**
	 * 删除索引
	 */
	@Test
	void testDeleteIndex() throws IOException {
		// 参数 1：删除的索引对象 参数 2：请求配置对象
		AcknowledgedResponse products = restHighLevelClient.indices().delete(new DeleteIndexRequest("products"), RequestOptions.DEFAULT);
		System.out.println("删除状态：" + products.isAcknowledged());
		restHighLevelClient.close();
	}
	
	/**
	 * 索引一条文档
	 */
	@Test
	void testCreateDocument() throws IOException {
		IndexRequest indexRequest = new IndexRequest("products");
		indexRequest.id("1") // 手动指定文档 ID
				.source("{\n" +
				"  \"title\": \"小浣熊\",\n" +
				"  \"price\": 1.5,\n" +
				"  \"created_at\": \"2022-02-08\",\n" +
				"  \"description\": \"小浣熊真好吃！\"\n" +
				"}", XContentType.JSON); // 指定文档数据
		// 参数 1：索引的请求对象 参数 2：请求配置对象
		IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
		System.out.println("创建状态：" + index.status()); // 创建状态
	}
	
	/**
	 * 更新文档
	 */
	@Test
	void testUpdateDocument() throws IOException {
		// 参数 1：去哪个索引中更新 参数 2：更新文档 id
		UpdateRequest updateRequest = new UpdateRequest("products", "1");
		updateRequest.doc("{\n" +
				"    \"title\": \"小小浣熊\"\n" +
				"  }", XContentType.JSON);
		// 参数 1：更新请求对象 参数 2：请求配置对象
		UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
		System.out.println("更新结果：" + update.status());
	}
	
	/**
	 * 删除文档
	 */
	@Test
	void testDeleteDocument() throws IOException {
		// 参数 1：删除哪个文档中的数据 参数 2：删除的 id
		DeleteRequest deleteRequest = new DeleteRequest("products", "1");
		// 参数 1：删除请求对象 参数 2：请求配置对象
		DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
		System.out.println("删除状态：" + delete.status());
	}
	
	/**
	 * 基于 id 查询文档
	 */
	@Test
	void testQueryDocumentById() throws IOException {
		// 参数 1：查询哪个文档中的数据 参数 2：查询的 id
		GetRequest getRequest = new GetRequest("products", "1");
		// 参数 1：查询请求对象 参数 2：请求配置对象
		GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
		System.out.println("查询结果：" + documentFields.getSourceAsString());
	}
}
