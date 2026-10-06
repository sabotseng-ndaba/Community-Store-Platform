export interface Contact {
  phone: string;
  email: string;
  altPhone: string;
}

export interface Role {
  roleId: string;
  description: string;
  roleName: string;
}

export interface User {
  userId: string;
  firstName: string;
  lastName: string;
  role: Role;
  contact: Contact;
}