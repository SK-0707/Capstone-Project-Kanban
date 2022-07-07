import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatCalendarCellClassFunction } from '@angular/material/datepicker';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import Swal from 'sweetalert2';
import { Task } from '../model/task';
import { KanbanService } from '../service/kanban.service';
import { TaskService } from '../service/task.service';

@Component({
  selector: 'app-task-dialog',
  templateUrl: './task-dialog.component.html',
  styleUrls: ['./task-dialog.component.css']
})
export class TaskDialogComponent implements OnInit {
  @Input() min: any;
  minDate:Date = new Date()
   dialogTitle: String;
   kanbanId: number;

  deatils:Task ={
    taskId:0,
    taskName: "",
    description: "",
    taskStatus: "",
    assignTo: "",
    date:""
  }

  form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<TaskDialogComponent>,
    @Inject(MAT_DIALOG_DATA) data:any,
    
    private kanbanService: KanbanService,

    private taskService: TaskService) {
      console.log(data);
      this.minDate.setDate(this.minDate.getDate() );
     this.dialogTitle = data.title;
     this.kanbanId = data.kanbanId;
    

    this.createTaskForm();

  

   }

   createTaskForm() :void{
    this.form=new FormGroup({
      "taskId":new FormControl(this.deatils.taskId,[Validators.required]),

      "taskName":new FormControl(this.deatils.taskName,[Validators.required]),
       "description":new FormControl(this.deatils.description,[Validators.required]),
       "taskStatus":new FormControl("TODO"),
       "assignTo":new FormControl(this.deatils.assignTo,[Validators.required]),
       "date":new FormControl(this.deatils.date,[Validators.required])

    });
  }

  dateClass: MatCalendarCellClassFunction<Date> = (cellDate, view) => {
    // Only highligh dates inside the month view.
    if (view === 'month') {
      const date = cellDate.getDate();

      // Highlight the 1st and 20th day of each month.
      return date === 1 || date === 20 ? 'example-custom-date-class' : '';
    }

    return '';
  };

  ngOnInit() {

 
  }

 
  save(){
    
    this.mapFormToTaskModel();
    if(this.form.value){
      // Swal.fire({   
      //   icon: 'success',  
      //   title: 'you created task successfuly ',  
      //   showConfirmButton: false,  
      //   timer: 10500  
      // }); 
    const Observable= this.kanbanService.saveNewTaskInKanban(this.kanbanId,this.form.value);
    Observable.subscribe((data:{}) =>{
     
      
    });
   
  }
  else{
    
    this.taskService.updateTask(this.form.value).subscribe();
  }
  this.dialogRef.close();
  window.location.reload();
}



  close() {
      this.dialogRef.close();
  } 

  private mapFormToTaskModel(): void {

    this.deatils.taskName = this.form.value;
    this.deatils.description= this.form.value;
    this.deatils.assignTo=this.form.value;

  }

}
