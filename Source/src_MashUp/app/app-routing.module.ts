import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MashUpComponent} from "./MashUp/MashUp.component";

const routes: Routes = [{path:'MashUp', component: MashUpComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
