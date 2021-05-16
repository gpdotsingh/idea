import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GetHealthComponent } from './components/get-health/get-health.component';

const routes: Routes = [
  {path:'',redirectTo:'',pathMatch:'full'},
  
  {
      path:"health",
      component:GetHealthComponent
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
