import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../library.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-bookissue',
  templateUrl: './bookissue.component.html',
  styleUrls: ['./bookissue.component.css']
})
export class BookissueComponent implements OnInit {

  message: string;


  constructor(private libraryService: LibraryService) { }
  
  postIssue(form: NgForm){
    this.libraryService.postIssue(form.value).subscribe(response => {
      console.log(response);
      form.reset();
      if (!response.error) {
        this.message = 'Book Issued Successfully';
      }else{
        this.message='Book cannot be Issued';
      }
    }, error => {
      console.log(error);
    });
  
  }
    ngOnInit(): void {  }
  

}
