package org.gradle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PipelineTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuild_Null_Input() {

		IPipelineStep<String, String>[] pipelineSteps = null;
		Pipeline.build(pipelineSteps);

	}

	@SuppressWarnings("unchecked")
	public void testBuild_SingleStep_VarArgs() {

		Pipeline<String, String> pipeline = Pipeline
				.build(createBasicStep("step1"));

		Assert.assertEquals(1, pipeline.steps.size());
		Assert.assertEquals("step1", pipeline.steps.get(0).getStepIdentifier());

	}

	@SuppressWarnings("unchecked")
	public void testBuild_MultipleStep_VarArgs() {
		IPipelineStep<String, String> step1 = createBasicStep("step1");
		IPipelineStep<String, String> step2 = createBasicStep("step2");
		IPipelineStep<String, String> step3 = createBasicStep("step3");

		Pipeline<String, String> pipeline = Pipeline.build(step1, step2, step3);

		Assert.assertEquals(3, pipeline.steps.size());
		Assert.assertEquals("step1", pipeline.steps.get(0).getStepIdentifier());
		Assert.assertEquals("step2", pipeline.steps.get(1).getStepIdentifier());
		Assert.assertEquals("step3", pipeline.steps.get(2).getStepIdentifier());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuild_Null_List() {

		List<IPipelineStep<String, String>> pipelineSteps = null;
		Pipeline.build(pipelineSteps);

	}

	public void testBuild_SingleStep_List() {
		List<IPipelineStep<String, String>> pipelineSteps = Collections
				.singletonList(createBasicStep("Step1"));

		Pipeline<String, String> pipeline = Pipeline.build(pipelineSteps);

		Assert.assertEquals(1, pipeline.steps.size());
		Assert.assertEquals("step1", pipeline.steps.get(0).getStepIdentifier());

	}

	public void testBuild_MultipleStep_List() {
		IPipelineStep<String, String> step1 = createBasicStep("step1");
		IPipelineStep<String, String> step2 = createBasicStep("step2");
		IPipelineStep<String, String> step3 = createBasicStep("step3");

		List<IPipelineStep<String, String>> pipelineSteps = new ArrayList<IPipelineStep<String, String>>();
		pipelineSteps.add(step1);
		pipelineSteps.add(step2);
		pipelineSteps.add(step3);

		Pipeline<String, String> pipeline = Pipeline.build(pipelineSteps);

		Assert.assertEquals(3, pipeline.steps.size());
		Assert.assertEquals("step1", pipeline.steps.get(0).getStepIdentifier());
		Assert.assertEquals("step2", pipeline.steps.get(1).getStepIdentifier());
		Assert.assertEquals("step3", pipeline.steps.get(2).getStepIdentifier());
	}

	private IPipelineStep<String, String> createBasicStep(String stepId) {
		return new BasicStep(stepId);
	}

	class BasicStep implements IPipelineStep<String, String> {

		private String stepId;

		BasicStep(String stepId) {
			this.stepId = stepId;
		}

		public String getStepIdentifier() {

			return stepId;

		}

		public void process(Map<String, String> pipelineContext,
				NotificationMonitor monitor, IExecutionContext executionContext) {
			// TODO Auto-generated method stub

		}

	}

}
