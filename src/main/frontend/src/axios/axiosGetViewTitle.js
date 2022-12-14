import axios from 'axios';
const axiosGetViewTitle = (temp="") =>{
    const exp = [
        { 
            title : "메인페이지 조회수순1",
            views : 123,
            date : "2022-11-14"
        },
        { 
            title : "메인페이지 조회수순1",
            views : 123,
            date : "2022-11-14"
        },
        { 
            title : "메인페이지 조회수순1",
            views : 123,
            date : "2022-11-14"
        },
        { 
            title : "메인페이지 조회수순",
            views : 123,
            date : "2022-11-14"
        },
        { 
            title : "메인페이지 조회수순1",
            views : 123,
            date : "2022-11-14"
        }
    ]
    const request = axios.get('/get/mainviewdec')
    .then( response => {
        if(!response.data){
            return exp;
        }else{
            return response.data
        }
    }).catch(err =>{
        return exp;
    })

    return request;
}
export default axiosGetViewTitle;