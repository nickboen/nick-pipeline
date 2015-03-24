package org.gradle.example;

import org.gradle.IPipelineStep;

public abstract class AbstractStep<T, R> implements IPipelineStep {

	public String getStepIdentifier() {
		return "";
	}
	
}
