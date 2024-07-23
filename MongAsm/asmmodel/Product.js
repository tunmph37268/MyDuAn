const mongoose=require('mongoose');
const Scheme=mongoose.Schema;
const Products=new Scheme({
    image:{type:String},
    name:{type:String},
    size:{type:String},
    price:{type:String},
    discrip:{type:String},
    id_typename:{type:Scheme.Types.ObjectId,ref: 'typename'},
},{
    timestamps:true
})
module.exports=mongoose.model('product',Products)