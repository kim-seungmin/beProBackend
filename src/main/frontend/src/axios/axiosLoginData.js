import axios from 'axios';
function axiosLoginData(data){
    let request = { 
        loginSuccess : "", // true or false
        cookie : "", // jwt token
        nick : "", // 닉네임
        msg : "" // 로그인 성공, 로그인 실패 등등
    }
    
    // 로그인 로직
    /*
     일단은 로컬 로직으로 로그인이 가능하게 되도록 설정 해 둠
     /user/login 으로 아이디와 비번 전달
     돌아온 object 타입의 데이터를 받아서 정제 후 jwt 토큰은 localStorage에 저장
     저장된 토큰이 있다면 헤더 컴포넌트 목록을 변경
    */ 
    if(data.id==="김회창" && data.password==="123123"){
        request['loginSuccess'] = true;
        request['cookie'] = "dfafdasfas";
        request['nick'] = "haechang";
        request['msg'] = "로그인에 성공하였습니다.";
    }else{
        request['loginSuccess'] = false;
        request['msg'] = "로그인에 실패하였습니다.";
    }
    if(request.loginSuccess){
        localStorage.setItem("token",JSON.stringify({value:request.cookie, nick:request.nick}));
    }

    /*const request = axios.post('/user/login',data)
    .then(response =>
        response.data
    )*/

    return {
        resultData : request
    }
    /*
    return request;
    */
}


export default axiosLoginData;