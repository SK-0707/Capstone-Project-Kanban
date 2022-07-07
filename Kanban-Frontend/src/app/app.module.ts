import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatChipsModule} from '@angular/material/chips';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { RegisterFormComponent } from './register-form/register-form.component';
import { TaskComponent } from './task/task.component';
import { KanbanComponent } from './kanban/kanban.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { KanbanDialogComponent } from './kanban-dialog/kanban-dialog.component';
import { TaskDialogComponent } from './task-dialog/task-dialog.component';
import { MatListModule } from '@angular/material/list';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { UpdateDialogComponent } from './update-dialog/update-dialog.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { KanbanService } from './service/kanban.service';
import { AuthGuardService } from './service/auth-guard.service';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { PrivacyComponent } from './privacy/privacy.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    RegisterFormComponent,
    TaskComponent,
    KanbanComponent,
    KanbanDialogComponent,
    TaskDialogComponent,
    UpdateDialogComponent,
    HomeComponent,
    AboutComponent,
    PrivacyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatDialogModule,
    MatInputModule,
    MatSelectModule,
    DragDropModule,
    MatListModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    MatButtonModule,
    MatChipsModule
  ],
  providers: [KanbanService,AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
