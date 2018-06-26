import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { routing } from './app.routing';
import { AuthGuard } from './_guards/index';
import { AuthenticationService, UserService, AlertService } from './_services/index';
import { HomeComponent } from './home/index';
import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';

import {
    MatAutocompleteModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatStepperModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MAT_DATE_LOCALE,
} from '@angular/material';

const MAT_MODULES = [
    MatAutocompleteModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatStepperModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
];

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NavbarComponent } from './navbar/navbar.component';
import { RentsComponent } from './rents/rents.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { AlertComponent } from './_directives';
import { NavbarService } from './navbar/navbar.service';
import { AdminComponent, } from './admin/admin.component';
import { VehicleService } from './_services/vehicle.service';
import { RentService } from './_services/rent.service';
import {FlexLayoutModule} from '@angular/flex-layout';
import { RentDialogComponent } from './dialogs/rent/rent-dialog.component';
import { BrandService } from './_services/brand.service';
import { VehicleDialogComponent } from './dialogs/vehicle/vehicle-dialog.component';
import { RentEditDialogComponent } from './dialogs/rent/edit/rent-edit-dialog.component';


@NgModule({
        exports: [
        MAT_MODULES,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        routing,
        MAT_MODULES,
        BrowserAnimationsModule,
        FlexLayoutModule,
        ReactiveFormsModule,
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent,
        NavbarComponent,
        RentsComponent,
        AboutComponent,
        ContactComponent,
        AlertComponent,
        AdminComponent,
        VehicleDialogComponent,
        RentDialogComponent,
        RentEditDialogComponent,
    ],
    providers: [
        AuthGuard,
        AuthenticationService,
        UserService,
        VehicleService,
        RentService,
        NavbarService,
        AlertService,
        BrandService,        
        {provide: MAT_DATE_LOCALE, useValue: 'pt-BR'}
    ],
    entryComponents: [VehicleDialogComponent, RentDialogComponent, RentEditDialogComponent],
    bootstrap: [AppComponent],
})

export class AppModule { }
