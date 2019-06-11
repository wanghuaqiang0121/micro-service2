package org.wechat.module.service.global;

public interface BaseGlobalEnum {
	
	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年11月16日
	 * @description: 工单状态枚举
	 */
	public enum WorkOrderStatus{
		
		
		/**
		 *@Fields <font color="blue">WAITRESPONSE</font>
		 *@description 待响应:1
		 */
		PENDINGRESPONSE(1),
		
		/**
		 *@Fields <font color="blue">EXECUTIONED</font>
		 *@description 待执行:2
		 */
		TOBEEXECUTED(2),
		
		/**
		 *@Fields <font color="blue">REFUSED</font>
		 *@description 已关闭:3
		 */
		CLOSED(3),
		
		/**
		 *@Fields <font color="blue">LAUNCHTTYPEONLINE</font>
		 *@description 执行中:4
		 */
	
		INTHEEXECUTION(4),
		
		/**
		 *@Fields <font color="blue">LAUNCHTTYPELINE</font>
		 *@description 已执行（待评价）:5
		 */
		EXECUTED(5),
		
		/**
		 *@Fields <font color="blue">SIGNLOGTYPEOPERATIONLOG</font>
		 *@description 已评价:6
		 */
		EVALUATED(6),
		
		/**
		 *@Fields <font color="blue">ORDERRESOURCEONLINE</font>
		 *@description 工单来源 1：在线-微信
		 */
		ORDERRESOURCEONLINEWECHAT(1),
		
		/**
		 *@Fields <font color="blue">ORDERRESOURCEONLINE</font>
		 *@description 工单来源 2：在线-itv
		 */
		ORDERRESOURCEONLINEITV(2),
		
		/**
		 *@Fields <font color="blue">ORDERRESOURCESCENE</font>
		 *@description  工单来源 3：现场
		 */
		ORDERRESOURCESCENE(3),
		
		/**
		 *@Fields <font color="blue">ORDERRESOURCEPLAN</font>
		 *@description  工单来源 4：计划
		 */
		ORDERRESOURCEPLAN(4),
		
		
		/**
		 *@Fields <font color="blue">SIGNLOGTYPEOPERATIONLOG</font>
		 *@description 0：操作日志
		 */
		OPERATION_LOG(0),
		
		/**
		 *@Fields <font color="blue">SIGNLOGTYPESYNERGETICLOG</font>
		 *@description 1：协同日志
		 */
		WORKORDERLOGTYPESYNERGETICLOG(1),
		
		/**
		 *@Fields <font color="blue">SIGNLOGTYPESYNERGETICLOG</font>
		 *@description 0：医生发起
		 */
		WORKORDERLOGTYPEDOCTORSPONSOR(0),
		
		/**
		 *@Fields <font color="blue">SIGNLOGTYPESYNERGETICLOG</font>
		 *@description 1：用户发起
		 */
		USER_SPONSOR(1);
		
		private int value;
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
		
