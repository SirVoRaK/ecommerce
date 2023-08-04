export class Money {
  static format(value: number): string {
    return `$${value.toFixed(2)}`;
  }
}
