import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserLookupComponent} from './user-lookup/user-lookup.component';

const routes: Routes = [
  {
    path: 'users',
    component: UserLookupComponent
  },
  {
    path: '**',
    redirectTo: '/users',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
