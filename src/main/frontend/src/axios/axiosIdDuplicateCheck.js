import axios from "axios";
//깃허브 테스트
function axiosIdDuplicateCheck(id){
    // 백엔드 통신 이후 주석 해제
    /*const request = axios.post('/user/idcheck',id)
    .then(response =>
        response.data
    )
    return request;*/
    return {
        idCheckSuccess : false,
        msg : "중복된 아이디가 존재합니다."
    }
}

export default axiosIdDuplicateCheck;