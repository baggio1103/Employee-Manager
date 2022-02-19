import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  employees: Employee[];
  editEmployee!: Employee | null;
  deleteEmployee!: Employee | null;

  constructor(private employeeService: EmployeeService){
    this.employees = []
  }
  
  ngOnInit(): void {
    this.getEmployees();
  }

  public getEmployees():void {
    this.employeeService.getEmployee().subscribe(
      (response: Employee[]) => {
        this.employees = response;
      },
      (error : HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddEmployee(addForm: NgForm): void {
    document.getElementById('addEmployeeModal')?.click()
    this.employeeService.addEmployee(addForm.value).subscribe(
        (response: Employee) => {
          console.log(response);
          this.getEmployees();
          addForm.reset();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
          addForm.reset();
        }
    );
  }

  public onUpdateEmployee(employee : Employee): void {
    this.employeeService.updateEmployee(employee).subscribe(
      (response: Employee) =>{
        console.log("Successfully edited")
        this.getEmployees();
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onDeleteEmployee(id : number){
    this.employeeService.deleteEmployee(id).subscribe(
        (response: void) => {
          console.log(response)
          this.getEmployees()
        },
        (error: HttpErrorResponse) => {
          alert(error.message)
        }

    )
  }

  public searchEmployees(key : string):void {
    const result : Employee[] = [];
    for(const employee of this.employees){
       if(employee.name.toLocaleLowerCase().indexOf(key.toLocaleLowerCase()) !== -1
          || employee.email.toLocaleLowerCase().indexOf(key.toLocaleLowerCase()) !== -1
          || employee.jobTitle.toLocaleLowerCase().indexOf(key.toLocaleLowerCase()) !== -1
          || employee.phone.toLocaleLowerCase().indexOf(key.toLocaleLowerCase()) !== -1){
          result.push(employee)
       }
    }
    this.employees = result;
    if(result.length === 0 || !key){
      this.getEmployees()
    }
  }

  public onOpenModal(employee: Employee | null, mode:String):void {
    this.editEmployee = employee;
    const container = document.getElementById('main-container')
    const button = document.createElement('button')
    button.type = 'button'
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal')
    if(mode === 'add'){
      button.setAttribute('data-target','#addEmployeeModal')  
    }else if(mode === 'edit'){
      button.setAttribute('data-target','#updateEmployee')  
    }else if(mode === 'delete'){
      this.deleteEmployee = employee
      button.setAttribute('data-target','#deleteEmployeeModal')  
    }
    container?.appendChild(button);
    button.click();
  }

}
