package org.gradle.example;

public enum TestPipelineEnum {

	// NOTE: Thought about including the supported class type for each enum.
	// Problem is that will vary by the pipeline configuration. For example,
	// Pipeline Config A may require INPUT to be ProductV1 and Config B may
	// require ProductV4. Ideas?

	EXECUTION_GUID, INPUT, SKU_DETAILS
}
