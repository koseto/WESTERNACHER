import {BaseDTO, IBaseDTOInput} from './base-dto.model';

export class User extends BaseDTO {
  firstName: string;
  lastName: string;
  email: string;
  dateOfBirth: string;

  constructor(rawObject: IUser) {
    super(rawObject);
    if (rawObject) {
      this.firstName = rawObject.firstName;
      this.lastName = rawObject.lastName;
      this.email = rawObject.email;
      this.dateOfBirth = rawObject.dateOfBirth;
    }
  }
}

export interface IUser extends IBaseDTOInput {
  firstName: string;
  lastName: string;
  email: string;
  dateOfBirth: string;
}
