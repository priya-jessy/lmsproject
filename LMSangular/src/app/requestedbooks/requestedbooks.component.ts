import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LibraryService } from '../library.service';

@Component({
  selector: 'app-requestedbooks',
  templateUrl: './requestedbooks.component.html',
  styleUrls: ['./requestedbooks.component.css']
})
export class RequestedbooksComponent implements OnInit {
  message: String;
  requests;
  issueDetails;
  isAdminLogin:boolean;
  searchText;
  fieldName = "rId";
  constructor(private libraryService: LibraryService) {
    this.getRequests();
  }

  getRequests() {
    this.libraryService.getRequestedBooks().subscribe(response => {
      console.log(response);
      this.requests = response.requestList;
    }, error => {
      console.log(error);
    })

  }
  issueBook(requests) {
    this.libraryService.postIssue(requests).subscribe(response => {
      console.log(response);
      if (response.error) {
        this.message = 'Book cannot be Issued';
      } else {
        this.getRequests();
        this.message = 'Book Issued Successfully';
      }
      setTimeout(() => {
        this.message = null;
      }, 5000);
    });


  }

  ngOnInit(): void {
  }

}
