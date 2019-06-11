package org.wechat.module.height.obesity.entity;

import java.util.Date;
import org.service.core.entity.BaseEntity;

public class UserTriageRecord extends BaseEntity {
    /**
	 * @type: {@link long}
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月11日
	 * @description: TODO
	 */
	private static final long serialVersionUID = 2143775307757228397L;

	private Integer id;

    private Integer userId;

    private Integer organizationId;

    private Integer organizationTeamId;

    private Integer organizationUserId;

    private Integer organizationConsultingRoomId;

    private Integer status;

    private Date getTriageTime;

    private Date createDateTime;

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
}