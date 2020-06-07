import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactusComponent } from './contactus/contactus.component';
import { LoginComponent } from './login/login.component';
import { AddbookComponent } from './addbook/addbook.component';
import { BooklistComponent } from './booklist/booklist.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { RegisterComponent } from './register/register.component';
import { Router } from '@angular/router';
import { UserslistComponent } from './userslist/userslist.component';
import { RequestlistComponent } from './requestlist/requestlist.component';
import { BookrequestComponent } from './bookrequest/bookrequest.component';
import { BookissueComponent } from './bookissue/bookissue.component';
import { BookreturnComponent } from './bookreturn/bookreturn.component';
import { BookreceiveComponent } from './bookreceive/bookreceive.component';
import { DeletebookComponent } from './deletebook/deletebook.component';
import { UserloginComponent } from './userlogin/userlogin.component';
import { UpdatebookComponent } from './updatebook/updatebook.component';
import { SearchBookPipe } from './search-book.pipe';
import { RequestedbooksComponent } from './requestedbooks/requestedbooks.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    AboutComponent,
    ContactusComponent,
    LoginComponent,
    AddbookComponent,
    BooklistComponent,
    UserComponent,
    AdminComponent,
    RegisterComponent,
    UserslistComponent,
    RequestlistComponent,
    BookrequestComponent,
    BookissueComponent,
    BookreturnComponent,
    BookreceiveComponent,
    DeletebookComponent,
    UserloginComponent,
    UpdatebookComponent,
    SearchBookPipe,
    RequestedbooksComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
