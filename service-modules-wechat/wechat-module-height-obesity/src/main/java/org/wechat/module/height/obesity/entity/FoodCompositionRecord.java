package org.wechat.module.height.obesity.entity;

import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

public class FoodCompositionRecord extends BaseEntity {
    /**
	 * @type: {@link long}
	 * @author: ZhangGuangZhi
	 * @date: 2018年12月11日
	 * @description: TODO
	 */
	private static final long serialVersionUID = -8350666697363566181L;

	private Integer id;

    private String foodCompositionCode;

    private String code;

    private String codeName;

    private String codeUnit;

    private Float value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

 

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeUnit() {
        return codeUnit;
    }

    public void setCodeUnit(String codeUnit) {
        this.codeUnit = codeUnit;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

	
	public String getFoodCompositionCode() {
		return foodCompositionCode;
	}

	public void setFoodCompositionCode(String foodCompositionCode) {
		this.foodCompositionCode = foodCompositionCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Override
   	@NotNull(message = "{page.empty}", groups = { SelectAll.class})
   	public Integer getPage() {
   		return super.getPage();
   	}

   	@Override
   	@NotNull(message = "{pageSize.empty}", groups = { SelectAll.class })
   	public Integer getPageSize() {
   		return super.getPageSize();
   	} 
}