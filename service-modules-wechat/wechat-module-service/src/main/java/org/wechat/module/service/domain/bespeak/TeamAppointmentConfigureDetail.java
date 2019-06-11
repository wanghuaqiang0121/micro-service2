package org.wechat.module.service.domain.bespeak;

import java.util.Date;
import org.service.core.entity.BaseEntity;

/**
 * 
 * Copyright © 2018 四川易迅通健康医疗技术发展有限公司. All rights reserved.
 *
 * @author: ChenYan
 * @date: 2018年10月18日
 * @description: TeamAppointmentConfigureDetail
 */
public class TeamAppointmentConfigureDetail extends BaseEntity {
   
	private static final long serialVersionUID = 7879900513576167344L;

	private Integer id;

    private Integer teamAppointmentConfigureId;

    private Date startDate;

    private Date endDate;

    private Integer startNo;

    private Integer endNo;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamAppointmentConfigureId() {
        return teamAppointmentConfigureId;
    }

    public void setTeamAppointmentConfigureId(Integer teamAppointmentConfigureId) {
        this.teamAppointmentConfigureId = teamAppointmentConfigureId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStartNo() {
        return startNo;
    }

    public void setStartNo(Integer startNo) {
        this.startNo = startNo;
    }

    public Integer getEndNo() {
        return endNo;
    }

    public void setEndNo(Integer endNo) {
        this.endNo = endNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}