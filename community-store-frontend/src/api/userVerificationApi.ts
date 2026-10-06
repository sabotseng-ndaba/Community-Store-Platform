import { apiDelete, apiGet, apiPost, apiPut } from './client';
import type { UserVerification } from '../types/userVerification';

const VERIFICATION_PATH = '/CommunityStore/user-verifications';

export const userVerificationApi = {
  create(
    verification: UserVerification
  ): Promise<UserVerification> {
    return apiPost<UserVerification>(
      `${VERIFICATION_PATH}/create`,
      verification
    );
  },

  update(
    verification: UserVerification
  ): Promise<UserVerification> {
    return apiPut<UserVerification>(
      `${VERIFICATION_PATH}/update`,
      verification
    );
  },

  getById(
    verificationId: number
  ): Promise<UserVerification> {
    return apiGet<UserVerification>(
      `${VERIFICATION_PATH}/${verificationId}`
    );
  },

  getAll(): Promise<UserVerification[]> {
    return apiGet<UserVerification[]>(
      `${VERIFICATION_PATH}/all`
    );
  },

  getByType(
    type: string
  ): Promise<UserVerification[]> {
    return apiGet<UserVerification[]>(
      `${VERIFICATION_PATH}/type/${encodeURIComponent(type)}`
    );
  },

  getByStatus(
    status: string
  ): Promise<UserVerification[]> {
    return apiGet<UserVerification[]>(
      `${VERIFICATION_PATH}/status/${encodeURIComponent(status)}`
    );
  },

  delete(
    verificationId: number
  ): Promise<void> {
    return apiDelete<void>(
      `${VERIFICATION_PATH}/delete/${verificationId}`
    );
  },
};