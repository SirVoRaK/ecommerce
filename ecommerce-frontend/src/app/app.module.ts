import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { OrdersComponent } from './orders/orders.component';
import { OrderEntryComponent } from './order-entry/order-entry.component';
import { MatCardModule } from '@angular/material/card';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { ItemsListComponent } from './items-list/items-list.component';
import { ItemEntryComponent } from './item-entry/item-entry.component';

@NgModule({
  declarations: [AppComponent, OrdersComponent, OrderEntryComponent, OrderDetailsComponent, ItemsListComponent, ItemEntryComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
