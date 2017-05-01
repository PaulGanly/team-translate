import { Component, OnInit } from '@angular/core';
import { SearchService } from './search.service';
import { FormBuilder, Validators } from '@angular/forms';

import { Observable } from 'rxjs/Observable';
import * as models from '../model/models';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css'],
  providers: [SearchService]
})
export class SearchFormComponent implements OnInit {
  searchResult: Observable<models.Match[]>;

  public searchForm = this.fb.group({
    searchLanguage: ["", Validators.required],
    searchTerm: ["", Validators.required]
  });

  constructor(private searchService: SearchService, public fb: FormBuilder) { }

  ngOnInit() { }

  onSubmit() {
    this.searchService.search(this.searchForm.controls.searchLanguage.value, this.searchForm.controls.searchTerm.value).subscribe();
  };

}
