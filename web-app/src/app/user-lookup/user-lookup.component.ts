import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from '../shared/models/user.model';
import {NgxDatatableSort} from '../shared/models/ngx-datatable-sort.model';
import {UserService} from '../services/user.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-user-lookup',
  templateUrl: './user-lookup.component.html',
  styleUrls: ['./user-lookup.component.scss']
})
export class UserLookupComponent implements OnInit {
  members$: Observable<Array<User>>;
  @Input() currentSorts: Array<NgxDatatableSort>;
  @Output() onTableSorted: EventEmitter<NgxDatatableSort> = new EventEmitter();
  @Output() onDeleteUser: EventEmitter<void> = new EventEmitter();

  constructor(private _userService: UserService) {
  }

  ngOnInit() {
    this._loadAllUsers();
  }

  /**
   * This method is used by the grid sorting, and is required, due to how ngx datatable works
   */
  sort(sortObj: { sorts: Array<NgxDatatableSort> }) {
    if (sortObj.sorts.length && sortObj.sorts[0].prop) {
      this.onTableSorted.emit(sortObj.sorts[0]);
    }
  }

  deleteUser(userId: string) {
    this._userService.deleteUser(userId)
    .subscribe(() => {
      this._loadAllUsers();
    });
  }

  private _loadAllUsers() {
    this.members$ = this._userService.getAllUsers();
  }

}
