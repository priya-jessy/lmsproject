import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LibraryService } from '../library.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  message:string;

  constructor(private libraryService:LibraryService) { }

  postUser(form:NgForm){
    this.libraryService.postData(form.value).subscribe(response => {
      console.log(response);
      if(!response.error) {
        this.message = response.message;
        setTimeout(() => {
          this.message = null;
        }, 4000);
      } else {
        this.message = response.message;
        setTimeout(() => {
          this.message = null;
        }, 4000);
      }
    }, error => {
      console.log(error);
    });
  }
  ngOnInit(): void {   
  }

}
