var express = require("express");
var router = express.Router();
const Typenames = require("../asmmodel/typename");
const Products = require("../asmmodel/Product");
const Cart=require("../asmmodel/Cart");
const upload=require("../config/common/upload")

// Hàm tính tổng giá trị giỏ hàng
const calculateTotalPrice = async (products) => {
  let total = 0;
  for (const item of products) {
      const product = await Products.findById(item.productId);
      total += item.quantity * product.price;
  }
  return total;
};

// Route thêm sản phẩm vào giỏ hàng
/**
* POST /add-to-cart
* Thêm sản phẩm vào giỏ hàng
* Yêu cầu:
* - userId: ID của người dùng (Firebase)
* - productId: ID của sản phẩm
* - quantity: Số lượng sản phẩm
*/
router.post("/add-to-cart", async (req, res) => {
  const { userId, productId, quantity } = req.body;

  // Kiểm tra xem các trường cần thiết có trong request body hay không
  if (!userId || !productId || !quantity) {
      return res.status(400).json({ message: 'Missing required fields' });
  }

  try {
      // Tìm giỏ hàng của người dùng
      let cart = await Cart.findOne({ userId });
      if (cart) {
          // Tìm sản phẩm trong giỏ hàng
          const productIndex = cart.products.findIndex(p => p.productId.toString() === productId);
          if (productIndex !== -1) {
              // Tăng số lượng nếu sản phẩm đã tồn tại trong giỏ hàng
              cart.products[productIndex].quantity += quantity;
          } else {
              // Thêm sản phẩm mới vào giỏ hàng nếu sản phẩm chưa có trong giỏ hàng
              cart.products.push({ productId, quantity });
          }
      } else {
          // Tạo giỏ hàng mới nếu người dùng chưa có giỏ hàng
          cart = new Cart({
              userId,
              products: [{ productId, quantity }]
          });
      }
      // Tính lại tổng giá trị của giỏ hàng
      cart.totalPrice = await calculateTotalPrice(cart.products);
      // Lưu giỏ hàng
      const result = await cart.save();
      // Trả về phản hồi thành công
      res.status(200).json({
          status: 200,
          message: "Product added to cart successfully",
          data: result
      });
  } catch (err) {
      // Trả về lỗi nếu có lỗi xảy ra
      res.status(500).json({ message: err.message });
  }
});
// Route lấy danh sách sản phẩm trong giỏ hàng của người dùng
/**
 * GET /get-cart/:userId
 * Lấy danh sách sản phẩm trong giỏ hàng của người dùng
 * Yêu cầu:
 * - userId: ID của người dùng (Firebase)
 */
router.get("/get-cart/:userId", async (req, res) => {
    const { userId } = req.params;

    try {
        // Tìm giỏ hàng của người dùng
        const cart = await Cart.findOne({ userId }).populate('products.productId', 'name price image');

        if (!cart) {
            return res.status(404).json({ message: 'Cart not found' });
        }

        // Trả về danh sách sản phẩm trong giỏ hàng
        res.status(200).json({
            status: 200,
            message: "Get cart successfully",
            data: {
                userId: cart.userId,
                products: cart.products,
                totalPrice: cart.totalPrice
            }
        });
    } catch (err) {
        // Trả về lỗi nếu có lỗi xảy ra
        res.status(500).json({ message: err.message });
    }
});
//add product
router.post("/add-product",async (req, res) => {
  try {
    const data = req.body;

    const newProducts = new Products({
      image:data.image,
      name: data.name,
      size: data.size,
      price: data.price,
      discrip:data.discrip,
      id_typename: data.id_typename,
    });
    const result = await newProducts.save();
    if (result) {
      res.json({
        status: 200,
        messenger: "Add Success",
        data: result,
      });
    } else {
      res.json({
        status: 400,
        messenger: "Fail success",
        data: [],
      });
    }
  } catch (err) {
    console.log(err);
  }
});
//get product
router.get("/get-list-product", async (req, res) => {
    try {
      //lấy danh sách theo thứ tự nhà phân phối mới nhất
      const data = await Products.find().sort({ createdAt: -1 });
     
      if (data) {
        //nếu lây ds thanh công thì trả về danh sách dữ liệu
        res.json({
          status: 200,
          data: data,
        });
      } else {
        res.json({
          status: 400,
          messenger: "lấy danh sách thất bại",
          data: [],
        });
      }
    } catch (error) {
      console.log(error);
    }
  });
router.post("/add-typename", async (req, res) => {
  try {
    const data = req.body;
    const newTypenames = new Typenames({
      name: data.name,
    });
    const result = await newTypenames.save();
    if (result) {
      res.json({
        status: 200,
        messenger: "Add Success",
        data: result,
      });
    } else {
      res.json({
        status: 400,
        messenger: "Fail success",
        data: [],
      });
    }
  } catch (err) {
    console.log(err);
  }
});

//list typname
router.get("/get-list-typename", async (req, res) => {
  try {
    //lấy danh sách theo thứ tự nhà phân phối mới nhất
    const data = await Typenames.find().sort({ createdAt: -1 });
   
    if (data) {
      //nếu lây ds thanh công thì trả về danh sách dữ liệu
      res.json({
        status: 200,
      
        data: data,
      });
    } else {
      res.json({
        status: 400,
        messenger: "lấy danh sách thất bại",
        data: [],
      });
    }
  } catch (error) {
    console.log(error);
  }
});
//update
router.put("/update-product-by-id/:id", async (req, res) => {
  try {
    const { id } = req.params;
    const data = req.body;
    const result = await Products.findByIdAndUpdate(id, {
    image:data.image,
    name:data.name,
    size:data.size,
    price:data.price,
    discrip:data.discrip,
    
    });
    if (result) {
      res.json({
        status: 200,
        messenger: "tìm thấy id và update thành công",
        data: result,
      });
    } else {
      res.json({
        status: 400,
        messenger: "update thất bại",
        data: null,
      });
    }
  } catch (error) {
    console.log(error);
  }
});
//delete
router.delete("/delete-product-by-id/:id", async (req, res) => {
  try {
    const { id } = req.params;
    const result = await Products.findByIdAndDelete(id);
    if (result) {
      res.json({
        status: 200,
        messenger: "tìm và xóa theo id thành công",
        data: result,
      });
    } else {
      res.json({
        status: 400,
        messenger: "tìm và xóa thất bại",
        data: [],
      });
    }
  } catch (error) {
    console.log(error);
  }
});
//tim kiem
router.get('/get-product-by-id', async (req,res)=>{
  try {
      const id = req.query.id
      const data = await Products.find({name: {"$regex": id, "$options": "i"}}).sort({createdAt: -1})
      res.json({
          "status":200,
          "messenger":"Tìm kiếm thành công",
          "data":data
      })
  } catch (error) {
      console.log(error)
  }
})
module.exports = router;
