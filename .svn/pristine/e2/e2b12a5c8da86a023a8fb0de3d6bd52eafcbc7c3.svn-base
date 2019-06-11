package org.service.task.delay.entity.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.service.core.entity.BaseEntity;

public class ServiceTimeout extends BaseEntity {
	private static final long serialVersionUID = 1L;
	@NotNull(message = "{service.timeout.id.not.null}", groups = { Insert.class })
	private Integer id;
	@NotNull(message = "{service.timeout.type.not.null}", groups = { Insert.class })
	private ServiceType type;
	@NotNull(message = "{service.timeout.time.not.null}", groups = { Insert.class })
	@Min(message = "{service.timeout.time.min}", value = 0, groups = { Insert.class })
	private Integer time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ServiceType getType() {
		return type;
	}

	public void setType(ServiceType type) {
		this.type = type;
	}

	public Integer getTime() {
		if (time > 0) {
			return time * 1000;
		}
		return 1;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}
