package org.wechat.module.height.obesity.dao;

import java.util.List;

import org.service.core.dao.IBaseMapper;
import org.wechat.module.height.obesity.entity.OrganizationConsultingRoom;

public interface OrganizationConsultingRoomMapper extends IBaseMapper<OrganizationConsultingRoom> {
    int deleteByPrimaryKey(Integer id);

    int insert(OrganizationConsultingRoom record);

    OrganizationConsultingRoom selectByPrimaryKey(Integer id);

    List<OrganizationConsultingRoom> selectAll();

    int updateByPrimaryKey(OrganizationConsultingRoom record);
}