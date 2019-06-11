package org.service.tools.feature;

import java.util.List;
import java.util.stream.Collectors;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月14日
 * @description: 定制化分词工具
 */
public class Tokenizer {

	/**
	 * @author: LiuGangQiang
	 * @date: 2018年9月14日
	 * @param sentence
	 * @return {@link List}
	 * @description: 分词
	 */
	public static List<Word> segment(String sentence) {
		/* 通过此工厂方法得到的是当前版本速度和效果最平衡的分词器,将来HanLP升级后，用户无需修改调用代码。 */
		Segment segment = HanLP.newSegment();
		/* 设置索引模式 */
		segment.enableIndexMode(false);
		/* 启用用户词典 */
		segment.enableCustomDictionary(true);
		// TODO 这里应该注入系统关键词
		List<Term> terms = segment.seg(sentence);
		return terms.stream().map(term -> new Word(term.word, term.nature.toString())).collect(Collectors.toList());
	}

}