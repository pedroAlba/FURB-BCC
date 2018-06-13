import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { VehicleDTO } from '../_models/vehicle';
import { environment } from '../../environments/environment';

@Injectable()
export class VehicleService {

  constructor(private http: HttpClient) { }


  create(vehicle: VehicleDTO) {
    return this.http.post(`${environment.baseURL}/api/vehicles/`, vehicle);
  }

}
