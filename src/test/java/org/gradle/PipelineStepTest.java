package org.gradle;

import java.util.HashMap;
import java.util.Map;

import org.gradle.Notification.NotificationTypeEnum;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PipelineStepTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		SimpleStep step = new SimpleStep();
		Map<Integer, Integer> pipelineContext = new HashMap<Integer, Integer>();
		NotificationMonitor monitor = new NotificationMonitor();
		pipelineContext.put(1, 5);

		Assert.assertEquals("SimpleStep", step.getStepIdentifier());

		ExecutionContext executionContext = new ExecutionContext();
		step.process(pipelineContext, monitor, executionContext);

		Assert.assertEquals(Integer.valueOf(22), pipelineContext.get(1));
		Assert.assertEquals(1, monitor.getNotifications().size());

	}

	class SimpleStep implements IPipelineStep<Integer, Integer> {

		public String getStepIdentifier() {
			return getClass().getSimpleName();
		}

		public void process(Map<Integer, Integer> pipelineContext,
				NotificationMonitor monitor, IExecutionContext executionContext) {
			pipelineContext.put(1, 22);

			monitor.addNotification(new Notification("TestStep",
					NotificationTypeEnum.INFO, "Sample Message"));

		}

	}

}
