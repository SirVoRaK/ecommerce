# ecommerce

Simple Ecommerce endpoints using GraphQL and PubSub.
<br>

## Summary
1. [Basic features](#basic-features)
2. [Run locally](#run-locally)
3. [API Usage](#api-usage)

### Basic features:
- Receive orders from third parties origins.
- Visualize all orders.

### Run locally:

1. Install Docker and Docker Compose.

2. Clone the repository and cd into it:

```
git clone https://github.com/SirVoRaK/ecommerce
cd ecommerce
```

3. Build the docker images:

```
docker build -t ecommerce-publisher ecommerce-publisher
docker build -t ecommerce-consumer ecommerce-consumer
```

4. **__(IMPORTANT)__** Create the data folder:

```
mkdir data
mkdir data/mongodb
mkdir data/pubsub
```

5. Run the containers:
    1. Keep attached:
    ```
    docker compose up
    ```

    2. Run in background:
    ```
    docker compose up -d
    ```

6. Stop the container:

```
docker compose down
```

### API Usage:

## Publish an order:
# Example Request:
POST http://localhost:8001/graphql

```graphql
mutation {
    publishPlacedOrderMessage(order: {
        order: "MY-ORDER-NUMBER-1",
        origin: "moon",
        items: [
            {
                name: "Boot",
                image: "image-path.png",
                qty: 1,
                cost: 50.25,
                currency: "BRL"
            },
            {
                name: "Hammer",
                image: "image-path.png",
                qty: 2,
                cost: 10.5,
                currency: "BRL"
            },
            {
                name: "Fork",
                image: "image-path.png",
                qty: 12,
                cost: 2.25,
                currency: "BRL"
            }
        ]
    }) {
        success,
        message
    }
}
```

# Example Response:
```json
{
    "data": {
        "publishPlacedOrderMessage": {
            "success": true,
            "message": "Order created successfully"
        }
    }
}
```
