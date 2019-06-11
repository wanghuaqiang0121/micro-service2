package org.web.module.height.obesity.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

public class UserTriageRecord extends BaseEntity {
	
    public interface TodayList {

	}
	private static final long serialVersionUID = 2143775307757228397L;

	private Integer id;
	@NotNull(message = "{user.triage.record.user.id.not.null.valid}", groups = { Insert.class })
    private Integer userId;

    private Integer sex;
    
    private Integer organizationId;

    private Integer organizationTeamId;

    private Integer organizationUserId;

    //@NotNull(message = "{organization.consulting.room.id.not.null.valid}", groups = { Insert.class })
    private Integer organizationConsultingRoomId;

    private Integer status;
    
    private Integer source;

    private Date getTriageTime;
    //@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDateTime;
    //@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCreateDateTime;
    private String filed;
    
    public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Date getEndCreateDateTime() {
		return endCreateDateTime;
	}

	public void setEndCreateDateTime(Date endCreateDateTime) {
		this.endCreateDateTime = endCreateDateTime;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
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

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getOrganizationTeamId() {
        return organizationTeamId;
    }

    public void setOrganizationTeamId(Integer organizationTeamId) {
        this.organizationTeamId = organizationTeamId;
    }

    public Integer getOrganizationUserId() {
        return organizationUserId;
    }

    public void setOrganizationUserId(Integer organizationUserId) {
        this.organizationUserId = organizationUserId;
    }

    public Integer getOrganizationConsultingRoomId() {
        return organizationConsultingRoomId;
    }

    public void setOrganizationConsultingRoomId(Integer organizationConsultingRoomId) {
        this.organizationConsultingRoomId = organizationConsultingRoomId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGetTriageTime() {
        return getTriageTime;
    }

    public void setGetTriageTime(Date getTriageTime) {
        this.getTriageTime = getTriageTime;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
    @Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class,TodayList.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class,TodayList.class })
	public Integer getPageSize() {
		return super.getPageSize();
	}
}