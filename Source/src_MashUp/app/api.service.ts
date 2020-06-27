import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpClient: HttpClient) { }

  API_KEY = 'GKJDUI3KPPH12YS5UNLCI2ZNUNIBUL42O3TNPOR0BV54ERNN';
  API_SECRET = 'JREXS4GEN3IDHAGAD4CGNUDTGHBQRF0AY2RTPKW55BYPR4FK'
  location = 'Springfield'


  public getPlace() {
    return this.httpClient.get('https://api.foursquare.com/v2/venues/explore/?near=' + this.location + '&limit=3' + '&query=tacos' + '&client_id=' + this.API_KEY + '&client_secret=' + this.API_SECRET + '&v=20131124');
  }

}
