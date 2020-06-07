import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../library.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-bookreturn',
  templateUrl: './bookreturn.component.html',
  styleUrls: ['./bookreturn.component.css']
})
export class BookreturnComponent implements OnInit {
  message: string;

  constructor(private libraryService: LibraryService) { }
  
  postReturn(form: NgForm){
    this.libraryService.postReturn(form.value).subscribe(response => {
      console.log(response);
      form.reset();
      if (!response.error) {
        this.message = 'Book Retunrned to admin Successfully';
      }else{
        this.message='Book Cannot be returned';
      }
    }, error => {
      console.log(error);
    });
  
  }
    ngOnInit(): void {  }

}
