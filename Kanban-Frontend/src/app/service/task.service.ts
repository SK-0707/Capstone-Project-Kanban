import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../model/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {


  constructor(private httpClient:HttpClient) { }

  apiURL="http://localhost:8080";

  updateTask(data:any): Observable<Task> {
    
    console.log(data);
    return this.httpClient.put<Task>(
      this.apiURL +'/api/v3/update' ,
      data,
      );
  }

  
  getTaskById(data:any): Observable<Task> {
    return this.httpClient.get<Task>(this.apiURL+ '/api/v2/' + data +'/tasks');
  }



}
