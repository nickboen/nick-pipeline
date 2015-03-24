package org.gradle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PipelineResult<T, R> implements Serializable {

	private static final long serialVersionUID = 1L;

	StatusEnum status;

	List<String> steps;

	List<Long> performanceData;

	Map<T, R> pipelineContext;

	List<Notification> notifications;

	private long getTotalExecutionTime() {
		long aggTime = 0;

		for (Long stepTime : performanceData) {
			aggTime += stepTime;
		}

		return aggTime;
	}

	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		sb.append("steps", steps);
		sb.append("status", status);
		sb.append("pipelineContext", pipelineContext);
		sb.append("notifications", notifications);
		sb.append("totalExecutionTime", getTotalExecutionTime());

		for (int i = 0; i < performanceData.size(); i++) {
			sb.append("Step: " + steps.get(i) + " duration: ",
					performanceData.get(i));
		}

		return sb.toString();
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<String> getSteps() {
		return steps;
	}

	public Map<T, R> getPipelineContext() {
		return pipelineContext;
	}

	public void setPipelineContext(Map<T, R> pipelineContext) {
		this.pipelineContext = pipelineContext;
	}

	public void setSteps(List<IPipelineStep<T, R>> pipelineSteps) {
		if (pipelineSteps != null) {
			steps = new ArrayList<String>(pipelineSteps.size());
			for (IPipelineStep<T, R> step : pipelineSteps) {
				steps.add(step.getClass().getName());
			}
		}

	}

	public List<Long> getPerformanceData() {
		return performanceData;
	}

	public void setPerformanceData(List<Long> performanceData) {
		this.performanceData = performanceData;
	}

	public enum StatusEnum {
		COMPLETED, COMPLETED_WITH_ERRORS, ABORTED
	}

}
