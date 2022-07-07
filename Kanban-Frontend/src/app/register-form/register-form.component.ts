import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { KanbanService } from '../service/kanban.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  constructor(private router:Router, private kanbanService:KanbanService) {}

  ngOnInit(): void {
  }

    registerForm=new FormGroup({
       username:new FormControl('',[Validators.required]),
       email:new FormControl('',[Validators.required,Validators.email]),
       password:new FormControl('',[Validators.required,Validators.minLength(8)])
      });

  get username() {
    return this.registerForm.get('username') as FormControl;
  }
  get email() {
    return this.registerForm.get('email') as FormControl;
  }
  get password() {
    return this.registerForm.get('password') as FormControl;
  }

  onSubmit(){
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 1500,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    })
    
    Toast.fire({
      icon: 'success',
      title: 'Registered Successfully'
    })
    const Observable= this.kanbanService.register(this.registerForm.value);
    Observable.subscribe((data:{}) =>{
      this.router.navigate(['/login']);
    });
  }

}
