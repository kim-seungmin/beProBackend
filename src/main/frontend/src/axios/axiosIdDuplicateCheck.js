import axios from "axios";

function axiosIdDuplicateCheck(id){
    const request = axios.post('/user/idcheck',id)
    .then(response =>
        response.data
    ).catch(err =>{
        alert(err.message);
    })
    return request;
}

export default axiosIdDuplicateCheck;