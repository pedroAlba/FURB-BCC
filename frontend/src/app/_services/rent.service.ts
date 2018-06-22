import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { RentDTO } from '../_models/rentDTO';
import { Observable } from 'rxjs';
import { Rent } from '../_models/rent';

@Injectable()
export class RentService {

    constructor(private http: HttpClient) { }

    baseAPI = `${environment.baseURL}/api/rent/`;
    create(rent: RentDTO) {
        return this.http.post(`${environment.baseURL}/api/rent/`, rent);
    }

    getOccupiedDays(vehicleId: string): Observable<String[]> {
        return this.http.get<String []>(this.baseAPI + '/days/' + vehicleId);
    }

    delete(rentId: String) {        
        return this.http.delete(this.baseAPI + '/' + rentId, {responseType: 'text'});
    }

    getUserRents(username: string): Observable<Rent[]> {
        return this.http.get<Rent[]> (this.baseAPI + '/user/' + username)        
    }

}
