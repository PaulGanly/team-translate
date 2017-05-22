import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { FormBuilder, Validators } from '@angular/forms';
import { UploadService } from './upload.service'


@Component({
  selector: 'app-upload-form',
  templateUrl: './upload-form.component.html',
  styleUrls: ['./upload-form.component.css'],
  providers: [UploadService]
})
export class UploadFormComponent implements OnInit {

  searchResult: Observable<string[]>;
 
  constructor(private uploadService: UploadService, public fb: FormBuilder) { }

  ngOnInit() {
  }

  public uploadForm = this.fb.group({
    uploadLanguage: ['', Validators.required],
    fileToTranslate: ['', Validators.required]
  });

  doUpload() {
    console.log('In do upload');
    this.uploadService.uploadFile(this.uploadForm.controls.uploadLanguage.value, this.uploadForm.controls.fileToTranslate).subscribe();
  };

}
