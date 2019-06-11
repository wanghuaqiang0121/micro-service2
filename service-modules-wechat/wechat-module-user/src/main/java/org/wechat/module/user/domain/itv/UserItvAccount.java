package  org.wechat.module.user.domain.itv;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.service.core.entity.BaseEntity;
/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: 用户itv账号
 */
public class UserItvAccount extends BaseEntity {
	
	
	private static final long serialVersionUID = 5743786639528831960L;

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @description:  itv绑定用户列表校验
	 */
	public interface ItvUsers{};
	
	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @description: itv用户登录
	 */
	public interface Login{};
	
	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @description: token
	 */
	public interface ValidToken{};
	
	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @description: 密码
	 */
	public interface ValidPassword{};
	
	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @description: 解除绑定
	 */
	public interface Unbind{};
	
	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @description: 注销登录
	 */
	public interface LoginOut{};
	
    private Integer id;
    //@NotNull(message = "{user.itv.account.userId.is.not.null}", groups = {Login.class,ValidToken.class,ValidPassword.class} )
    private Integer userId;
    @Length(min = 0, max = 64, message = "{user.itv.account.itvSn.length}", groups = { Insert.class})
    @NotBlank(message = "{user.itv.account.itvSn.is.not.null}", groups = {Login.class,ItvUsers.class,Insert.class,ValidPassword.class} )
    private String itvSn;
    @Length(min = 0, max = 32, message = "{user.itv.account.password.length}", groups = { Insert.class})
    @NotBlank(message = "{user.itv.account.password.is.not.null}", groups = {Insert.class,ValidPassword.class} )
    private String password;

    @NotBlank(message = "{user.itv.account.validCode.is.not.null}", groups = { Insert.class})
	private String validCode;
    
    private Integer status;

    private Date createDate;

    @NotBlank(message = "{user.itv.account.phone.is.not.null}", groups = { Insert.class})
    private String phone;
    
    public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getItvSn() {
        return itvSn;
    }

    public void setItvSn(String itvSn) {
        this.itvSn = itvSn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class,ItvUsers.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class,ItvUsers.class })
	public Integer getPageSize() {
		return super.getPageSize();
	}

}