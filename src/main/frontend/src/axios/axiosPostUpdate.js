import axios from "axios";

const axiosPostUpdate = (body,board)=>{
    const request = axios.post(`/${board}/update`)
    .then( response => response.data )
    .catch( err =>{
        localStorage.removeItem("post_"+body.index);
        localStorage.setItem("post_"+body.index,JSON.stringify(body));
        return{
            err : err,
            msg : `통신 오류(오류코드 : ${err.request.status})로 인해 로컬스토리지에서 수정되었습니다.`
        }
    })
    return request;
}

export default axiosPostUpdate;