		private WorkOrderStatus(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年11月16日
	 * @description: 签约状态枚举
	 */
	public enum Sign{
		
        
        /**
         *@Fields <font color="blue">WAITRESPONSE</font>
         *@description 待响应
         */
        WAITRESPONSE(0),
                  
        /**
         *@Fields <font color="blue">EXECUTIONED</font>
         *@description 已执行
         */
        EXECUTIONED(1),
                   
        /**
         *@Fields <font color="blue">REFUSED</font>
         *@description 已拒绝
         */
        REFUSED(2),
        
		/**
		 *@Fields <font color="blue">LAUNCHTTYPEONLINE</font>
		 *@description 线下
		 */
		LAUNCHTTYPEONLINE(1),
		
		/**
		 *@Fields <font color="blue">LAUNCHTTYPELINE</font>
		 *@description 线上
		 */
		LAUNCHTTYPELINE(2),
		
		/**
		 *@Fields <font color="blue">SIGNLOGTYPEOPERATIONLOG</font>
		 *@description 0：操作日志
		 */
		SIGNLOGTYPEOPERATIONLOG(0),
		
		/**
		 *@Fields <font color="blue">SIGNLOGTYPESYNERGETICLOG</font>
		 *@description 1：协同日志
		 */
		SIGNLOGTYPESYNERGETICLOG(1);
		
		private int value;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		private Sign(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年11月16日
	 * @description: 问询状态
	 */
	public enum Status {
		/**
		 *@Fields <font color="blue">TOBEACCEPTED</font>
		 *@description 1：待接受（未回复）
		 */
		TOBEACCEPTED(1),
		
		/**
		 *@Fields <font color="blue">ACCEPT</font>
		 *@description 1：已接受
		 */
		ACCEPT(2),
		/**
		 * @Fields <font color="blue">REPLIES</font>
		 * @description 3：已回复
		 */
		REPLIES(3),
		
		/**
		 *@Fields <font color="blue">INQUIRIES</font>
		 *@description 4：有追问
		 */
		INQUIRIES(4),
		
		/**
		 *@Fields <font color="blue">CLOSED</font>
		 *@description 5：已关闭
		 */
		CLOSED(5),
		/**
		 *@Fields <font color="blue">FINISHED</font>
		 *@description 6：已结束
		 */
		FINISHED(6);
		private int value;
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
		
		private Status(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年11月16日
	 * @description: 问询类型
	 */
	public enum Type {
		/**
		 *@Fields <font color="blue">REPLY</font>
		 *@description 1：回复
		 */
		REPLY(1),
		
		/**
		 * @Fields <font color="blue">INQUIRIES</font>
		 * @description 2：追问
		 */
		INQUIRIES(2);
		private int value;
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
		
		private Type(int value) {
			this.value = value;
		}
	}
	/**
	 * @author <font color="red"><b>Wang.Hua.Qiang</b></font>
	 * @Date 2018年10月16日
	 * @Version Inquiry
	 * @Description 追问次数
	 */
	public enum InquiriesNum {
		
		/**
		 *@Fields <font color="blue">TOTAL</font>
		 *@description 2次
		 */
		TOTAL(2);
		private int value;
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
		
		private InquiriesNum(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月15日
	 * @description: 签约类型
	 */
	public enum LaunchTypeEnum {
		/**
		 * @Fields <font color="blue">ONLINE</font>
		 * @description
		 */
		ONLINE(1),
		/**
		 * @Fields <font color="blue">LINE</font>
		 * @description 线下签约
		 */
		LINE(2);
		private int value;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		private LaunchTypeEnum(int value) {
			this.value = value;
		}
	}

	/**
	 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
	 * @date 2018年8月13日
	 * @version 1.0
	 * @description 签约状态
	 */
	public enum StatusEnum {

		/**
		 * @Fields <font color="blue">WAITRESPONSE</font>
		 * @description 待响应
		 */
		WAITRESPONSE(0),

		/**
		 * @Fields <font color="blue">EXECUTIONED</font>
		 * @description 已执行
		 */
		EXECUTIONED(1),

		/**
		 * @Fields <font color="blue">REFUSED</font>
		 * @description 已拒绝
		 */
		REFUSED(2);

		private int value;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		private StatusEnum (int value) {
			this.value = value;
		}
	}

	/**
	 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
	 * @date 2018年8月13日
	 * @version 1.0
	 * @description 签约日志类型
	 */
	public enum LogTypeEnum  {

		/**
		 * @Fields <font color="blue">OPERATIONLOG</font>
		 * @description 0：操作日志
		 */
		OPERATIONLOG(0),

		/**
		 * @Fields <font color="blue">SYNERGETICLOG</font>
		 * @description 1：协同日志
		 */
		SYNERGETICLOG(1);

		private int value;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		private LogTypeEnum (int value) {
			this.value = value;
		}
	}
	
	public enum OrderStatus {
		/**
		 *@Fields <font color="blue">PENDINGNUMBER</font>
		 *@description 1：待取号
		 */
		PENDINGNUMBER(1),
		
		/**
		 * @Fields <font color="blue">FETCHEDNUMBER</font>
		 * @description 2：已取号
		 */
		FETCHEDNUMBER(2),
		
		/**
		 *@Fields <font color="blue">CANCEL</font>
		 *@description 3：已取消
		 */
		CANCEL(3);
		private int value;
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
		
		private OrderStatus(int value) {
			this.value = value;
		}
	}
	
	public enum TeamAppointmentConfigureStatus {
		/**
		 *@Fields <font color="blue">ENABLE</font>
		 *@description 启用
		 */
		ENABLE(1),
		
		/**
		 * @Fields <font color="blue">OffLine</font>
		 * @description 禁用
		 */
		DISABLE(2);
		private int value;
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
		
		private TeamAppointmentConfigureStatus(int value) {
			this.value = value;
		}
	}
	/**
	 * @author <font color="red"><b>Wang.Hua.Qiang</b></font>
	 * @Date 2018年10月15日
	 * @Version TeamAppointmentConfigure
	 * @Description 周期天数
	 */
	public enum CycleNum {
		/**
		 *@Fields <font color="blue">ENABLE</font>
		 *@description 7天
		 */
		WEEK(7);
		private int value;
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
		
		private CycleNum(int value) {
			this.value = value;
		}
	}
	public enum Cycle {
		/**
		 *@Fields <font color="blue">EVERYDAY</font>
		 *@description 每天
		 */
		EVERYDAY(1),
		
 		/**
 		 *@Fields <font color="blue">CUSTOM</font>
 		 *@description 自定义
 		 */
 		CUSTOM(2);
		private int value;
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
		
		private Cycle(int value) {
			this.value = value;
		}
	}
	
	/**
	 * @author <font color="red"><b>Gong.YiYang</b></font>
	 * @Date 2018年3月23日
	 * @Version OrganizationStatusCode
	 * @Description 模板类型
	 */
	public enum IWechatApiType {
		/**
		 * @Fields <font color="blue">SEND</font>
		 * @description 模板类型1
		 */
		ONE("微信推送"),
		/**
		 * @Fields <font color="blue">RECHARGE</font>
		 * @description 模板类型2
		 */
		TWO("微信推送");

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		private IWechatApiType(String value) {
			this.value = value;
		}
	}
	
}
