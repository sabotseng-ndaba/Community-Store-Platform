import { apiDelete, apiGet, apiPost, apiPut } from './client';
import type { User } from '../types/user';

const USER_PATH = '/CommunityStore/users';

export const userApi = {
  create(user: User): Promise<User> {
    return apiPost<User>(USER_PATH, user);
  },

  getAll(): Promise<User[]> {
    return apiGet<User[]>(USER_PATH);
  },

  getById(userId: string): Promise<User> {
    return apiGet<User>(
      `${USER_PATH}/${encodeURIComponent(userId)}`
    );
  },

  findByFirstName(firstName: string): Promise<User[]> {
    return apiGet<User[]>(
      `${USER_PATH}/firstName/${encodeURIComponent(firstName)}`
    );
  },

  findByLastName(lastName: string): Promise<User[]> {
    return apiGet<User[]>(
      `${USER_PATH}/lastName/${encodeURIComponent(lastName)}`
    );
  },

  update(user: User): Promise<User> {
    return apiPut<User>(USER_PATH, user);
  },

  delete(userId: string): Promise<void> {
    return apiDelete<void>(
      `${USER_PATH}/${encodeURIComponent(userId)}`
    );
  },
};