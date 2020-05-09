export class BaseDTO {
  id?: string;

  constructor(rawObject: IBaseDTOInput) {
    if (rawObject) {
      this.id = rawObject.id;
    }
  }
}

export interface IBaseDTOInput {
  id?: string;
}
