const mongoose=require('mongoose');
const Scheme=mongoose.Schema;
const Typenames=new Scheme({
    name:{type:String},
},{
    timestamps:true
})
module.exports=mongoose.model('typename',Typenames)