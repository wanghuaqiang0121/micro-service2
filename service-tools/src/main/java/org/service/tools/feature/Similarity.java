package org.service.tools.feature;

/**
 * 计算两个字符串的相识度
 */
public class Similarity {

	public static final String content1 = "怎么开车";

	public static final String content2 = "怎么开车";

	public static final String content3 = "开车如何";

	public static void main(String[] args) {

		System.out.println("相似度：" + CosineSimilarity.getSimilarity(content1, content2));
		System.out.println("相似度：" + CosineSimilarity.getSimilarity(content1, content3));
		System.out.println("相似度：" + CosineSimilarity.getSimilarity(content2, content3));
	}
}