import { Component, OnInit } from '@angular/core';
import {ApiService} from "../api.service";

@Component({
  selector: 'app-news',
  templateUrl: './MashUp.component.html',
  styleUrls: ['./MashUp.component.css']

})

export class MashUpComponent implements OnInit {

  place;
  myJSON;
  venue;

  constructor(private apiService: ApiService) { }
  ngOnInit(): void {
    this.apiService.getPlace().subscribe((data)=>{
      console.log(data);

      this.place = data['response']
      this.myJSON = JSON.stringify(this.place);
    })
  }
  getVenues() {
    this.venue = this.venue.nativeElement.value;
  }
}
