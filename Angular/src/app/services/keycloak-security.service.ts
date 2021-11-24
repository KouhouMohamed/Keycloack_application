import { Injectable } from '@angular/core';
import {KeycloakInstance} from "keycloak-js";
import {HttpClient} from "@angular/common/http";

declare var Keycloak:any;

@Injectable({
  providedIn: 'root'
})
export class KeycloakSecurityService {

  public kc:KeycloakInstance=new Keycloak();

  constructor(private httpClient:HttpClient) { }
  public async init(){
     this.kc = new Keycloak({
      url:"http://localhost:8080/auth",
      clientId:"angular-client",
      realm:"ecom_realm"
    });

    await this.kc.init({
       // avec le lancement de l'application on redirect vers la formule d'authentification
       //onLoad:"login-required"
       // au demarage on vérifie si l'authenfication si oui on récupère les tokens et autre
       // et après si il tente d'accéder à une resource protéger on le regirige vers Login form
        onLoad:"check-sso"
     });
  }
}
