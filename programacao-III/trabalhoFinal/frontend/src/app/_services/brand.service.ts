import { Injectable } from "@angular/core";
import { Brand } from "../_models/brand";
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable()
export class BrandService {

    constructor(private http: HttpClient) { }

    create(brand: Brand) {
        return this.http.post(`${environment.baseURL}/api/brand/`, brand);
    }

    findAll(): Observable<Brand[]> {
        return this.http.get<Brand[]>(`${environment.baseURL}/api/brand/`)
    }

}
