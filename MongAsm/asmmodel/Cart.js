const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const CartSchema = new Schema({
    userId: { type: String, required: true }, // userId từ Firebase
    products: [
        {
            productId: { type: Schema.Types.ObjectId, ref: 'product', required: true },
            quantity: { type: Number, required: true, default: 1 }
        }
    ],
    totalPrice: { type: Number, required: true, default: 0 }
}, {
    timestamps: true
});

module.exports = mongoose.model('cart', CartSchema);
