package org.gradle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This class prepares a pipeline and processes it
 * 
 * @author nick.boen
 *
 * @param <T>
 *            Context Key Type
 * @param <R>
 *            Output
 */
public class Pipeline<T, R> {

	List<IPipelineStep<T, R>> steps;
	NotificationMonitor monitor;

	public static <T, R> Pipeline<T, R> build(
			IPipelineStep<T, R>... pipelineSteps) {

		// Validate steps were passed in
		if (pipelineSteps == null || pipelineSteps.length == 0) {
			throw new IllegalArgumentException(
					"No steps were provided for the pipeline to execute");
		}

		// Create the pipeline
		Pipeline<T, R> pipeline = new Pipeline<T, R>();
		pipeline.steps = Arrays.asList(pipelineSteps);

		return pipeline;
	}

	public static <T, R> Pipeline<T, R> build(
			List<IPipelineStep<T, R>> pipelineSteps) {

		// Validate steps were passed in
		if (pipelineSteps == null || pipelineSteps.size() == 0) {
			throw new IllegalArgumentException(
					"No steps were provided for the pipeline to execute");
		}

		// Create the pipeline
		Pipeline<T, R> pipeline = new Pipeline<T, R>();
		pipeline.steps = pipelineSteps;

		return pipeline;
	}

	public PipelineResult<T, R> process(Map<T, R> pipelineContext) {
		PipelineResult<T, R> result = new PipelineResult<T, R>();

		for (IPipelineStep<T, R> step : steps) {
			step.process(pipelineContext, monitor);
		}

		return result;
	}

	private Pipeline() {
		// Prevent pipeline from being created directly

		steps = new ArrayList<IPipelineStep<T, R>>();
		monitor = new NotificationMonitor();

	}
}
