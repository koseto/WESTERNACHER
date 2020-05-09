import {Inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {IUser, User} from '../shared/models/user.model';
import {map} from 'rxjs/operators';
import {IResponseDTO} from '../shared/interfaces/IResponseDTO';
import {API_PREFIX} from '../app.module';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(@Inject(API_PREFIX) private _API_PREFIX: string, private _http: HttpClient) {
  }

  getAllUsers(sortBy?: string, sortDirection?: string): Observable<Array<IUser>> {
    let params: HttpParams = new HttpParams();

    if (sortBy) {
      params = params.set('sortBy', sortBy);
    }
    if (sortDirection) {
      params = params.set('sortDirection', sortDirection);
    }

    return this._http
    .get<IResponseDTO<Array<IUser>>>(`${this._API_PREFIX}/users`, {params: params})
    .pipe(map((response) => response.result.map((user) => new User(user))));
  }

  createUser(newUser: IUser) {
    return this._http
    .post(`${this._API_PREFIX}/users`, [newUser]);
  }

  deleteUser(userId: string) {
    return this._http
    .delete(`${this._API_PREFIX}/users/${userId}`);
  }
}
