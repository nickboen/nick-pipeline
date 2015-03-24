package org.gradle.example;

import java.util.Map;

import org.gradle.IExecutionContext;
import org.gradle.Notification;
import org.gradle.Notification.NotificationTypeEnum;
import org.gradle.NotificationMonitor;

public class SkuLookupStep extends AbstractStep<TestPipelineEnum, Object> {

	public String getStepIdentifier() {
		return "SkuLookupStep";
	}

	@Override
	boolean validateInput(Map<TestPipelineEnum, Object> pipelineContext,
			NotificationMonitor monitor) {

		// Assume sku existance is verified in the ValidationStep

		return true;
	}

	@Override
	void executeLogic(Map<TestPipelineEnum, Object> pipelineContext,
			NotificationMonitor monitor, IExecutionContext executionContext) {

		Product product = (Product) pipelineContext.get(TestPipelineEnum.INPUT);

		monitor.addNotification(new Notification(getStepIdentifier(),
				NotificationTypeEnum.INFO, "Looking up sku to match.  sku: "
						+ product.getSku()));

		// Fake logic to lookup the sku
		Sku sku = new Sku();
		sku.setSkuId(product.getSku());
		sku.setPrice(12.99);

		pipelineContext.put(TestPipelineEnum.SKU_DETAILS, sku);
	}

}
