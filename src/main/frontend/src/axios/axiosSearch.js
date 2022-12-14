import axios from "axios";
const axiosSearch = (inputValue, hashTags=[]) =>{
    console.log("검색어 : "+inputValue);
    console.log("해쉬태그들 : "+hashTags);
    if(inputValue.length<2 && hashTags.length<1){
        alert("검색어 길이는 최소 2글자 이상이어야 합니다.");
        return false;
    }else{
        let data ={
            inputValue : inputValue,
            hashTags : hashTags
        }
        const request = axios.post('/search',data)
        .then(response =>{
            console.log(response.data);
            return response.data;
        } 
            )
        .catch(err =>{
            alert(err);
        })
        return request
    }
}
    

export default axiosSearch;