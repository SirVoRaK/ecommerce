db = db.getSiblingDB('ecommerce')

db.createUser({
    user: 'ecommerce_consumer',
    pwd: 'ecommerce_consumer_password',
    roles: [
        {
            role: 'readWrite',
            db: 'ecommerce',
        },
    ],
})
