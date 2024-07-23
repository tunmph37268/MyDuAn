const mongoose=require('mongoose');
const Scheme=mongoose.Schema;
const Xemays=new Scheme({
    image:{type:String},
    name:{type:String},
    price:{type:Number},
    discrip:{type:String},
},{
    timestamps:true
})
module.exports=mongoose.model('xemay',Xemays)