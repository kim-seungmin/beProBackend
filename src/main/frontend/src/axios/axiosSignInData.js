import axios from 'axios';
function axiosSignInData(data){
    console.log(data);
    const request = axios.post('/user/signin',data)
    .then(response =>response.data
    ).catch(err =>{
        alert(err.message);
    })
    console.log(request);
    request.then(token => {
        if(token.loginSuccess){
            localStorage.setItem("token",JSON.stringify({value:token.cookie, nick:token.nick, id:data.id}))
            window.location.reload();
        }
    })
    /*if(request.loginSuccess){
        localStorage.setItem("token",JSON.stringify({value:request.cookie, nick:request.nick}));
    }*/
    return request;
}
export default axiosSignInData;