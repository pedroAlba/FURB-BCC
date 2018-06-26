import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { VehicleDTO } from '../_models/vehicle';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class VehicleService {


  constructor(private http: HttpClient) { }

  baseAPI = `${environment.baseURL}/api/vehicles`;

  create(vehicle: VehicleDTO) {
    return this.http.post(this.baseAPI, vehicle);
  }

  getVehicles(): Observable<VehicleDTO[]> {
    return this.http.get<VehicleDTO[]>(this.baseAPI);
  }

  deleteVehicle(id: string) {
    return this.http.delete(this.baseAPI + '/' + id, {responseType: 'text'});
  }

  updateVehicle(id: string, vehicle: VehicleDTO) {
    return this.http.put(this.baseAPI + '/' + id, vehicle);
  }
}
