import {BrowserModule} from '@angular/platform-browser';
import {InjectionToken, NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';
import {UserLookupComponent} from './user-lookup/user-lookup.component';
import {AppRoutingModule} from './app-routing.module';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {HttpClientModule} from '@angular/common/http';
import {CreateUserComponent} from './create-user/create-user.component';
import {ReactiveFormsModule} from '@angular/forms';
import {ToastNoAnimation, ToastNoAnimationModule, ToastrModule} from 'ngx-toastr';
import {InfoModalComponent} from './info-modal/info-modal.component';

export const API_PREFIX = new InjectionToken('API_PREFIX');

@NgModule({
  declarations: [
    AppComponent,
    UserLookupComponent,
    CreateUserComponent,
    InfoModalComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule,
    NgxDatatableModule,
    ReactiveFormsModule,
    ToastNoAnimationModule,
    ToastrModule.forRoot({
      toastComponent: ToastNoAnimation,
      timeOut: 10000,
      tapToDismiss: false,
      closeButton: true
    })
  ],
  providers: [
    {provide: API_PREFIX, useValue: 'api/v1'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
