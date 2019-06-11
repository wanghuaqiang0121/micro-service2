package org.web.module.organization.domain.user;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.service.core.entity.BaseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月12日
 * @description: 机构用户
 */
public class OrganizationUser extends BaseEntity {
	
	private static final long serialVersionUID = 6825175338596685706L;

	/**
	 * @author <font color="red"><b>Gong.YiYang</b></font>
	 * @Date 2018年5月31日
	 * @Version OrganizationUser
	 * @Description 修改密码
	 */
	public interface changePassword {

	}

	/**
	 * @author <font color="red"><b>Gong.YiYang</b></font>
	 * @Date 2018年3月9日
	 * @Version OrganizationUser
	 * @Description 查询机构用户模块权限列表
	 */
	public interface ModulePermissionList {

	}

	/**
	 * @author <font color="red"><b>Gong.YiYang</b></font>
	 * @Date 2018年3月9日
	 * @Version OrganizationUser
	 * @Description 查询机构用户模块操作列表
	 */
	public interface ModuleOperationList {

	}

	/**
	 * @author <font color="red"><b>Gong.YiYang</b></font>
	 * @Date 2018年3月9日
	 * @Version OrganizationUser
	 * @Description 查询机构用户模块角色列表
	 */
	public interface ModuleRoleList {

	}

	/**
	 * @author <font color="red"><b>Gong.YiYang</b></font>
	 * @Date 2018年3月9日
	 * @Version OrganizationUser
	 * @Description 模块菜单列表
	 */
	public interface ModuleMenuList {

	}

	/**
	 * @author <font color="red"><b>Gong.YiYang</b></font>
	 * @Date 2018年3月8日
	 * @Version OrganizationUser
	 * @Description 修改密码
	 */
	public interface updatePassword {

	}

	public interface ModuleList {

	}

	public interface Login {
	}
	/**
	 * 
	 * @author <font color="red"><b>Chen.Yan</b></font>
	 * @Date 2018年3月23日
	 * @Version OrganizationUser
	 * @Description 新增机构用户必填信息
	 */
	public interface InsertOrganizationUser{};
	
	
	public interface validCertificateRepeat {

	}

	public interface AccountRepeat {}

	public interface PhoneRepeat {};


	private Integer id;
	@NotBlank(message = "{organization.user.name.not.blank}", groups = { Insert.class ,InsertOrganizationUser.class})
	@Length(min = 0, max = 16, message = "{organization.user.name.length}", groups = { Insert.class ,InsertOrganizationUser.class})
	private String name;
	@NotNull(message = "{organization.user.sex.not.null}", groups = { Insert.class,InsertOrganizationUser.class })
	private Integer sex;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "{organization.user.birthday.not.null}", groups = { Insert.class })
	@Past(message = "{organization.user.birthday.past}", groups = { Insert.class })
	private Date birthday;
	@NotBlank(message = "{organization.user.phone.not.blank}", groups = { Insert.class,InsertOrganizationUser.class,PhoneRepeat.class })
	private String phone;
	private Integer phoneStatus;
	@NotBlank(message = "{organization.user.photo.not.blank}", groups = { Insert.class })
	private String photo;
	private Integer status;
	@NotBlank(message = "{organization.user.account.not.blank}", groups = { Insert.class, Login.class,InsertOrganizationUser.class,AccountRepeat.class  })
	private String account;
	@NotBlank(message = "{organization.user.password.not.blank}", groups = { Login.class, updatePassword.class,changePassword.class })
	private String password;
	//@NotBlank(message = "{organization.user.oldPassword.not.blank}", groups = { changePassword.class })
	private String oldPassword;
	
	private boolean isInitPassword;

	private Date createDate;

	private String remark;
	
	private Integer moduleId;
	private String post;
	private Integer departmentId;
	// 新密码
	@NotBlank(message = "{organization.user.newPassword.not.blank}", groups = { updatePassword.class })
	private String newPassword;

    
	
	@NotNull(message = "{organization.user.person.type.id.notnull.valid}", groups = { Insert.class,InsertOrganizationUser.class})
	private Integer organizationPersonTypeId;
	private String certificateType;
	private String certificateTypeName;
	private String certificateNumber;
	private String positive;
	private String opposite;
	
	
	private DoctorInfo doctorInfo;
	private DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty;
	@Valid
	@NotNull(message = "{organization.user.organizationUserCertificates.notnull.valid}", groups = { InsertOrganizationUser.class,validCertificateRepeat.class})
	 private List<OrganizationUserCertificate> organizationUserCertificates;
	private Integer organizationId;
	 private String workNumber;
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	

	public Integer getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(Integer phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public Integer getOrganizationPersonTypeId() {
		return organizationPersonTypeId;
	}

	public void setOrganizationPersonTypeId(Integer organizationPersonTypeId) {
		this.organizationPersonTypeId = organizationPersonTypeId;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public String getPositive() {
		return positive;
	}

	public void setPositive(String positive) {
		this.positive = positive;
	}

	public String getOpposite() {
		return opposite;
	}

	public void setOpposite(String opposite) {
		this.opposite = opposite;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean getIsInitPassword() {
		return isInitPassword;
	}

	public void setIsInitPassword(boolean isInitPassword) {
		this.isInitPassword = isInitPassword;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	

	public void setInitPassword(boolean isInitPassword) {
		this.isInitPassword = isInitPassword;
	}

	
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}


	public String getCertificateTypeName() {
		return certificateTypeName;
	}

	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}

	public DoctorInfo getDoctorInfo() {
		return doctorInfo;
	}

	public void setDoctorInfo(DoctorInfo doctorInfo) {
		this.doctorInfo = doctorInfo;
	}

	public DoctorOrganizationDepartmentDuty getDoctorOrganizationDepartmentDuty() {
		return doctorOrganizationDepartmentDuty;
	}

	public void setDoctorOrganizationDepartmentDuty(DoctorOrganizationDepartmentDuty doctorOrganizationDepartmentDuty) {
		this.doctorOrganizationDepartmentDuty = doctorOrganizationDepartmentDuty;
	}

	public List<OrganizationUserCertificate> getOrganizationUserCertificates() {
		return organizationUserCertificates;
	}

	public void setOrganizationUserCertificates(List<OrganizationUserCertificate> organizationUserCertificates) {
		this.organizationUserCertificates = organizationUserCertificates;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getWorkNumber() {
		return workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	@Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class, ModuleList.class, ModuleRoleList.class,
			ModulePermissionList.class, ModuleOperationList.class, ModuleMenuList.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class, ModuleList.class, ModuleRoleList.class,
			ModulePermissionList.class, ModuleOperationList.class, ModuleMenuList.class })
	public Integer getPageSize() {
		return super.getPageSize();
	}
}
