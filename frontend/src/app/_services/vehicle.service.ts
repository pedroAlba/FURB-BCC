import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { VehicleDTO } from '../_models/vehicle';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class VehicleService {

  constructor(private http: HttpClient) { }


  create(vehicle: VehicleDTO) {
    return this.http.post(`${environment.baseURL}/api/vehicles/`, vehicle);
  }

  getVehicles(sort: string, order: string, page: number): Observable<VehicleDTO[]> {
    return this.http.get<VehicleDTO[]>(`${environment.baseURL}/api/vehicles`);
  }

  deleteVehicle(id: string) {
    return this.http.delete(`${environment.baseURL}/api/vehicles/` + id, {responseType: 'text'});
  }
}
