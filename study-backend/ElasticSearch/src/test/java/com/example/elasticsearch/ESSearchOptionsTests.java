package com.example.elasticsearch;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
class ESSearchOptionsTests {
	
	private final RestHighLevelClient restHighLevelClient;
	
	@Autowired
	ESSearchOptionsTests(RestHighLevelClient restHighLevelClient) {
		this.restHighLevelClient = restHighLevelClient;
	}
	
	/**
	 * 查询所有
	 */
	@Test
	void testMatchAll() throws IOException {
		SearchRequest searchRequest = new SearchRequest("products"); // 指定搜索索引
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); // 指定条件对象
		sourceBuilder.query(QueryBuilders.matchAllQuery()); // 查询所有
		searchRequest.source(sourceBuilder); // 指定搜索条件
		// 参数 1： 搜索的请求对象 参数 2：请求配置对象 返回值：查询结果对象
		SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		
		System.out.println("总条数：" + search.getHits().getTotalHits().value);
		System.out.println("最大得分：" + search.getHits().getMaxScore());
		
		// 获取全部结果
		SearchHit[] searchHits = search.getHits().getHits();
		for (SearchHit searchHit : searchHits) {
			System.out.println("id：" + searchHit.getId() + "，source：" + searchHit.getSourceAsString());
		}
	}
	
	/**
	 * 不同条件查询 term（关键词查询）
	 */
	@Test
	void testQuery() throws IOException {
		// 1、term 关键词查询
		query(QueryBuilders.termQuery("price", 1.5));
		// 2、range 范围查询
		query(QueryBuilders.rangeQuery("price").gte(0).lte(2));
		// 3、prefix 前缀查询
		query(QueryBuilders.prefixQuery("description", "小"));
		// 4、wildcard 通配符查询
		query(QueryBuilders.wildcardQuery("title", "*浣熊"));
		// 5、ids 多个指定 id 查询
		query(QueryBuilders.idsQuery().addIds("1").addIds("2"));
		// 6、multi_match 多字段查询
		query(QueryBuilders.multiMatchQuery("好吃大", "description", "title"));
	}
	
	void query(QueryBuilder queryBuilder) throws IOException {
		SearchRequest searchRequest = new SearchRequest("products");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		
		sourceBuilder.query(queryBuilder); // 指定查询条件
		searchRequest.source(sourceBuilder);
		
		SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		
		System.out.println("符合条件数：" + search.getHits().getTotalHits().value);
		System.out.println("最大文档分数：" + search.getHits().getMaxScore());
		for (SearchHit hit : search.getHits().getHits()) {
			System.out.println("id：" + hit.getId() + " source：" + hit.getSourceAsString());
		}
	}
	
	/**
	 * 分页查询
	 * from 起始位置
	 * size 每页展示记录数 默认 10
	 * sort 排序 默认 asc（正序）
	 * fetchSource 返回指定字段
	 * highlight 高亮
	 */
	@Test
	void testPageSearch() throws IOException {
		SearchRequest searchRequest = new SearchRequest("products");
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		// 创建高亮
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		// 关闭字段匹配
		highlightBuilder.requireFieldMatch(false).field("description").field("title").preTags("<span stype=\"color=red\">").postTags("</span>");
		
		sourceBuilder.query(QueryBuilders.termQuery("description", "熊"))
				.from(0)
				.size(2)
				.sort("price", SortOrder.DESC)
				.fetchSource(new String[]{}, new String[]{"created_at"})// 参数 1：包含字段 参数 2：排除字段
				.highlighter(highlightBuilder); // 高亮搜索结果
		searchRequest.source(sourceBuilder);
		SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		System.out.println("符合条件数：" + search.getHits().getTotalHits().value);
		System.out.println("最大文档分数：" + search.getHits().getMaxScore());
		for (SearchHit hit : search.getHits().getHits()) {
			System.out.println("id：" + hit.getId() + " source：" + hit.getSourceAsString());
			Map<String, HighlightField> highlightFields = hit.getHighlightFields();
			System.out.println(highlightFields);
			if (highlightFields.containsKey("description")) {
				System.out.println("description 高亮结果：" + highlightFields.get("description").fragments()[0]);
			}
			if (highlightFields.containsKey("title")) {
				System.out.println("title 高亮结果：" + highlightFields.get("title").fragments()[0]);
			}
		}
	}
	
	/**
	 * query			：查询精确查询，查询计算文档得分，并根据文档得分进行返回
	 * filter query		：过滤查询，用来在大量数据中筛选出目的查询相关的数据 不会计算文档得分 速度快 经常使用的结果进行缓存
	 * 注意：一旦使用这两个，es 优先 执行 filter query 然后再执行 query
	 */
	@Test
	void testFilterQuery() throws IOException {
		SearchRequest searchRequest = new SearchRequest("products");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder
				.query(QueryBuilders.matchAllQuery())
				.postFilter(QueryBuilders.termQuery("description", "真"));
		searchRequest.source(searchSourceBuilder);
		SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		
		System.out.println("符合条件数：" + search.getHits().getTotalHits().value);
		System.out.println("最大文档分数：" + search.getHits().getMaxScore());
		for (SearchHit hit : search.getHits().getHits()) {
			System.out.println("id：" + hit.getId() + " source：" + hit.getSourceAsString());
		}
	}
}
