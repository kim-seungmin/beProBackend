import axios from 'axios';
function axiosGetHashTag(){
    const request = axios.get('/get/hashtag')
    .then( response =>
        response.data)
    .catch(err =>{
        alert(err.message);
    })
    
    return request;
}

export default axiosGetHashTag