import { Component, OnInit, EventEmitter } from '@angular/core';

import {MaterializeDirective,MaterializeAction} from 'angular2-materialize';
import { RESULTS, UploadResult} from './upload-results';

@Component({
  selector: 'app-upload-result',
  templateUrl: './upload-result.component.html',
  styleUrls: ['./upload-result.component.css']
})
export class UploadResultComponent implements OnInit {
  uploadResults = RESULTS;

  selectedResult = UploadResult;

  inputModalActions = new EventEmitter<string|MaterializeAction>();
  viewSimilarModalActions = new EventEmitter<string|MaterializeAction>();

  openInputModal(uploadResult) {
    this.selectedResult = uploadResult;
    this.inputModalActions.emit({action: 'modal' , params: ['open']});
  }
  closeInputModal() {
    this.inputModalActions.emit({action: 'modal' , params: ['close']});
  }
  openViewSimilarModal(uploadResult) {
    this.selectedResult = uploadResult;
    this.viewSimilarModalActions.emit({action: 'modal' , params: ['open']});
  }
  closeViewSimilarModal() {
    this.viewSimilarModalActions.emit({action: 'modal' , params: ['close']});
  }
  constructor() { }

  ngOnInit() {
  }

}
