import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { MaterializeModule } from 'angular2-materialize';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { InputFormComponent } from './input-form/input-form.component';
import { SearchFormComponent } from './search-form/search-form.component';
import { SearchResultsComponent } from './search-form/search-results/search-results.component';
import { UploadFormComponent } from './upload-form/upload-form.component';
import { UploadResultComponent } from './upload-form/upload-result/upload-result.component';
import { SearchService } from './search-form/search.service';
import { InputService } from './input-form/input.service';
import { UploadService } from './upload-form/upload.service';
import { ReactiveFormsModule } from '@angular/forms';
import { InputConfirmComponent } from './input-form/input-confirm/input-confirm.component';


@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    InputFormComponent,
    SearchFormComponent,
    SearchResultsComponent,
    UploadFormComponent,
    UploadResultComponent,
    InputConfirmComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    MaterializeModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [SearchService, InputService, UploadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
