import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LibraryService } from '../library.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn : boolean;
isNotLoggedIn: boolean;

  constructor(private router:Router, public libraryService:LibraryService) {
    this.isLoggedIn = this.libraryService.isLoggedIn();
    this.isNotLoggedIn = this.libraryService.isNotLoggedIn();
   }

   
  ngOnInit(): void {
    
  }

  logoutClick(){
    localStorage.clear();
    //this.router.navigate(["login"]);
    
    // this.(route)/login
  }

}


