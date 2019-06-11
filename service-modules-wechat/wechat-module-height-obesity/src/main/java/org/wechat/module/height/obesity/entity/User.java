package org.wechat.module.height.obesity.entity;

import java.util.Date;
import org.service.core.entity.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User extends BaseEntity {
    /**
	 * @type: {@link long}
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月11日
	 * @description: TODO
	 */
	private static final long serialVersionUID = -8681153837923620748L;

	private Integer code;

	private String type;
	private Integer id;
    private Integer monthAge;
    private Integer heightOrWeightType;

    private String name;

    private Integer sex;

    private String idCard;

    private Date birthday;

    private String phone;

    private Integer phoneStatus;

    private String photo;

    private String province;

    private String city;

    private String area;

    private String street;

    private Double lng;

    private Double lat;

    private String address;

    private Integer status;

    private Boolean isBindWechat;

    private Integer source;

    private Integer terminalSource;

    private Date createDate;

    private Date updateDate;

    private String remark;
    @Override
    @NotNull(message = "{page.empty}", groups = { SelectAll.class})
    public Integer getPage() {
        return super.getPage();
    }

    @Override
    @NotNull(message = "{pageSize.empty}", groups = { SelectAll.class})
    public Integer getPageSize() {
        return super.getPageSize();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(Integer phoneStatus) {
        this.phoneStatus = phoneStatus;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsBindWechat() {
        return isBindWechat;
    }

    public void setIsBindWechat(Boolean isBindWechat) {
        this.isBindWechat = isBindWechat;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getTerminalSource() {
        return terminalSource;
    }

    public void setTerminalSource(Integer terminalSource) {
        this.terminalSource = terminalSource;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMonthAge() {
        return monthAge;
    }

    public void setMonthAge(Integer monthAge) {
        this.monthAge = monthAge;
    }

    public Integer getHeightOrWeightType() {
        return heightOrWeightType;
    }

    public void setHeightOrWeightType(Integer heightOrWeightType) {
        this.heightOrWeightType = heightOrWeightType;
    }
}