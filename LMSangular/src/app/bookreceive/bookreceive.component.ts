import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../library.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-bookreceive',
  templateUrl: './bookreceive.component.html',
  styleUrls: ['./bookreceive.component.css']
})
export class BookreceiveComponent implements OnInit {
  message: string;
  error: string;
  requests;
  isAdminLogin :boolean;
    constructor(private libraryService:LibraryService) {
      this.getReturnedBooks();
      this.isAdminLogin = libraryService.isAdmin();
     } 
  
     getReturnedBooks() {
      return this.libraryService.getReturnedBooks().subscribe(response => {
        console.log(response);
        this.requests = response.requestList;
      }, error => {
        console.log(error);
      })
    }
  postReceiveBook(requests){
      this.libraryService.postReceive(requests).subscribe(response => {
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
  ngOnInit(): void {  }


}
