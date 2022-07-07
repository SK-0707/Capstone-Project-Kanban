import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Kanban } from '../model/kanban';
import { Task } from '../model/task';

@Injectable({
  providedIn: 'root'
})
export class KanbanService {

  constructor(private httpClient:HttpClient,private router:Router) { }

  apiURL="http://localhost:5255";
  apiURL1="http://localhost:9005";

  public authenticate(data:any): Observable<any> {
    const headers = { 'content-type': 'application/json'}  
    const body=JSON.stringify(data);
    console.log(body)
    return this.httpClient.post<any>(this.apiURL + '/api/v1/login',data)
  }

  loggedIn(){
    return !!sessionStorage.getItem('token')
  }
  logout(){
     sessionStorage.removeItem('token');
     this.router.navigate(['']);

  }


  public register(data:any): Observable<any> {
    const headers = { 'content-type': 'application/json'}  
    const body=JSON.stringify(data);
    console.log(body)
    return this.httpClient.post<any>(this.apiURL + '/api/v1/register',data)
  }



  saveNewKanban(data:any): Observable<string> {
    let headers = new HttpHeaders({'Content-Type': 'application/json' });
   
    // console.log(data);
    
    return this.httpClient.post<string>(
      this.apiURL1 + '/api/v2/save' ,
      JSON.stringify(data) ,
      {'headers':headers}
    );
  }

  saveNewTaskInKanban(kanbanId: number,data:any): Observable<Task> {
    let headers = new HttpHeaders({'Content-Type': 'application/json' });
    let options = { headers: headers };
    return this.httpClient.post<Task>(
      this.apiURL1 + '/api/v2/' + kanbanId + '/tasks',
      data,
      options);
  }



  getAllKanbanBoards(): Observable<Kanban[]> {
    return this.httpClient.get<Kanban[]>(this.apiURL1 + '/api/v2/kanbans');
  }

  getKanbanById(id: number): Observable<Kanban> {
    return this.httpClient.get<Kanban>(this.apiURL1 + '/api/v2/kanban/' + id);

  }

  updateTaskToKanban(kanbanId:any,data:any): Observable<Task>{
    
    console.log(kanbanId);
    console.log(data);
    
    return this.httpClient.post<Task>(
      this.apiURL1 + '/api/v2/' + kanbanId + '/update',
      data
      );

  }
  updateKanban(kanban:any): Observable<Kanban> {
    return this.httpClient.put<Kanban>(
      this.apiURL1 +'/api/v2/update' ,
      kanban
      );
  }
  public getAllKanbanByEmail(email:any): Observable<Kanban[]> {  
    console.log(email);
    return this.httpClient.get<Kanban[]>(this.apiURL1 + '/api/v2/kanbans/'+ email);
  }

  public deleteKanbanById(kanbanId:any):Observable<Object>{
    return this.httpClient.delete(this.apiURL1+'/api/v2/delete/'+kanbanId)
  }

}
