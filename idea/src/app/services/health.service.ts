import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Health } from '../model/health';
@Injectable({
  providedIn: 'root'
})
export class HealthService {

  constructor(private _httpClient:HttpClient) { }

  healthUrl: string ="http://localhost:8086/actuator/health/custom";

  public getHealth(){
    console.log("URL::"+this.healthUrl);
    return this._httpClient.get<Health>(this.healthUrl);
  }
}
