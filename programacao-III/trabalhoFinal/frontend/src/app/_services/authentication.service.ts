import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { User } from '../_models';

@Injectable()
export class AuthenticationService {

    @Output() getLoggedInName: EventEmitter<any> = new EventEmitter();
    
    constructor(private http: HttpClient) { }

    login(username: string, password: string) {
        return this.http.post<any>(`${environment.baseURL}/api/login`, { username: username, password: password })
            .map(user => {
                // login successful if there's a jwt token in the response
                if (user && Boolean(user)) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(username));
                    this.getLoggedInName.emit(username);
                }
                return user;
            });
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }

    getCurrentUser() : string {
        return <string> JSON.parse(localStorage.getItem('currentUser'));
    }
}