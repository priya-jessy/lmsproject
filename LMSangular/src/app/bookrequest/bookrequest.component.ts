import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../library.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-bookrequest',
  templateUrl: './bookrequest.component.html',
  styleUrls: ['./bookrequest.component.css']
})
export class BookrequestComponent implements OnInit {

  message: string;


constructor(private libraryService: LibraryService) { }

postReq(form: NgForm){
  this.libraryService.postRequest(form.value).subscribe(response => {
    console.log(response);
    form.reset();
    if (!response.error) {
      this.message = 'Book Request Placed Successfully';
    }else{
      this.message='Request not placed due to invalid bookid or userid'
    }
  }, error => {
    console.log(error);
  });

}
  ngOnInit(): void {  }

}
