import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { Kanban } from '../model/kanban';
import { Task } from '../model/task';
import { KanbanService } from '../service/kanban.service';
import { TaskService } from '../service/task.service';
import { TaskDialogComponent } from '../task-dialog/task-dialog.component';
import { UpdateDialogComponent } from '../update-dialog/update-dialog.component';


@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  kanban!: Kanban;
  todos: Task[] = [];
  inprogress: Task[] = [];
  dones: Task[] = [];
  archives:Task[] = [];
  id!:any;
  email!:any;
 

  constructor(
    private kanbanService: KanbanService,
    private taskService: TaskService,
    private route: ActivatedRoute,
    private dialog: MatDialog,) {this.email=sessionStorage.getItem('email')}

  ngOnInit() {
    this.getKanban();
  }

  drop(event: CdkDragDrop<any>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      this.updateTaskStatusAfterDragDrop(event);
      transferArrayItem(event.previousContainer.data,
                        event.container.data,
                        event.previousIndex,
                        event.currentIndex);
    }
  }

    openDialogForNewTask(): void {
    this.openDialog('Create New Task', new Task());
  }

    openTaskDialog(event:any): void {
    let taskId = event.target.taskId;
    this.taskService.getTaskById(taskId).subscribe(
      response => {
        this.openDialog('Update Task', response);
      }
    );
  }

  private getKanban(): void {
    let kanbanId = this.route.snapshot.params['kanbanId'];
     this.id=kanbanId;
    sessionStorage.setItem('id',this.id);
      console.log(this.id); 
    this.kanbanService.getKanbanById(kanbanId).subscribe(
      (response)=> {
       
        this.kanban = response;
        console.log(this.kanban); 
        this.splitTasksByStatus(this.kanban);
      }
    ) 
  }

  private splitTasksByStatus(kanban: Kanban): void {
    this.todos = kanban.tasks.filter(t=>t.taskStatus==='TODO');
    this.inprogress = kanban.tasks.filter(t=>t.taskStatus==='INPROGRESS');
    this.dones = kanban.tasks.filter(t=>t.taskStatus==='DONE');
    this.archives=kanban.tasks.filter(t=>t.taskStatus==='ARCHIVE')
  }
  
  private updateTaskStatusAfterDragDrop(event: CdkDragDrop<string[], string[]>) {
    let taskId = event.item.element.nativeElement.id;
    console.log(taskId);

    let kanbanId = this.route.snapshot.params['kanbanId'];
    let containerId = event.container.id;
    // Swal.fire({
    //   position: 'top-end',
    //   icon: 'success',
    //   title: 'Your work has been saved',
    //   showConfirmButton: false,
    //   timer: 2000
    // });

    this.kanbanService.getKanbanById(kanbanId).subscribe(
      response => {
        this.updateTaskStatus(response, containerId, taskId);
      }
    );
  }

  private updateTaskStatus(kanban: Kanban, containerId: string, taskId: any): void {

    kanban.tasks.forEach(function (task) {
      if (task.taskId == taskId) {
        if (containerId == 'todo') {
          console.log('Specific Task :: ' + task.taskName);
          task.taskStatus = 'TODO';
        }
        else if (containerId == 'inpro') {

          task.taskStatus = 'INPROGRESS';
        }
        else {
          task.taskStatus = 'DONE';
        }
      }
    });
    this.kanbanService.updateKanban(kanban).subscribe(
      (data:{})=>{  
    });
    
  }

  private openDialog(title: string, task: Task): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      title: title,
      task: task,
      kanbanId: this.kanban.kanbanId
    };
    this.dialog.open(TaskDialogComponent, dialogConfig)
  }

  update(task:Task){
    
   this.dialog.open(UpdateDialogComponent, {
    
    width: '330px',
    height: '500px',
    data: task 
    
  });
}
logoutIn(){
  this.kanbanService.logout();
  Swal.fire({
  position: 'top-end',
  icon: 'success',
  title: 'Logged out Successfully',
  showConfirmButton: false,
  timer: 1000
})
}
}
