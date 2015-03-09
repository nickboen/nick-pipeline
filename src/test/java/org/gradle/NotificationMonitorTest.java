package org.gradle;

import static org.junit.Assert.fail;

import org.gradle.Notification.NotificationTypeEnum;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NotificationMonitorTest {

	NotificationMonitor monitor = null;

	@Before
	public void setUp() throws Exception {
		monitor = new NotificationMonitor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddNotification() {

		Assert.assertEquals(0, monitor.getNotifications().size());

		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.INFO, null));

		Assert.assertEquals(1, monitor.getNotifications().size());
	}

	@Test
	public void testNotifications_CannotModifyList() {

		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.INFO, null));

		// Test modification of getNotifications
		try {
			monitor.getNotifications().remove(0);
			fail("Should not be allowed to modify the notification list");
		} catch (UnsupportedOperationException e) {
		}

	}

	@Test
	public void testErrors_CannotModifyList() {

		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.ERROR, null));

		// Test modification of getErrorMessages
		try {
			monitor.getErrorMessages().remove(0);
			fail("Should not be allowed to modify the error list");
		} catch (UnsupportedOperationException e) {
		}

	}

	@Test
	public void testWarnings_CannotModifyList() {

		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.WARNING, null));

		// Test modification of getWarningMessages
		try {
			monitor.getWarningMessages().remove(0);
			fail("Should not be allowed to modify the warning list");
		} catch (UnsupportedOperationException e) {
		}

	}

	@Test
	public void testInfos_CannotModifyList() {

		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.INFO, null));

		// Test modification of getInfoMessages
		try {
			monitor.getInfoMessages().remove(0);
			fail("Should not be allowed to modify the warning list");
		} catch (UnsupportedOperationException e) {
		}

	}

	@Test
	public void testHasErrorMessages_NoMessagesOfAnyKind() {

		Assert.assertFalse(monitor.hasWarningMessages());

	}

	@Test
	public void testHasErrorMessages_NoErrors() {

		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.INFO, null));

		Assert.assertFalse(monitor.hasErrorMessages());
	}

	@Test
	public void testHasErrorMessages_HasErrors() {

		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.INFO, null));
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.ERROR, null));

		Assert.assertTrue(monitor.hasErrorMessages());
	}

	@Test
	public void testGetErrorMessages_NoMessagesOfAnyKind() {
		Assert.assertNull(monitor.getErrorMessages());
	}

	@Test
	public void testGetErrorMessages_NoErrors() {
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.WARNING, null));
		Assert.assertNull(monitor.getErrorMessages());
	}

	@Test
	public void testGetErrorMessages_HasErrors() {
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.ERROR, null));
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.WARNING, null));
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.INFO, null));
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.ERROR, null));
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.ERROR, null));

		Assert.assertEquals(3, monitor.getErrorMessages().size());
	}

	@Test
	public void testHasWarningMessages_NoMessagesOfAnyKind() {

		Assert.assertFalse(monitor.hasWarningMessages());

	}

	@Test
	public void testHasWarningMessages_NoWarnings() {

		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.INFO, null));

		Assert.assertFalse(monitor.hasWarningMessages());
	}

	@Test
	public void testHasWarningMessages_HasWarnings() {

		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.INFO, null));
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.WARNING, null));

		Assert.assertTrue(monitor.hasWarningMessages());
	}

	@Test
	public void testGetWarningMessages_NoMessagesOfAnyKind() {
		Assert.assertNull(monitor.getWarningMessages());
	}

	@Test
	public void testGetWarningMessages_NoWarnings() {
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.ERROR, null));
		Assert.assertNull(monitor.getWarningMessages());
	}

	@Test
	public void testGetWarningMessages_HasWarnings() {
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.INFO, null));
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.WARNING, null));
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.WARNING, null));

		Assert.assertEquals(2, monitor.getWarningMessages().size());
	}

	@Test
	public void testHasInfoMessages_NoMessagesOfAnyKind() {

		Assert.assertFalse(monitor.hasInfoMessages());

	}

	@Test
	public void testHasInfoMessages_NoInfos() {

		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.WARNING, null));

		Assert.assertFalse(monitor.hasInfoMessages());
	}

	@Test
	public void testHasInfoMessages_HasInfos() {

		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.ERROR, null));
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.INFO, null));

		Assert.assertTrue(monitor.hasInfoMessages());
	}

	@Test
	public void testGetInfoMessages_NoMessagesOfAnyKind() {
		Assert.assertNull(monitor.getInfoMessages());
	}

	@Test
	public void testGetInfoMessages_NoInfos() {
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.WARNING, null));
		Assert.assertNull(monitor.getInfoMessages());
	}

	@Test
	public void testGetInfoMessages_HasInfos() {
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.ERROR, null));
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.WARNING, null));
		monitor.addNotification(new Notification("TestStep",
				NotificationTypeEnum.INFO, null));

		Assert.assertEquals(1, monitor.getInfoMessages().size());
	}

}
