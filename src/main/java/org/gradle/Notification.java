package org.gradle;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.MULTI_LINE_STYLE);

		sb.append("stepIdentifier", stepIdentifier);
		sb.append("notificationType", notificationType);
		sb.append("message", message);

		return sb.toString();
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
