import { Component, OnInit } from '@angular/core';
import { constants } from '../constants/constants';
import { Order } from '../models/Order';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss'],
})
export class OrdersComponent implements OnInit {
  orders!: Promise<Order[]>;

  ngOnInit(): void {
    this.loadOrders();
  }

  async loadOrders() {
    const requestBody = {
      query: `
      query {
        placedOrders {
          id,
          order,
          origin,
          total,
          createdAt,
          items {
            name,
            image,
            qty,
            cost,
            currency
          }
        }
      }
    `,
    };

    const response = await fetch(constants.graphQlUrl, {
      method: 'POST',
      body: JSON.stringify(requestBody),
      headers: {
        'Content-Type': 'application/json',
      },
    });
    const responseJson = await response.json();
    this.orders = Promise.resolve(responseJson.data.placedOrders);
  }
}
