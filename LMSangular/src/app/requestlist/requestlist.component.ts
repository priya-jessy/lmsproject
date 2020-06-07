import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../library.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-requestlist',
  templateUrl: './requestlist.component.html',
  styleUrls: ['./requestlist.component.css']
})
export class RequestlistComponent implements OnInit {

  message:String;
  requests;
  isAdminLogin :boolean;

  constructor(private libraryService:LibraryService,private router:Router) {
   this.getRequests();
   this.isAdminLogin = libraryService.isAdmin();
   }

  getRequests(){
    return this.libraryService.getRequests().subscribe(response => {
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
 

  ngOnInit(): void {
  }

}
