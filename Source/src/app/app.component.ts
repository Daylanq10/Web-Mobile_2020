import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  // define list of items
  items= [];
  public newTask;
  public day;
  public hour;
  public minute;
  public second;
  public reason;

  public addToList(){
    if (this.newTask == '') {
      this.items.push('Empty');
    }
    else {
    this.items.push(this.newTask)};
    this.newTask = '';
  }

  public deleteTask(index){
    this.items.splice(index,1);
  }

  public countDown() {
    this.second = this.second - 1;
    if (this.second < 0) {
      this.second = 59;
      this.minute = this.minute - 1;
    }
    if (this.minute < 0) {
      this.minute = 59;
      this.hour = this.hour - 1;
    }
    if (this.hour < 0) {
      this.hour = 23;
      this.day = this.day - 1;
    }
  }
  public timer() {
    setInterval(() => {
      this.countDown();
    }, 1000);
  }
}


