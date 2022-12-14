import axios from 'axios';

function axiosSignUpData(data){
    const request = axios.post('/user/signup',data)
    .then(response => 
        response.data
    ).catch(err =>{
        alert(err.message);
    })
    return request;
}

export default axiosSignUpData;