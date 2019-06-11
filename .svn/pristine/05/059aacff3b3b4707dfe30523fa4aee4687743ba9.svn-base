package org.web.module.organization.service.service.packages;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.organization.dao.OrganizationServiceTypeMapper;
import org.web.module.organization.domain.OrganizationServiceType;

@Service
public class OrganizationServiceTypeService {

	@Resource
	private OrganizationServiceTypeMapper organizationServiceTypeMapper;
	
	public List<Map<String, Object>> getList(OrganizationServiceType organizationServiceType) {
		return organizationServiceTypeMapper.getList(organizationServiceType);
	}

	public Map<String, Object> getRepeat(OrganizationServiceType organizationServiceType) {
		return organizationServiceTypeMapper.getRepeat(organizationServiceType);
	}

	public int insert(OrganizationServiceType organizationServiceType) {
		return organizationServiceTypeMapper.insert(organizationServiceType);
	}

	public int delete(OrganizationServiceType organizationServiceType) {
		return organizationServiceTypeMapper.delete(organizationServiceType);
	}
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2018年10月10日
	 * @param organizationServiceType
	 * @return
	 * @description: 机构没有的服务类型
	 */
	public List<Map<String, Object>> getOrganizationIsNullServiceTypeList(
			OrganizationServiceType organizationServiceType) {
		return organizationServiceTypeMapper.getOrganizationIsNullServiceTypeList(organizationServiceType);
	}
	
	/**
	 * 
	 * @author <font color="green"><b>Chen.Yan</b></font>
	 * @param organizationServiceType
	 * @return
	 * @date 2018年6月6日
	 * @version 1.0
	 * @description 查询机构拥有和未拥有的服务类型
	 */
	public List<Map<String, Object>> getOrganizationServiceTypeIsChoose(OrganizationServiceType organizationServiceType){
		return organizationServiceTypeMapper.getOrganizationServiceTypeIsChoose(organizationServiceType);
	}

}
