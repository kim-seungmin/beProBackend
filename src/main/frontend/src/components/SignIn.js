import {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axiosLoginData from '../axios/axiosLoginData';
function SignIn(props){
    const navigate = useNavigate();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
    const onSubmitHandler = (e)=>{
        let loginData = {
            id : id,
            password : pw
        }
        e.preventDefault();
        const result = axiosLoginData(loginData);
        if(result.resultData.loginSuccess){
            alert(result.resultData.msg);
            navigate(0);
        }else{
            alert(result.resultData.msg);
        }
        /*
        const result = axiosLoginData(loginData);
        result.then(data => {
            if(data.loginSuccess){
                alert(data.msg);
                navigate(0);
            }else{
                alert(data.msg);
            }
        })
        */
    }
    return(
        <div className="modal">
            <div className="loginModal">
                <span className="close" onClick={()=>{props.forClose(false)}}>x</span>
                <form onSubmit={onSubmitHandler}>
                    <div className="modalContents">
                        <input type="text" className="loginId" placeholder="아이디" value={id} onChange={(e)=>{setId(e.target.value)}}></input>
                        <input type="password" className="loginPw" placeholder="비밀번호" value={pw} onChange={(e)=>{setPw(e.target.value)}}></input>
                    </div>
                    <br/>
                    <div className="loginArea">
                        <button className="loginBtn">로그인</button>       
                    </div>             
                </form>
            </div>
        </div>
    )
}
export default SignIn;