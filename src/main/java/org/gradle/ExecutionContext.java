package org.gradle;

import java.io.Serializable;

public class ExecutionContext implements IExecutionContext, Serializable {

	private static final long serialVersionUID = 1L;

	private PipelineState pipelineState;

	public PipelineState getPipelineState() {
		return pipelineState;
	}

	public void abortProcessing() {
		pipelineState = PipelineState.ABORT;
	}

	public void processingComplete() {
		pipelineState = PipelineState.COMPLETE;
	}

	public void continueProcessing() {
		pipelineState = PipelineState.CONTINUE;
	}

	enum PipelineState {
		ABORT, COMPLETE, CONTINUE
	}
}
