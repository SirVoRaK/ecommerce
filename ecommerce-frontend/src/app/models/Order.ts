import { Item } from './Item';

export class Order {
  id!: string;
  order!: string;
  origin!: string;
  total!: number;
  createdAt!: string;
  items!: Item[];
}
