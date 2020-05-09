import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HelperService {

  constructor() {
  }

  /**
   * Formats date from mm/dd/yyyy to yyyy-mm-dd
   */
  formatDate(uiDate: string) {
    return uiDate ? uiDate.replace(/(\d\d)\/(\d\d)\/(\d{4})/, '$3-$1-$2') : '';
  }
}
