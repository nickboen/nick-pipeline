package org.gradle;

import java.io.Serializable;

public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	String stepIdentifier;

	NotificationTypeEnum notificationType;

	String message;

	public Notification(String stepIdentifier,
			NotificationTypeEnum notificationType, String message) {
		this.stepIdentifier = stepIdentifier;
		this.notificationType = notificationType;
		this.message = message;
	}

	public String getStepIdentifier() {
		return stepIdentifier;
	}

	public NotificationTypeEnum getNotificationType() {
		return notificationType;
	}

	public String getMessage() {
		return message;
	}

	public enum NotificationTypeEnum {
		INFO, WARNING, ERROR
	}

}
