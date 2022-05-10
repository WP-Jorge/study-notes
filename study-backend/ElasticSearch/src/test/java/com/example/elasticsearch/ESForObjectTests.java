package com.example.elasticsearch;

import com.example.elasticsearch.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ESForObjectTests {
	
	private final RestHighLevelClient restHighLevelClient;
	
	@Autowired
	ESForObjectTests(RestHighLevelClient restHighLevelClient) {
		this.restHighLevelClient = restHighLevelClient;
	}
	
	/**
	 * 将对象放入 es 中
	 */
	@Test
	void testIndex() throws IOException {
		Product product = new Product();
		product.setId(1);
		product.setTitle("小浣熊干吃面");
		product.setPrice(1.5);
		product.setDescription("小浣熊真好吃！");
		
		// 录入 es
		IndexRequest indexRequest = new IndexRequest("products");
		indexRequest.id(product.getId().toString())
						.source((new ObjectMapper().writeValueAsString(product)), XContentType.JSON);
		IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
		System.out.println(index.status());
	}
	
	/**
	 * 反序列化 json
	 */
	@Test
	void testToJSON() throws IOException {
		SearchRequest searchRequest = new SearchRequest("products");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder
				.query(QueryBuilders.matchAllQuery())
				.from(0)
				.size(20);
		searchRequest.source(searchSourceBuilder);
		SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		
		System.out.println("符合条件数：" + search.getHits().getTotalHits().value);
		System.out.println("最大文档分数：" + search.getHits().getMaxScore());
		
		List<Product> productList = new ArrayList<>();
		for (SearchHit hit : search.getHits().getHits()) {
			System.out.println("id：" + hit.getId() + " source：" + hit.getSourceAsString());
			Product product = new ObjectMapper().readValue(hit.getSourceAsString(), Product.class);
			productList.add(product);
		}
		for (Product product : productList) {
			System.out.println(product.toString());
		}
	}
}
