import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class LibraryService {
  lmsUrl="http://localhost:8080/libraryspring/";
  id;

  constructor(private http:HttpClient){}

  postData(isAdded){
    return this.http.post<any>(`${this.lmsUrl}addUser`,isAdded);
  }
  postBook(book){
    return this.http.post<any>(`${this.lmsUrl}addBook`,book);
  }
  getData(){
    return this.http.get<any>(`${this.lmsUrl}getAllBooks`);
  }
  getUserData(){
    return this.http.get<any>(`${this.lmsUrl}getAllUsers`);
  }
  login(userDetails){
    return this.http.post<any>(`${this.lmsUrl}login`,userDetails);
  }
  getRequests(){
    return this.http.get<any>(`${this.lmsUrl}getAllRequests`);
  }

  postRequest(request){
    console.log(request,this.getId());
    return this.http.post<any>(`${this.lmsUrl}bookRequest/${this.getId()}`,request);
  }
  postIssue(request){
    return this.http.post<any>(`${this.lmsUrl}issueBook`,request);
  }
  postReturn(request){
    
    return this.http.post<any>(`${this.lmsUrl}returnBook/${this.getId()}`,request);
  }
  postReceive(request){
    return this.http.post<any>(`${this.lmsUrl}receiveBook`,request);
  }
  deleteBook(book){
    return this.http.delete<any>(`http://localhost:8080/libraryspring/deleteBook/${book.bookId}`);
  }
  searchBook(book){
    return this.http.get<any>(`http://localhost:8080/libraryspring/searchBook/${book.bookId}`);
  }
  postUpdateBook(book){
    return this.http.post<any>(`${this.lmsUrl}updateBook`,book);
  }
  getRequestedBooks(){
    return this.http.get<any>(`${this.lmsUrl}getAllRequestedBooks`);
  }
  getReturnedBooks(){
    return this.http.get<any>(`${this.lmsUrl}getAllReturnedBooks`);
  }
  isAdmin(){
    var role =  localStorage.getItem('role');
    if(role == 'admin'){
    return true;
    }
    else{
    return false;
    }
  }
  isUser(){
    var role =  localStorage.getItem('role');
    if(role == 'user'){
    return true;
    }
    else{
    return false;
    }
  }
  
  isLoggedIn(){
    var role = localStorage.getItem('role');
    if(role == 'user' || role == 'admin'){
      return true;
    }else{
      return false;
    }
  }
  isNotLoggedIn(){
    var role = localStorage.getItem('role');
    if(role != 'user' && role != 'admin'){
      return true;
    }else{
      return false;
    }
  }
  getId(){
    var user=JSON.parse(localStorage.getItem('userDetails'));

    return user.id;
  }
}
