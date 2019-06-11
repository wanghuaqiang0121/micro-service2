package org.service.tools.feature;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年9月14日
 * @description: 存储分词信息
 */
public class Word {
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年9月14日
	 * @description: 词名
	 */
	private String name;
	/**
	 * @type: {@link String}
	 * @author: LiuGangQiang
	 * @date: 2018年9月14日
	 * @description: 词性
	 */
	private String pos;
	/**
	 * @type: {@link Float}
	 * @author: LiuGangQiang
	 * @date: 2018年9月14日
	 * @description: 权重
	 */
	private Float weight;

	public Word(String name, String pos) {
		this.name = name;
		this.pos = pos;
	}

	public Word(String name, String pos, Float weight) {
		this.name = name;
		this.pos = pos;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

}