package org.gradle.example;

import java.util.Map;

import org.gradle.IExecutionContext;
import org.gradle.IPipelineStep;
import org.gradle.NotificationMonitor;

public abstract class AbstractStep<T, R> implements IPipelineStep<T, R> {

	/**
	 * Validates that the required values are passed into the step
	 * 
	 * @param pipelineContext
	 * @param monitor
	 * 
	 * @return true= validation successful, false= validation failed, stop
	 *         processing
	 */
	abstract boolean validateInput(Map<T, R> pipelineContext,
			NotificationMonitor monitor);

	abstract void executeLogic(Map<T, R> pipelineContext,
			NotificationMonitor monitor, IExecutionContext executionContext);

	public void process(Map<T, R> pipelineContext, NotificationMonitor monitor,
			IExecutionContext executionContext) {
		boolean continueProcessing = validateInput(pipelineContext, monitor);

		if (continueProcessing) {
			executeLogic(pipelineContext, monitor, executionContext);
		} else {
			executionContext.abortProcessing();
		}
	}

}
