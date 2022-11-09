import axios from 'axios';

function axiosSignUpData(data){
    /*let request = {
        signUpSuccess : "", // true or false
        msg : "", // 회원가입에 성공했습니다 or 실패했습니다.
    }*/
    console.log(data);
    const request = axios.post('/user/signup',data)
    .then(response => 
        response.data
    )
    return request;
}

export default axiosSignUpData;