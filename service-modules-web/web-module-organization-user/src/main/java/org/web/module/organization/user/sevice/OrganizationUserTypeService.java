package org.web.module.organization.user.sevice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.user.dao.OrganizationUserTypeMapper;
import org.web.module.organization.user.domain.OrganizationUserType;

@Service
public class OrganizationUserTypeService {

	@Resource
	private OrganizationUserTypeMapper organizationUserTypeMapper;

	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param organizationUserType
	 * @return
	 * @date 2018年3月21日
	 * @version 1.0
	 * @description 新增机构用户标签关联
	 */
	public int insert(OrganizationUserType organizationUserType) {
		return organizationUserTypeMapper.insert(organizationUserType);

	}

	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param organizationUserType
	 * @return
	 * @date 2018年3月21日
	 * @version 1.0
	 * @description 删除机构用户标签关联
	 */
	public int delete(OrganizationUserType organizationUserType) {
		return organizationUserTypeMapper.delete(organizationUserType);
	}

	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param organizationUserType
	 * @return
	 * @date 2018年3月21日
	 * @version 1.0
	 * @description 检查数据是否重复
	 */
	public Map<String, Object> getRepeat(OrganizationUserType organizationUserType) {
		return organizationUserTypeMapper.getRepeat(organizationUserType);
	}

	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param organizationUserType
	 * @return
	 * @date 2018年3月21日
	 * @version 1.0
	 * @description 查询机构用户标签关联列表
	 */
	public List<Map<String, Object>> getList(OrganizationUserType organizationUserType) {
		return organizationUserTypeMapper.getList(organizationUserType);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param organizationUserType
	 * @return
	 * @description: 查询机构没有的人群标签
	 */
	public List<Map<String, Object>> getOrganizationUserTypeIsNull(OrganizationUserType organizationUserType) {
		return organizationUserTypeMapper.getOrganizationUserTypeIsNull(organizationUserType);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param organizationUserType
	 * @return
	 * @description: 机构拥有和未拥有的人群类型
	 */
	public List<Map<String, Object>> getOrganizationUserTypehaveAndNotHaveList(
			OrganizationUserType organizationUserType) {
		return organizationUserTypeMapper.getOrganizationUserTypehaveAndNotHaveList(organizationUserType);
	}

}
