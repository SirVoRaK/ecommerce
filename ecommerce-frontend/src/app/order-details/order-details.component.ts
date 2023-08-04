import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { constants } from '../constants/constants';
import { Order } from '../models/Order';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.scss'],
})
export class OrderDetailsComponent {
  order!: Order;

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    const orderId = this.route.snapshot.paramMap.get('id')!;
    this.loadOrder(orderId);
  }

  async loadOrder(orderId: string) {
    const requestBody = {
      query: `
      query {
        placedOrder(id: "${orderId}") {
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
    this.order = responseJson.data.placedOrder;

    if (!this.order) this.router.navigate(['/']);
  }
}
