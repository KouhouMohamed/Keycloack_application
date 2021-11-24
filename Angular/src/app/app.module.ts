import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductComponent } from './get/product/product.component';
import { SupplierComponent } from './get/supplier/supplier.component';
import {HttpClientModule} from "@angular/common/http";
import {KeycloakSecurityService} from "./services/keycloak-security.service";

export function kcfactory(kcsecurityservice:KeycloakSecurityService){
  return ()=>kcsecurityservice.init();
}
@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    SupplierComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    // Demerer le service KeyclockSecurity avant le demarrage de l'application on executant la fct kcfactory
    {provide:APP_INITIALIZER, deps:[KeycloakSecurityService],useFactory:kcfactory,multi:true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
