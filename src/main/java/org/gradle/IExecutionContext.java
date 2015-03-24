package org.gradle;

public interface IExecutionContext {

	void abortProcessing();

	void processingComplete();

	void continueProcessing();

}
