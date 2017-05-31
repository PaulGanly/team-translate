import { Component, OnInit, EventEmitter } from '@angular/core';

import {MaterializeDirective,MaterializeAction} from 'angular2-materialize';
import { ActivatedRoute } from '@angular/router';
import { UploadResponse, UnmatchedPhrase } from '../../model/models';
import { UploadService } from '../upload.service';


@Component({
  selector: 'app-upload-result',
  templateUrl: './upload-result.component.html',
  styleUrls: ['./upload-result.component.css']
})
export class UploadResultComponent implements OnInit {
  selectedResult: UnmatchedPhrase;
  unmatchedTableRows = new Array<UnmatchedPhrase>();
  allSelected: boolean;

  inputModalActions = new EventEmitter<string|MaterializeAction>();
  viewSimilarModalActions = new EventEmitter<string|MaterializeAction>();

  findSimilarPhrases(unmatchedPhrase) {
    const res = <UnmatchedPhrase>{};
    res.phrase = unmatchedPhrase;
    this.uploadService.searchForViewSimilar(unmatchedPhrase)
                      .subscribe((result) => {res.closeMatches = result; this.openViewSimilarModal(res); });
  }

  openViewSimilarModal(selectedPhrase) {
    this.selectedResult = selectedPhrase;
    this.viewSimilarModalActions.emit({action: 'modal' , params: ['open']});
  }

  closeViewSimilarModal() {
    this.viewSimilarModalActions.emit({action: 'modal' , params: ['close']});
  }

  downloadTranslatedFile() {
    this.uploadService.downloadTranslatedFile().subscribe();
  }

  mailSelectedPhrasesForTranslation(event) {
    event.srcElement.href = 'mailto:translationsTeam@revenue.ie?subject=Translations%20Request&body=';
    event.srcElement.href += this.getMessage();
  }

  getMessage() {
    const space = '%20';
    const newLine = '%0D%0A';
    const greeting = 'Hi' + space + 'all,' + newLine +
                     newLine + 'Could' + space + 'I' + space + 'please' + space + 'get' + space + 'translations' +
                     space + 'for' + space + 'the' + space + 'following' + space + 'phrases%3A' + newLine + '';
    const closing = '' + newLine + 'Regards,' + newLine + newLine + '%5BINPUT_NAME%5D';
    return greeting + newLine + this.getMessageBody() + closing;
  }

  getMessageBody(){
    const tab = '%20%20%20%20';
    const newLine = '%0D%0A';
    const colon = '%3A';
    let body = '';

    const numberUnmatched = this.unmatchedTableRows.length;
    for (let i = 0; i < numberUnmatched; i++) {
      if (this.unmatchedTableRows[i].isSelectedForEmail) {
        body += tab + this.unmatchedTableRows[i].phrase + tab + colon + newLine;
      }
    }
    return body;
  }

  selectAll(bool) {
    this.allSelected = bool;
    for (const row of this.unmatchedTableRows) {
      row.isSelectedForEmail = bool;
    }
  }

  constructor(private uploadService: UploadService) {
  }

  ngOnInit() {
    const numberUnmatched = this.uploadService.uploadResult.unmatchedPhrases.length;
    for (let i = 0; i < numberUnmatched; i++) {
      const tableRow = <UnmatchedPhrase>{};
      tableRow.isSelectedForEmail = true;
      tableRow.phrase = this.uploadService.uploadResult.unmatchedPhrases[i];
      this.unmatchedTableRows.push(tableRow);
    }
    this.selectAll(true);
    this.allSelected = true;
  }

}
