import axios from "axios"

const axiosPost = (post,board) =>{
    const request = axios.post(`/${board}/post`)
    .then( response => response.data )
    .catch(err =>{
        if(!localStorage.getItem("post_1")){
            localStorage.setItem("post_"+1, JSON.stringify(post));
        }else{
            const index = localStorage.length;
            localStorage.setItem("post_"+index, JSON.stringify(post));
        }
        return {
            err : err,
            msg : `통신 오류(오류코드 : ${err.request.status})로 인해 로컬스토리지에 저장되었습니다.`
        }
    })
    return request;
}

export default axiosPost;