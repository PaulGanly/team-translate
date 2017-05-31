import { Component, OnInit } from '@angular/core';
import { InputService } from './input.service'
import { Observable } from 'rxjs/Observable';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-input-form',
  templateUrl: './input-form.component.html',
  styleUrls: ['./input-form.component.css'],
  providers: [InputService]
})
export class InputFormComponent implements OnInit {

  public inputForm = this.fb.group({
    english: ['', Validators.required],
    irish: ['', Validators.required],
    context: ['', Validators.required]
  });

  constructor(private inputService: InputService, public fb: FormBuilder, private router: Router) { }

  ngOnInit() {
  }

  doInput() {
    this.inputService
      .inputTranslationWithForm(this.inputForm.controls.english.value,
                                this.inputForm.controls.irish.value,
                                this.inputForm.controls.context.value)
                                .subscribe((result) => {console.log(result); this.router.navigate(['/confirminput']); });
  };

}
