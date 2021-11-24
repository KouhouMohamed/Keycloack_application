import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {KeycloakSecurityService} from "../services/keycloak-security.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private localhost : string = "http://localhost:8082/";
  constructor(private httpClient: HttpClient,private keycloakService:KeycloakSecurityService) { }

  public getProducts(){
    return this.httpClient.get(this.localhost+'products',
      {headers:new HttpHeaders( {Authorization:"Bearer "+this.keycloakService.kc.token})});
  }

  public getSuppliers(){
    return this.httpClient.get(this.localhost+'suppliers',
      {headers:new HttpHeaders( {Authorization:"Bearer "+this.keycloakService.kc.token})});
  }
}
