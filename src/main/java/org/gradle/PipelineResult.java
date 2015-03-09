package org.gradle;

import java.io.Serializable;

public class PipelineResult<T, R> implements Serializable {

	private static final long serialVersionUID = 1L;

	StatusEnum status;

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public enum StatusEnum {
		SUCCESS
	}

}
