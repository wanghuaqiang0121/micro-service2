
package org.service.tools.feature;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月17日
 * @description: 判定方式：余弦相似度，通过计算两个向量的夹角余弦值来评估他们的相似度 余弦夹角原理：
 *               向量a=(x1,y1),向量b=(x2,y2) similarity=a.b/|a|*|b| a.b=x1x2+y1y2
 *               |a|=根号[(x1)^2+(y1)^2],|b|=根号[(x2)^2+(y2)^2]
 */
public class CosineSimilarity {

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年9月17日
	 * @param text1 字符串1
	 * @param text2 字符串2
	 * @return {@link Double}
	 * @description: 计算两字符串相似度
	 */
	public static double getSimilarity(String text1, String text2) {

		if (((text1 == null || "".equals(text1)) && (text2 == null || "".equals(text2))) || text1.equals(text2)) {
			/* 如果都为空，或者空字符串，或者两字符串相同，则代表完全相同  */
			return 1.0;
		} else if ((text1 == null || "".equals(text1)) || (text2 == null || "".equals(text2))) {
			/* 如果一个为空或者空字符串，另一个不为，那说明完全不相似 */
			return 0.0;
		} else {
			/* 都不为空则可以进行相似度比较 */
			List<Word> words1 = Tokenizer.segment(text1);
			List<Word> words2 = Tokenizer.segment(text2);
			return getSimilarity(words1, words2);
		}
	}

	/**
	 * 2、对于计算出的相似度保留小数点后六位
	 */
	public static double getSimilarity(List<Word> words1, List<Word> words2) {

		double score = getSimilarityImpl(words1, words2);

		// (int) (score * 1000000 + 0.5)其实代表保留小数点后六位
		// ,因为1034234.213强制转换不就是1034234。对于强制转换添加0.5就等于四舍五入
		score = (int) (score * 1000000 + 0.5) / (double) 1000000;

		return score;
	}

	/**
	 * 文本相似度计算 判定方式：余弦相似度，通过计算两个向量的夹角余弦值来评估他们的相似度 余弦夹角原理： 向量a=(x1,y1),向量b=(x2,y2)
	 * similarity=a.b/|a|*|b| a.b=x1x2+y1y2
	 * |a|=根号[(x1)^2+(y1)^2],|b|=根号[(x2)^2+(y2)^2]
	 */
	public static double getSimilarityImpl(List<Word> words1, List<Word> words2) {

		// 向每一个Word对象的属性都注入weight（权重）属性值
		taggingWeightByFrequency(words1, words2);

		// 第二步：计算词频
		// 通过上一步让每个Word对象都有权重值，那么在封装到map中（key是词，value是该词出现的次数（即权重））
		Map<String, Float> weightMap1 = getFastSearchMap(words1);
		Map<String, Float> weightMap2 = getFastSearchMap(words2);

		// 将所有词都装入set容器中
		Set<Word> words = new HashSet<>();
		words.addAll(words1);
		words.addAll(words2);

		AtomicFloat ab = new AtomicFloat();// a.b
		AtomicFloat aa = new AtomicFloat();// |a|的平方
		AtomicFloat bb = new AtomicFloat();// |b|的平方

		// 第三步：写出词频向量，后进行计算
		words.parallelStream().forEach(word -> {
			// 看同一词在a、b两个集合出现的此次
			Float x1 = weightMap1.get(word.getName());
			Float x2 = weightMap2.get(word.getName());
			if (x1 != null && x2 != null) {
				// x1x2
				float oneOfTheDimension = x1 * x2;
				// +
				ab.addAndGet(oneOfTheDimension);
			}
			if (x1 != null) {
				// (x1)^2
				float oneOfTheDimension = x1 * x1;
				// +
				aa.addAndGet(oneOfTheDimension);
			}
			if (x2 != null) {
				// (x2)^2
				float oneOfTheDimension = x2 * x2;
				// +
				bb.addAndGet(oneOfTheDimension);
			}
		});
		// |a| 对aa开方
		double aaa = Math.sqrt(aa.doubleValue());
		// |b| 对bb开方
		double bbb = Math.sqrt(bb.doubleValue());

		// 使用BigDecimal保证精确计算浮点数
		// double aabb = aaa * bbb;
		BigDecimal aabb = BigDecimal.valueOf(aaa).multiply(BigDecimal.valueOf(bbb));

		// similarity=a.b/|a|*|b|
		// divide参数说明：aabb被除数,9表示小数点后保留9位，最后一个表示用标准的四舍五入法
		double cos = BigDecimal.valueOf(ab.get()).divide(aabb, 9, BigDecimal.ROUND_HALF_UP).doubleValue();
		return cos;
	}

	/**
	 * 向每一个Word对象的属性都注入weight（权重）属性值
	 */
	protected static void taggingWeightByFrequency(List<Word> words1, List<Word> words2) {
		if (words1.get(0).getWeight() != null && words2.get(0).getWeight() != null) {
			return;
		}
		// 词频统计（key是词，value是该词在这段句子中出现的次数）
		Map<String, AtomicInteger> frequency1 = getFrequency(words1);
		Map<String, AtomicInteger> frequency2 = getFrequency(words2);

		// 标注权重（该词出现的次数）
		words1.parallelStream().forEach(word -> word.setWeight(frequency1.get(word.getName()).floatValue()));
		words2.parallelStream().forEach(word -> word.setWeight(frequency2.get(word.getName()).floatValue()));
	}

	/**
	 * 统计词频
	 * 
	 * @return 词频统计图
	 */
	private static Map<String, AtomicInteger> getFrequency(List<Word> words) {

		Map<String, AtomicInteger> freq = new HashMap<>();
		// 这步很帅哦
		words.forEach(i -> freq.computeIfAbsent(i.getName(), k -> new AtomicInteger()).incrementAndGet());
		return freq;
	}

	/**
	 * 构造权重快速搜索容器
	 */
	protected static Map<String, Float> getFastSearchMap(List<Word> words) {
		if (words == null || words.size() <= 0) {
			return Collections.emptyMap();
		}
		Map<String, Float> weightMap = new ConcurrentHashMap<>(words.size());

		words.parallelStream().forEach(i -> {
			if (i.getWeight() != null) {
				weightMap.put(i.getName(), i.getWeight());
			}
		});
		return weightMap;
	}

}