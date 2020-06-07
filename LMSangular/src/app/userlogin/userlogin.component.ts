import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LibraryService } from '../library.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-userlogin',
  templateUrl: './userlogin.component.html',
  styleUrls: ['./userlogin.component.css']
})
export class UserloginComponent implements OnInit {
error:String;
  constructor(private route:ActivatedRoute,private libraryService:LibraryService,private router:Router) { }

  ngOnInit(): void {
  }
  loginUser(form:NgForm){
    this.libraryService.login(form.value).subscribe(response=>
      {
        console.log(response);
        if(response.error){
          this.error=response.message;
          setTimeout(()=>{
            this.error=null;
          },5000);
        }else{
          localStorage.setItem('userDetails',JSON.stringify(response));
        }
        form.reset();
        this.router.navigateByUrl('/user');

      });
  }


}
