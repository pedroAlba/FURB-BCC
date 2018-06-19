import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { RentDTO } from '../_models/rentDTO';
import { Observable } from 'rxjs';
import { Rent } from '../_models/rent';

@Injectable()
export class RentService {

    constructor(private http: HttpClient) { }

    create(rent: RentDTO) {
        return this.http.post(`${environment.baseURL}/api/rent/`, rent);
    }

    getOccupiedDays(vehicleId: string): Observable<String[]> {
        return this.http.get<String []>(`${environment.baseURL}/api/rent/days/` + vehicleId);
    }

    getUserRents(username: string): Observable<Rent[]> {
        let res =  this.http.get<Rent[]> (`${environment.baseURL}/api/rent/user/` + username)
        res.subscribe(v => {
            console.log(v);
        })
        return res;
    }

}
