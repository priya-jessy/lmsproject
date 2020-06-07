import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../library.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-userslist',
  templateUrl: './userslist.component.html',
  styleUrls: ['./userslist.component.css']
})
export class UserslistComponent implements OnInit {

  
  message:String;
  users;

  constructor(private libraryService:LibraryService,private router:Router) {
  this.getUsers();
   }

  getUsers(){
    return this.libraryService.getUserData().subscribe(response => {
      console.log(response);
      this.users = response.userList;
    }, error => {
      console.log(error);
    })
  }
 
  ngOnInit(): void {
  }

}
