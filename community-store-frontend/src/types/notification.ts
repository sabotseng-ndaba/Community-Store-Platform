export interface Notification {
  notificationId: number;
  userId: number;
  message: string;
  dateSent: string | null;
  status: string;
}