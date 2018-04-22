import { Component, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private http: HttpClient) { }

  isValid = '';
  cpf: String;

  validar() {
    this.http.get(`http://localhost:8080/trabalho02/rest/cpf/${this.cpf}`, {responseType: 'text'}).subscribe(data => {      
        this.isValid = data.toString();
    });
  }
}

