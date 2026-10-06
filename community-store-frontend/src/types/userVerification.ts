export interface UserVerification {
  verificationId: number;
  verificationType: string;
  verificationStatus: string;
  verificationDocument: string;
  verifiedAt: string | null;
}