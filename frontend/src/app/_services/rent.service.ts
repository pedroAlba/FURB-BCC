import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../_models/index';
import { environment } from '../../environments/environment';
import { RentDTO } from '../_models/rent';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class RentService {

    constructor(private http: HttpClient) { }

    create(rent: RentDTO) {
        return this.http.post(`${environment.baseURL}/api/rent/`, rent);
    }

    getOccupiedDays(vehicleId: string): Observable<String[]> {
        return this.http.get<String []>(`${environment.baseURL}/api/rent/days/` + vehicleId);
    }

}
