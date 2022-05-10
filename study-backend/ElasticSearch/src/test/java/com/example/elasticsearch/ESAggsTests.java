package com.example.elasticsearch;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedDoubleTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.ParsedMax;
import org.elasticsearch.search.aggregations.metrics.ParsedMin;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ESAggsTests {
	
	private final RestHighLevelClient restHighLevelClient;
	
	@Autowired
	ESAggsTests(RestHighLevelClient restHighLevelClient) {
		this.restHighLevelClient = restHighLevelClient;
	}
	
	/**
	 * 基于 terms 类型进行聚会 基于字段分组聚合
	 */
	@Test
	void testAggs() throws IOException {
		SearchRequest searchRequest = new SearchRequest("fruit");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder
				.query(QueryBuilders.matchAllQuery()) // 查询条件
				.aggregation(AggregationBuilders.terms("price_group").field("price"))  // 用来设置聚合处理
				.size(0);
		searchRequest.source(searchSourceBuilder);
		SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		// 处理聚合结果
		Aggregations aggregations = search.getAggregations();
		ParsedDoubleTerms price_group = aggregations.get("price_group");
		List<? extends Terms.Bucket> buckets = price_group.getBuckets();
		for (Terms.Bucket bucket : buckets) {
			System.out.println(bucket.getKey() + " " + bucket.getDocCount());
		}
	}
	
	/**
	 * max(ParsedMax) min(ParsedMin) sum(ParsedSum) avg(ParsedAvg) 聚合函数 桶中只有一个返回值
	 */
	@Test
	void testAggsFunction() throws IOException {
		SearchRequest searchRequest = new SearchRequest("fruit");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder
				.query(QueryBuilders.matchAllQuery()) // 查询条件
				// .aggregation(AggregationBuilders.sum("sum_price").field("price"))  // 用来设置聚合处理
				// .aggregation(AggregationBuilders.avg("avg_price").field("price"))  // 用来设置聚合处理
				// .aggregation(AggregationBuilders.max("max_price").field("price"))  // 用来设置聚合处理
				// .aggregation(AggregationBuilders.min("min_price").field("price"))  // 用来设置聚合处理
				.aggregation(AggregationBuilders.terms("title_group").field("title"))  // 用来设置聚合处理
				.size(0);
		searchRequest.source(searchSourceBuilder);
		SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
		Aggregations aggregations = search.getAggregations();
		
		// ParsedSum sum_price = aggregations.get("sum_price");
		// System.out.println(sum_price);
		
		// ParsedAvg avg_price = aggregations.get("avg_price");
		// System.out.println(avg_price);
		
		// ParsedMax max_price = aggregations.get("max_price");
		// System.out.println(max_price);
		
		// ParsedMin min_price = aggregations.get("min_price");
		// System.out.println(min_price);
		
		ParsedStringTerms parsedStringTerms = aggregations.get("title_group");
		for (Terms.Bucket bucket : parsedStringTerms.getBuckets()) {
			System.out.println(bucket.getKey() + " " + bucket.getDocCount());
		}
		
	}
}
