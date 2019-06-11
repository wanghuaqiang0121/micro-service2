package org.wechat.module.height.obesity.controller.examine;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.service.core.api.ApiCodeEnum;
import org.service.core.api.JsonApi;
import org.service.core.api.MultiLine;
import org.service.core.auth.control.RequiresAuthentication;
import org.service.core.entity.BaseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.wechat.module.height.obesity.entity.UserExaminationMasterRecord;
import org.wechat.module.height.obesity.global.BaseGlobal;
import org.wechat.module.height.obesity.message.Prompt;
import org.wechat.module.height.obesity.service.UserExaminationMasterRecordService;
import org.wechat.module.height.obesity.service.feign.IUserService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RestController
public class UserExaminationMasterRecordController {
	
	@Resource
	private  UserExaminationMasterRecordService userExaminationMasterRecordService ;
	@Resource
	private IUserService useruService;
	/**
	 * @author: ChenYan
	 * @date: 2018年12月14日
	 * @param userExaminationMasterRecord
	 * @param result
	 * @param token
	 * @return
	 * @description: 检验检查报告列表
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/user/examination/master/records" })
	public JsonApi getList(@Validated({ BaseEntity.SelectAll.class })  UserExaminationMasterRecord userExaminationMasterRecord,BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token) {
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		userExaminationMasterRecord.setUserId(userId);
		Page<?> page = PageHelper.startPage(userExaminationMasterRecord.getPage(), userExaminationMasterRecord.getPageSize());
		List<Map<String, Object>> userExaminationMasterRecordList = userExaminationMasterRecordService.getList(userExaminationMasterRecord);
		if (userExaminationMasterRecordList != null && !userExaminationMasterRecordList.isEmpty()) {
			return new JsonApi(ApiCodeEnum.OK, new MultiLine(page.getTotal(), userExaminationMasterRecordList));
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2018年12月19日
	 * @param id
	 * @param userExaminationMasterRecord
	 * @param result
	 * @param token
	 * @return
	 * @description: 查询检查检验详情
	 */
	@RequiresAuthentication(authc = true)
	@GetMapping(value = { "/user/examination/master/record/{id}" })
	public JsonApi getOne(
			@PathVariable("id") Integer id,@Validated({ BaseEntity.SelectOne.class }) UserExaminationMasterRecord userExaminationMasterRecord,BindingResult result,@RequestHeader(required = true, value = BaseGlobal.TOKEN_FLAG) String token){
		/*获取并设置用户ID  */
		Integer userId = null;
		JsonApi jsonApi = useruService.getSession(BaseGlobal.CACHE_USER, token,token);
		if (jsonApi.getCode() == ApiCodeEnum.OK.getValue()) {
			userId = Integer.parseInt(jsonApi.getData().toString());
		}else {
			 return new JsonApi(ApiCodeEnum.NOT_FOUND).setMsg(Prompt.bundle("user.id.is.notblank.valid"));
		}
		userExaminationMasterRecord.setUserId(userId);
		/*设置主键*/
		userExaminationMasterRecord.setId(id);
		Map<String, Object> userExaminationMasterRecordMap = userExaminationMasterRecordService.getOne(userExaminationMasterRecord);
		if (userExaminationMasterRecordMap!=null ) {
			return new JsonApi(ApiCodeEnum.OK,userExaminationMasterRecordMap);
		}
		return new JsonApi(ApiCodeEnum.NOT_FOUND);
		
	}
}
