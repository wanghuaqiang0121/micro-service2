package  org.wechat.module.user.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月16日
 * @description: 用户用户组
 */
public class UserUserGroup extends BaseEntity {
   

	
	private static final long serialVersionUID = -6484392682763600960L;

	/**
	 * 
	 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
	 *
	 * @author: ChenYan
	 * @date: 2018年10月16日
	 * @description: 用户组管理者的成员列表
	 */
    public interface UsersByGroup {

	}
   /**
    * 
    * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
    *
    * @author: ChenYan
    * @date: 2018年10月16日
    * @description:  itv用户组管理者的成员列表
    */
    public interface ItvUsersByGroup {
    	
    }

	private Integer id;

	//@NotNull(message="{user.user.group.userId.is.not.null}",groups = {ItvUsersByGroup.class})
    private Integer userId;

    private Integer userGroupId;

    private String relation;

    private Integer trust;

    private Integer[] trusts;
    
    private Date createDatetime;

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

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Integer getTrust() {
        return trust;
    }

    public void setTrust(Integer trust) {
        this.trust = trust;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
    
    public Integer[] getTrusts() {
		return trusts;
	}

	public void setTrusts(Integer[] trusts) {
		this.trusts = trusts;
	}

	@Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class,UsersByGroup.class,ItvUsersByGroup.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class,UsersByGroup.class,ItvUsersByGroup.class })
	public Integer getPageSize() {
		return super.getPageSize();
	}
}