import {Component, OnInit} from '@angular/core';
import {KeycloakSecurityService} from "./services/keycloak-security.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Suppliers&Products';
  public user  : any = {"name":""};
  constructor(public kcSecurityService: KeycloakSecurityService) {
  }
  ngOnInit(): void {
    if(this.kcSecurityService.kc.authenticated){
      this.kcSecurityService.kc.loadUserInfo().then((response)=>{
        this.user = response;
      })
    }
  }
  onLogout(){
    this.kcSecurityService.kc.logout()
  }

  onLogin() {
    this.kcSecurityService.kc.login()
  }

  onChangePassword() {
    this.kcSecurityService.kc.accountManagement();
  }

  isAppManager():boolean {
    return  this.kcSecurityService.kc.hasResourceRole("app-manager")
  }
}
