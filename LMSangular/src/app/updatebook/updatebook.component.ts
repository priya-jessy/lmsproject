import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LibraryService } from '../library.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-updatebook',
  templateUrl: './updatebook.component.html',
  styleUrls: ['./updatebook.component.css']
})
export class UpdatebookComponent implements OnInit {
  message: String;
  updateBook;
  isAvaliable: boolean;
  bookId: Number;
  author: String;
  bookName : String;
  publisher : String;
  category : String;
  
  constructor(private activatedRoute: ActivatedRoute, private libraryService: LibraryService ) {
    this.activatedRoute.queryParams.subscribe(data => {
      console.log('Book To be Updated', data);
      this.updateBook = data;
      this.isAvaliable = data.isAvaliable;

    });
  }


  ngOnInit(): void {
    this.bookId = this.updateBook.bookId;
    this.isAvaliable = this.updateBook.isAvaliable;
    this.author = this.updateBook.author;
    this.bookName = this.updateBook.bookName;
    this.publisher = this.updateBook.publisher;
    this.category = this.updateBook.category;
  }


  postUpdate(form: NgForm){
    this.libraryService.postUpdateBook(form.value).subscribe(response => {
      console.log(response); 
      if (!response.error) {
        this.message = 'Book Updated Successfully';   
      }else{
        this.message="Book can't be updated";
      }
    }, error => {
      console.log(error);
    });
  
  }

}
