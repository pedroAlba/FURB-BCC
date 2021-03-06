import { Component, OnInit } from '@angular/core';
import { NavbarService } from '../navbar/navbar.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  constructor(private nav: NavbarService) { }

  ngOnInit() {
    this.nav.show();
  }

}
