package org.gradle;

import java.util.Map;

public interface IPipelineStep<T, R> {

	String getStepIdentifier();

	void process(Map<T, R> pipelineContext, NotificationMonitor monitor,
			IExecutionContext executionContext);

}
