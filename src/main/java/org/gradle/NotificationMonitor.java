package org.gradle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gradle.Notification.NotificationTypeEnum;

public class NotificationMonitor {

	List<Notification> notifications = new ArrayList<Notification>();

	public void addNotification(Notification notification) {
		notifications.add(notification);
	}

	public List<Notification> getNotifications() {
		return Collections.unmodifiableList(notifications);
	}

	public boolean hasErrorMessages() {

		return hasMessageType(NotificationTypeEnum.ERROR);
	}

	public List<Notification> getErrorMessages() {

		return getMessagesOfType(NotificationTypeEnum.ERROR);

	}

	public boolean hasWarningMessages() {

		return hasMessageType(NotificationTypeEnum.WARNING);

	}

	public List<Notification> getWarningMessages() {

		return getMessagesOfType(NotificationTypeEnum.WARNING);

	}

	public boolean hasInfoMessages() {

		return hasMessageType(NotificationTypeEnum.INFO);

	}

	public List<Notification> getInfoMessages() {

		return getMessagesOfType(NotificationTypeEnum.INFO);

	}

	private boolean hasMessageType(NotificationTypeEnum type) {

		for (Notification notification : notifications) {
			if (type.equals(notification.getNotificationType())) {
				return true;
			}
		}

		return false;
	}

	private List<Notification> getMessagesOfType(NotificationTypeEnum type) {

		if (hasMessageType(type)) {
			List<Notification> notifs = new ArrayList<Notification>();

			for (Notification notification : notifications) {
				if (type.equals(notification.getNotificationType())) {
					notifs.add(notification);
				}
			}

			return Collections.unmodifiableList(notifs);
		}

		return null;
	}
}
