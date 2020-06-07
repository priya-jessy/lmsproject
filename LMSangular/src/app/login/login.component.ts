import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../library.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RoutingguardService } from '../routingguard.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  error: String;
  message: String;
  constructor(private routingGuardService: RoutingguardService, private route: ActivatedRoute, private libraryService: LibraryService, private router: Router) { }

  ngOnInit(): void {
  }
  loginUser(form: NgForm) {
    this.libraryService.login(form.value).subscribe(response => {
      console.log(response);
      if (response.error) {
        this.error = response.message;
        setTimeout(() => {
          this.error = null;
        }, 5000);
      } else {
        form.reset();
        this.routingGuardService.setTempSession();
        if (response.libaryUserBean.role == 'user') {
          localStorage.setItem('userDetails',JSON.stringify(response.libaryUserBean));
          localStorage.setItem('role', response.libaryUserBean.role);
          this.router.navigateByUrl('/user');
        } else if (response.libaryUserBean.role == 'admin') {
          localStorage.setItem('role', response.libaryUserBean.role);
          this.router.navigateByUrl('/admin');
        } else {
          this.message=response.message;
          setTimeout(() => {
            this.error = null;
          }, 5000);

        }




      }


    });
  }

}
