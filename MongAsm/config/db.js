const mongoose=require('mongoose');
mongoose.set('strictQuery',true);
const local ="mongodb://127.0.0.1:27017/QliASM"
const conect=async()=>{
    try{
        await mongoose.connect(local,
            {
                useNewUrlParser:true,
                useUnifiedTopology:true,
            })
            console.log('conet success')
    }catch(erorr){
        console.log(erorr);

    }
}
module.exports={conect}