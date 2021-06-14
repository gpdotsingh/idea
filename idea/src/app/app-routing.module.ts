import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { GetHealthComponent } from './components/get-health/get-health.component';
import { HomeComponent } from './home/home/home.component';
import { CallbackComponent } from './login/callback/callback.component';
import { LoginComponent } from './login/login/login.component';

const routes: Routes = [
  {path:'',redirectTo:'',pathMatch:'full'},
  
  {
      path:"health",
      component:GetHealthComponent,
      canActivate:[AuthGuard]
  },
  {
      path:"login",
      component:LoginComponent
      
  },
  {
    path:"callback",
    component:CallbackComponent
    
  }
  ,
  {
    path:"home",
    component:HomeComponent
    
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
