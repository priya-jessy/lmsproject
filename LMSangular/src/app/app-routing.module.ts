import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ContactusComponent } from './contactus/contactus.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { AddbookComponent } from './addbook/addbook.component';
import { RegisterComponent } from './register/register.component';
import { BooklistComponent } from './booklist/booklist.component';
import { UserslistComponent } from './userslist/userslist.component';
import { RequestlistComponent } from './requestlist/requestlist.component';
import { BookrequestComponent } from './bookrequest/bookrequest.component';
import { BookissueComponent } from './bookissue/bookissue.component';
import { BookreturnComponent } from './bookreturn/bookreturn.component';
import { BookreceiveComponent } from './bookreceive/bookreceive.component';
import { DeletebookComponent } from './deletebook/deletebook.component';
import { UserloginComponent } from './userlogin/userlogin.component';
import { UserAuthGuardGuard } from './userauthguard.guard';
import { AdminAuthGuardGuard } from './adminauthguard.guard';
import { UpdatebookComponent } from './updatebook/updatebook.component';
import { RequestedbooksComponent } from './requestedbooks/requestedbooks.component';


const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'about',component:AboutComponent},
  {path:'contactus',component:ContactusComponent},
  {path:'login',component:LoginComponent},
  {path:'admin',component:AdminComponent,canActivate: [AdminAuthGuardGuard] },
  {path:'user',component:UserComponent,canActivate:[UserAuthGuardGuard] },
  {path:'addbook',component:AddbookComponent,canActivate: [AdminAuthGuardGuard] },
  {path:'register',component:RegisterComponent,canActivate: [AdminAuthGuardGuard] },
  {path:'booklist',component:BooklistComponent},
  {path:'userslist',component:UserslistComponent,canActivate: [AdminAuthGuardGuard] },
  {path:'requestlist',component:RequestlistComponent,canActivate: [AdminAuthGuardGuard] },
  {path:'bookrequest',component:BookrequestComponent,canActivate:[UserAuthGuardGuard] },
  {path:'bookissue',component:BookissueComponent,canActivate: [AdminAuthGuardGuard] },
  {path:'bookreturn',component:BookreturnComponent,canActivate:[UserAuthGuardGuard] },
  {path:'bookreceive',component:BookreceiveComponent,canActivate: [AdminAuthGuardGuard] },
  {path:'deletebook',component:DeletebookComponent,canActivate: [AdminAuthGuardGuard] },
  {path:'userlogin',component:UserloginComponent,canActivate:[UserAuthGuardGuard] },
  {path:'updatebook',component:UpdatebookComponent,canActivate: [AdminAuthGuardGuard]},
  {path:'requestedbooks',component:RequestedbooksComponent,canActivate:[AdminAuthGuardGuard]}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
