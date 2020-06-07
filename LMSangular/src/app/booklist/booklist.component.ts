import { Component, OnInit } from '@angular/core';

import { LibraryService } from '../library.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-booklist',
  templateUrl: './booklist.component.html',
  styleUrls: ['./booklist.component.css']
})
export class BooklistComponent implements OnInit {

  message: String;
  books;
  isAdminLogin: boolean;
  isUserLogin: boolean;
  request: RequestInfo;
  searchText;
  fieldName = "bookName";

  constructor(private libraryService: LibraryService, private router: Router) {
    this.getBooks();
    this.isAdminLogin = libraryService.isAdmin();
    this.isUserLogin = libraryService.isUser();
  }

  getBooks() {
    return this.libraryService.getData().subscribe(response => {
      console.log(response);
      this.books = response.booksList;
    }, error => {
      console.log(error);
    })
  }
  deleteBook(book) {
    this.libraryService.deleteBook(book).subscribe(response => {
      console.log(response);
      if (!response.error) {
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

  updateBook(book) {
    this.router.navigate(['/updatebook'], { queryParams: book });
    
  }
  postReq(request) {
    console.log(request.bookId);

    this.libraryService.postRequest(request).subscribe(response => {

      console.log(response);
      if (!response.error) {
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
  postReturnBook(books) {
    this.libraryService.postReturn(books).subscribe(response => {
      console.log(response);
      if (!response.error) {
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
class RequestInfo {
  id: String;
  bookId: String;
}
