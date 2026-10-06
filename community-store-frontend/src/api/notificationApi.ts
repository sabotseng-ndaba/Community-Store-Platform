import { apiDelete, apiGet, apiPost, apiPut } from './client';
import type { Notification } from '../types/notification';

const NOTIFICATION_PATH = '/CommunityStore/notification';

export const notificationApi = {
  create(notification: Notification): Promise<Notification> {
    return apiPost<Notification>(
      `${NOTIFICATION_PATH}/create`,
      notification
    );
  },

  getById(notificationId: number): Promise<Notification> {
    return apiGet<Notification>(
      `${NOTIFICATION_PATH}/read/${notificationId}`
    );
  },

  update(notification: Notification): Promise<Notification> {
    return apiPut<Notification>(
      `${NOTIFICATION_PATH}/update`,
      notification
    );
  },

  delete(notificationId: number): Promise<boolean> {
    return apiDelete<boolean>(
      `${NOTIFICATION_PATH}/delete/${notificationId}`
    );
  },

  getAll(): Promise<Notification[]> {
    return apiGet<Notification[]>(
      `${NOTIFICATION_PATH}/getAll`
    );
  },

  getByUser(userId: number): Promise<Notification[]> {
    return apiGet<Notification[]>(
      `${NOTIFICATION_PATH}/getByUser/${userId}`
    );
  },
};