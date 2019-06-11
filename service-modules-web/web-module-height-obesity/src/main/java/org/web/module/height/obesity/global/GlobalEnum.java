package org.web.module.height.obesity.global;

/**
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: LiuGangQiang
 * @date: 2018年11月26日
 * @description: 全局枚举
 */
public interface GlobalEnum {

	/**
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: WangHuaQiang
	 * @date: 2018年12月19日
	 * @description: 分诊状态
	 */
	public enum TriageRecordStatus {

	/**
	 * @type: {@link TriageRecordStatus}
	 * @author: WangHuaQiang
	 * @date: 2018年12月19日
	 * @description: 1 待接诊
	 */
	WAITING(1),

	/**
	 * @type: {@link TriageRecordStatus}
	 * @author: WangHuaQiang
	 * @date: 2018年12月19日
	 * @description: 2已接诊
	 */
	ALREADYACCEPTED(2);

		private Integer value;

		public int getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		private TriageRecordStatus(Integer value) {
			this.value = value;
		}
	}
	
	public enum UserExaminationMasterRecordType {
		
		/**
		 * @type: {@link TriageRecordStatus}
		 * @author: WangHuaQiang
		 * @date: 2018年12月19日
		 * @description: 1用户上传
		 */
		USER(1),
		
		/**
		 * @type: {@link TriageRecordStatus}
		 * @author: WangHuaQiang
		 * @date: 2018年12月19日
		 * @description: 2医生上传
		 */
		DOCTOR(2);
		
		private Integer value;
		
		public int getValue() {
			return value;
		}
		
		public void setValue(Integer value) {
			this.value = value;
		}
		
		private UserExaminationMasterRecordType(Integer value) {
			this.value = value;
		}
	}

	/**
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月24日
	 * @description: 身高百分位
	 */
	public enum Percentile {

		/**
		 * @type: {@link }
		 * @author: ZhangGuangZhi
		 * @date: 2018年12月24日
		 * @description: 2 第2百分位数
		 */
		Two(2),

		/**
		 * @type: {@link }
		 * @author: ZhangGuangZhi
		 * @date: 2018年12月24日
		 * @description: 3 第三百分位数
		 */
		THREE(3),

		/**
		 * @type: {@link }
		 * @author: ZhangGuangZhi
		 * @date: 2018年12月24日
		 * @description: 25 第25百分位数
		 */
		TwentyFive(25),

		/**
		 * @type: {@link }
		 * @author: ZhangGuangZhi
		 * @date: 2018年12月24日
		 * @description: 50 第50百分位数
		 */
		Fifty(50),

		/**
		 * @type: {@link }
		 * @author: ZhangGuangZhi
		 * @date: 2018年12月24日
		 * @description: 75 第75百分位数
		 */
		SeventyFive(75),

		/**
		 * @type: {@link }
		 * @author: ZhangGuangZhi
		 * @date: 2018年12月24日
		 * @description: 97 第97百分位数
		 */
		NinetySeven(97);

		private Integer value;

		public int getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		private Percentile(Integer value) {
			this.value = value;
		}
	}

	/**
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: WangHuaQiang
	 * @date: 2018年12月24日
	 * @description: 儿童测量状态
	 */
	public enum ChildrenMeasureStatus {

		/**
		 * @type: {@link ChildrenMeasureStatus}
		 * @author: WangHuaQiang
		 * @date: 2018年12月19日
		 * @description: 1 已测量
		 */
		MEASURED(1),

		/**
		 * @type: {@link ChildrenMeasureStatus}
		 * @author: WangHuaQiang
		 * @date: 2018年12月19日
		 * @description: 2 服务中
		 */
		INSERVICE(2),

		/**
		 * @type: {@link ChildrenMeasureStatus}
		 * @author: WangHuaQiang
		 * @date: 2018年12月24日
		 * @description: 3 已完成
		 */
		FINISHED(3);

		private Integer value;

		public int getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		private ChildrenMeasureStatus(Integer value) {
			this.value = value;
		}
	}

	/**
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月25日
	 * @description: 标准差
	 */
	public enum Sd {

		SUB_THREE(-3), SUB_TWO(-2), SUB_ONE(-1), MEDIAN_SD(0), PLUS_ONE_SD(1), PLUS_TWO_SD(2), PLUS_THREE_SD(3);

		private Integer value;

		public int getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		private Sd(Integer value) {
			this.value = value;
		}

	}
	
	/**
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月25日
	 * @description: 体型
	 */
	public enum Shape{
		
		/**
		 * @type: {@link Shape}
		 * @author: ZhangGuangZhi
		 * @date: 2018年12月25日
		 * @description: 1 超重
		 */		
		OVER_WEIGHT(1),
		
		/**
		 * @type: {@link Shape}
		 * @author: ZhangGuangZhi
		 * @date: 2018年12月25日
		 * @description: 2 肥胖
		 */		
		OBESITY(2),
		
		/**
		 * @type: {@link Shape}
		 * @author: ZhangGuangZhi
		 * @date: 2018年12月25日
		 * @description: 3 中度消瘦
		 */		
		MODERATE_EMACIATION(3),
		
		/**
		 * @type: {@link Shape}
		 * @author: ZhangGuangZhi
		 * @date: 2018年12月25日
		 * @description: 4 重度消瘦
		 */		
		SEVERE_EMACIATION(4),
		
		/**
		 * @type: {@link Shape}
		 * @author: ZhangGuangZhi
		 * @date: 2018年12月25日
		 * @description: 5 正常体型
		 */		
		NORMAL(5);
		
		private Integer value;

		public int getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		private Shape(Integer value) {
			this.value = value;
		}
	}

}
