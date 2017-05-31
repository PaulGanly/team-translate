import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { FormBuilder, Validators } from '@angular/forms';
import { UploadService } from './upload.service';
import { Router } from '@angular/router';
import { UploadResponse } from '../model/models';


@Component({
  selector: 'app-upload-form',
  templateUrl: './upload-form.component.html',
  styleUrls: ['./upload-form.component.css']
})
export class UploadFormComponent implements OnInit {
  fileToUpload: any;
  uploadResult: UploadResponse;

  public uploadForm = this.fb.group({
    uploadLanguage: ['ENGLISH', Validators.required],
    fileToTranslate: ['', Validators.required],
  });

  constructor(private uploadService: UploadService, public fb: FormBuilder, private router: Router) { }

  ngOnInit() {
  }

  doUpload(event) {
    const file = event.srcElement.getElementsByClassName('fileInput')[0].files[0];
    this.uploadService
      .uploadFile(this.uploadForm.controls.uploadLanguage.value, file)
      .subscribe((result) => {
            this.uploadService.uploadResult = result;
            this.router.navigate(['/uploadresults']);
          }
      );
  };

}
