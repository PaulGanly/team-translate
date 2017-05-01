import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { InputFormComponent } from './input-form/input-form.component';
import { SearchFormComponent } from './search-form/search-form.component';
import { SearchResultsComponent } from './search-form/search-results/search-results.component';
import { UploadFormComponent } from './upload-form/upload-form.component';
import { UploadResultComponent } from './upload-form/upload-result/upload-result.component';

const routes: Routes = [
  { path: '', redirectTo: '/search', pathMatch: 'full' },
  { path: 'search',  component: SearchFormComponent },
  { path: 'input', component: InputFormComponent },
  { path: 'upload',     component: UploadFormComponent },
  { path: 'uploadresults', component: UploadResultComponent }
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
