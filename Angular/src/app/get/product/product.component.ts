import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products : any;
  constructor(private authService:AuthService) { }

  ngOnInit(): void {
    this.authService.getProducts().subscribe((response)=>{
        this.products = response;
      },
      (error) => {
      })
  }


}
