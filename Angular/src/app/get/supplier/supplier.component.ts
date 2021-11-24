import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.css']
})
export class SupplierComponent implements OnInit {

  public suppliers:any;
  constructor(private authService:AuthService) { }

  ngOnInit(): void {
    this.authService.getSuppliers().subscribe((response)=>{
        this.suppliers = response;
        this.suppliers = this.suppliers._embedded.suppliers
      },
      (error) => {
      })
  }

}
