package org.gradle.example;

import java.util.Map;

import org.gradle.IExecutionContext;
import org.gradle.Notification;
import org.gradle.Notification.NotificationTypeEnum;
import org.gradle.NotificationMonitor;

public class ValidationStep extends AbstractStep<TestPipelineEnum, Object> {

	public String getStepIdentifier() {
		return "ValidationStep";
	}

	@Override
	boolean validateInput(Map<TestPipelineEnum, Object> pipelineContext,
			NotificationMonitor monitor) {

		// Verify the input object was populated
		if (!pipelineContext.containsKey(TestPipelineEnum.INPUT)) {
			monitor.addNotification(new Notification(getStepIdentifier(),
					NotificationTypeEnum.ERROR, "Step requires "
							+ TestPipelineEnum.INPUT + " of type Product.  "
							+ TestPipelineEnum.INPUT + " was not populated"));
			return false;
		}

		// Verify the input object is a product
		if (!pipelineContext.get(TestPipelineEnum.INPUT).getClass()
				.equals(Product.class)) {
			monitor.addNotification(new Notification(getStepIdentifier(),
					NotificationTypeEnum.ERROR, "Step requires "
							+ TestPipelineEnum.INPUT
							+ " of type Product.  "
							+ TestPipelineEnum.INPUT
							+ " was "
							+ pipelineContext.get(TestPipelineEnum.INPUT)
									.getClass()));
			return false;
		}

		return true;
	}

	@Override
	void executeLogic(Map<TestPipelineEnum, Object> pipelineContext,
			NotificationMonitor monitor, IExecutionContext executionContext) {

		Product product = (Product) pipelineContext.get(TestPipelineEnum.INPUT);
		
		monitor.addNotification(new Notification(getStepIdentifier(),
				NotificationTypeEnum.INFO, "Validating sku " + product.getSku()));
		

		if (product.getSku().equalsIgnoreCase("sku1")) {
			monitor.addNotification(new Notification(getStepIdentifier(),
					NotificationTypeEnum.ERROR, "This is an invalid sku"));

			executionContext.abortProcessing();
		} else if (product.getSku().equalsIgnoreCase("sku11")) {
			monitor.addNotification(new Notification(getStepIdentifier(),
					NotificationTypeEnum.INFO,
					"This is configured to be skipped.  sku: "
							+ product.getSku()));

			executionContext.processingComplete();

		} else {
			monitor.addNotification(new Notification(getStepIdentifier(),
					NotificationTypeEnum.INFO, "This is a valid sku.  sku: "
							+ product.getSku()));
		}

	}

}
