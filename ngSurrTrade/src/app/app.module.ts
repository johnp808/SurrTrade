import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({ declarations: [AppComponent, HomeComponent],
    bootstrap: [AppComponent], imports: [BrowserModule, AppRoutingModule], providers: [provideHttpClient(withInterceptorsFromDi())] })
export class AppModule {}
