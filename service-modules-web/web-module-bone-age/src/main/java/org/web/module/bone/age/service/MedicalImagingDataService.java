package org.web.module.bone.age.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.web.module.bone.age.dao.MedicalImagingDataMapper;
import org.web.module.bone.age.domain.MedicalImagingData;

@Service
public class MedicalImagingDataService {
	@Resource
	private MedicalImagingDataMapper medicalImagingDataMapper;
	
	/**
	 * @author: ChenYan
	 * @date: 2019年4月8日
	 * @param medicalImagingData
	 * @return
	 * @description: 上传影像资料
	 */
	public int insert(MedicalImagingData medicalImagingData) {
		return medicalImagingDataMapper.insert(medicalImagingData);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2019年4月8日
	 * @param medicalImagingData
	 * @return
	 * @description: 列表
	 */
	public List<Map<String, Object>> getList(MedicalImagingData medicalImagingData) {
		return medicalImagingDataMapper.getList(medicalImagingData);
	}

	/**
	 * @author: ChenYan
	 * @date: 2019年4月8日
	 * @param medicalImagingData
	 * @return
	 * @description: 提交咨询意见书
	 */
	public int update(MedicalImagingData medicalImagingData) {
		return medicalImagingDataMapper.update(medicalImagingData);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2019年4月8日
	 * @param medicalImagingData
	 * @return
	 * @description: 查看咨询意见书,查看委托书,查看退回原因
	 */
	public Map<String, Object> getOne(MedicalImagingData medicalImagingData) {
		return medicalImagingDataMapper.getOne(medicalImagingData);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2019年4月8日
	 * @param medicalImagingData
	 * @return
	 * @description: 通过用户id查询用户的影像资料  骨龄报告(送检)状态1.未完成 （不新增）2.已完成 （新增）
	 */
	public List<Map<String, Object>> getUser(MedicalImagingData medicalImagingData){
		return medicalImagingDataMapper.getUser(medicalImagingData);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2019年4月11日
	 * @param medicalImagingData
	 * @return
	 * @description: 查询机构详情
	 */
	public Map<String, Object> getOrganiztionOne(MedicalImagingData medicalImagingData){
		return medicalImagingDataMapper.getOrganiztionOne(medicalImagingData);
	}

	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2019年4月17日
	 * @param medicalImagingData
	 * @return
	 * @description: 查询骨龄司法鉴定列表
	 */
	public List<Map<String, Object>> getJudicialBoneAgeList(MedicalImagingData medicalImagingData){
		return medicalImagingDataMapper.getJudicialBoneAgeList(medicalImagingData);
	}
	
	/**
	 * 
	 * @author: ChenYan
	 * @date: 2019年4月17日
	 * @param medicalImagingData
	 * @return
	 * @description: 查询临床鉴定委托书列表
	 */
	public List<Map<String, Object>> getentrusImgList(MedicalImagingData medicalImagingData){
		return medicalImagingDataMapper.getentrusImgList(medicalImagingData);
	}
	
	
	/**
	 * @author: ChenYan
	 * @date: 2019年4月24日
	 * @param medicalImagingData
	 * @return
	 * @description: 影像资料列表
	 */
	public List<Map<String, Object>> getMedicalImagingList(MedicalImagingData medicalImagingData){
		return medicalImagingDataMapper.getMedicalImagingList(medicalImagingData);
	}
	
	/**
	 * @author: ChenYan
	 * @date: 2019年4月24日
	 * @param medicalImagingData
	 * @return
	 * @description:搜索用户
	 */
	public List<Map<String, Object>> getMedicalImagingQueryList(MedicalImagingData medicalImagingData){
		return medicalImagingDataMapper.getMedicalImagingQueryList(medicalImagingData);
	}
}
