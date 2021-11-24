import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductComponent} from "./get/product/product.component";
import {SupplierComponent} from "./get/supplier/supplier.component";

const routes: Routes = [
  {path:"products", component:ProductComponent},
  {path:"suppliers", component:SupplierComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
