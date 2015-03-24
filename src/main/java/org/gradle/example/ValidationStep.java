package org.gradle.example;

import java.util.Map;

import org.gradle.IPipelineStep;
import org.gradle.Notification;
import org.gradle.Notification.NotificationTypeEnum;
import org.gradle.NotificationMonitor;

public class ValidationStep implements IPipelineStep<TestPipelineEnum, Object> {

	public String getStepIdentifier() {
		return "ValidationStep";
	}

	public void process(Map<TestPipelineEnum, Object> pipelineContext,
			NotificationMonitor monitor) {

		Product product = (Product) pipelineContext.get(TestPipelineEnum.INPUT);
		
		monitor.addNotification(new Notification(getStepIdentifier(),
				NotificationTypeEnum.INFO, "Validating sku " + product.getSku()));
		

		if (product.getSku().equalsIgnoreCase("sku1")) {
			monitor.addNotification(new Notification(getStepIdentifier(),
					NotificationTypeEnum.ERROR, "This is an invalid sku"));
		}

	}

}
