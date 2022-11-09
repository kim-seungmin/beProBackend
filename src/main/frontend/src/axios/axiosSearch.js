import axios from "axios";
const axiosSearch = (inputValue, hashTags=[]) =>{
    if(inputValue.length<2 && hashTags.length<1){
        alert("2글자 이상 혹은 태그를 선택 해 주세요");
    }else{
        let data ={
            inputValue : inputValue,
            hashTags : hashTags
        }
        const request = axios.post('/search',data)
        .then(response => 
            response)
        
        return request
    }
}
    

export default axiosSearch;