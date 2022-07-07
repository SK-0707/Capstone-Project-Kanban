import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { KanbanService } from './kanban.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private auth:KanbanService,private router:Router) { }
  canActivate(): boolean {
    if(this.auth.loggedIn()){
      return true
    }
    else {
      this.router.navigate(['/login'])
      return false
    }
    }
}
