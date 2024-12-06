import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {
  provideHttpClient,
  withInterceptorsFromDi,
} from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/pages/home/home.component';
import { LoginComponent } from './components/user/login/login.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from './components/common/header/header.component';
import { FooterComponent } from './components/common/footer/footer.component';
import { RegisterComponent } from './components/user/login/register/register.component';
import { AccountConfirmedComponent } from './components/user/account-confirmed/account-confirmed.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ProfileComponent,
    HeaderComponent,
    FooterComponent,
    RegisterComponent,
    AccountConfirmedComponent,
  ],
  bootstrap: [AppComponent],
  imports: [BrowserModule, AppRoutingModule, RouterModule, FormsModule],
  providers: [provideHttpClient(withInterceptorsFromDi())],
})
export class AppModule {}
