import axios from 'axios';
function axiosGetHashTag(getTag, tag){
    /*const request = axios.get('/get/hashtag')
    .then( response =>
        response.data)*/

    const request = [{
        "name" : "#컴퓨터공학과" 
    },
    {
        "name" : "#컴퓨터시스템공학과"
    },
    {
        "name" : "#의예과"
    },
    {
        "name" : "#건축공학과"
    }]
    return request;
}

export default axiosGetHashTag