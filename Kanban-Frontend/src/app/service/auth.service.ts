import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isAuthenticate:boolean=false;
  redirecedUrl:string="";
  constructor() { }
  login(){
    this.isAuthenticate=true;
  }
}
