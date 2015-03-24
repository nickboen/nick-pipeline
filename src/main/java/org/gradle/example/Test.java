package org.gradle.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gradle.IPipelineStep;
import org.gradle.Pipeline;
import org.gradle.PipelineResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Test test = new Test();
		test.runPipeline_MultiStep();

	}

	public void runPipelineWithNoInput() {
		Map<TestPipelineEnum, Object> pipelineContext = new HashMap<TestPipelineEnum, Object>();

		List<IPipelineStep<TestPipelineEnum, Object>> steps = new ArrayList<IPipelineStep<TestPipelineEnum, Object>>();
		steps.add(new ValidationStep());

		doPipeline(steps, pipelineContext);

	}

	public void runPipelineBadTypeForValidationStep() {
		Map<TestPipelineEnum, Object> pipelineContext = new HashMap<TestPipelineEnum, Object>();
		pipelineContext.put(TestPipelineEnum.INPUT, "Bad type");

		List<IPipelineStep<TestPipelineEnum, Object>> steps = new ArrayList<IPipelineStep<TestPipelineEnum, Object>>();
		ValidationStep validationStep = new ValidationStep();

		steps.add(validationStep);

		doPipeline(steps, pipelineContext);

	}

	public void runPipelineBadSkuForValidationStep() {
		Map<TestPipelineEnum, Object> pipelineContext = new HashMap<TestPipelineEnum, Object>();
		Product product = new Product();
		product.setSku("sku1");
		pipelineContext.put(TestPipelineEnum.INPUT, product);

		List<IPipelineStep<TestPipelineEnum, Object>> steps = new ArrayList<IPipelineStep<TestPipelineEnum, Object>>();
		ValidationStep validationStep = new ValidationStep();

		steps.add(validationStep);

		doPipeline(steps, pipelineContext);

	}

	public void runPipelineValidSkuForValidationStep() {
		Map<TestPipelineEnum, Object> pipelineContext = new HashMap<TestPipelineEnum, Object>();
		Product product = new Product();
		product.setSku("sku77");
		pipelineContext.put(TestPipelineEnum.INPUT, product);

		List<IPipelineStep<TestPipelineEnum, Object>> steps = new ArrayList<IPipelineStep<TestPipelineEnum, Object>>();
		ValidationStep validationStep = new ValidationStep();

		steps.add(validationStep);

		doPipeline(steps, pipelineContext);

	}

	public void runPipeline_ValidationStopProcessing() {
		Map<TestPipelineEnum, Object> pipelineContext = new HashMap<TestPipelineEnum, Object>();
		Product product = new Product();
		product.setSku("sku11");
		pipelineContext.put(TestPipelineEnum.INPUT, product);

		List<IPipelineStep<TestPipelineEnum, Object>> steps = new ArrayList<IPipelineStep<TestPipelineEnum, Object>>();
		ValidationStep validationStep = new ValidationStep();

		steps.add(validationStep);

		doPipeline(steps, pipelineContext);

	}

	public void runPipeline_MultiStep() {
		Map<TestPipelineEnum, Object> pipelineContext = new HashMap<TestPipelineEnum, Object>();
		Product product = new Product();
		product.setSku("sku11");
		pipelineContext.put(TestPipelineEnum.INPUT, product);

		List<IPipelineStep<TestPipelineEnum, Object>> steps = new ArrayList<IPipelineStep<TestPipelineEnum, Object>>();
		ValidationStep validationStep = new ValidationStep();
		SkuLookupStep skuLookupStep = new SkuLookupStep();

		steps.add(validationStep);
		steps.add(skuLookupStep);

		doPipeline(steps, pipelineContext);

	}

	private void doPipeline(
			List<IPipelineStep<TestPipelineEnum, Object>> steps,
			Map<TestPipelineEnum, Object> pipelineContext) {
		Pipeline<TestPipelineEnum, Object> pipeline = Pipeline.build(steps);

		PipelineResult<TestPipelineEnum, Object> result = pipeline
				.process(pipelineContext);

		LOGGER.info(result.toString());
	}
}
