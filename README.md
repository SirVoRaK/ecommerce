# ecommerce

Simple Ecommerce endpoints using GraphQL and PubSub.
<br>

## Summary
1. [Basic features](#basic-features)
2. [Run locally](#run-locally)

### Basic features:
- Receive orders from third parties origins.
- Visualize all of the orders.

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
