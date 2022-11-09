import {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axiosSignUpData from '../axios/axiosSignUpData';
import axiosLoginData from '../axios/axiosLoginData';
import axiosIdDuplicateCheck from '../axios/axiosIdDuplicateCheck';
function SignUp(props){
    const [전공입력, set전공입력] = useState(false);
    const [id, setId] = useState("");
    const [email, setEmail] = useState("");
    const [pw, setPw] = useState("");
    const [nick, setNick] = useState("");
    const [major, setMajor] = useState("");

    const onClickHandler = (e) => {
        console.log(e.target);
        let idValue = {
            id : id
        }
        e.preventDefault();
        const result  = axiosIdDuplicateCheck(idValue);
        /* 아이디 중복체크 로직 */
        /*result.then( data => {
            if(data.idCheckSuccess){
                alert(data.msg);
                e.target.disabled=true;
            }else{
                alert(data.msg);
            }
        })*/
        if(result.idCheckSuccess){
            alert(result.msg);
            e.target.disabled=true;
        }else{
            alert(result.msg);
        }
    }

    const navigate = useNavigate();
    /*
    axiosSignUpData.js 에서 회원가입 데이터를 받고
    서버로 전송 후 msg 와 회원가입 성공여부를 받는다.
    회원가입에 성공하였을 시 즉시 로그인에 시도한다.
    */
    const onSubmitHandler = (e) =>{
        let SignUpData ={
            id : id,
            email : email,
            pw : pw,
            nick : nick,
            isPro : 전공입력,
            major : major
        }
        e.preventDefault();
        const result = axiosSignUpData(SignUpData);
        result.then(data => {
            if(data.signUpSuccess){
                alert(data.msg);
                var loginData ={
                    id : id,
                    pw : pw,
                }
                axiosLoginData(loginData);
                navigate(0);
            }else{
                alert(data.msg)
            }
        })
    }

    return(
        <div className="modal">
            <div className="SignUpModal">
                <span className="close" onClick={()=>{props.forClose(false)}}>x</span>
                <div className="modalContents">
                    <form onSubmit={onSubmitHandler}>
                        <input type="text" className="SignUpId" value={id} onChange={(e)=>{setId(e.target.value)}} placeholder="아이디 입력"></input>
                        <input type="button" className="JungbokBtn" value="중복체크" onClick={onClickHandler}></input>
                        <input type="email" className="SignUpEmail" value={email} onChange={(e)=>{setEmail(e.target.value)}} placeholder="이메일 입력"></input>
                        <input type="password" className="SignUpPw" value={pw} onChange={(e)=>{setPw(e.target.value)}} placeholder="비밀번호 입력"></input>
                        <input type="text" className="SignUpNick" value={nick} onChange={(e)=>{setNick(e.target.value)}} placeholder="닉네임 입력"></input>
                        <p>전공자 인가요?</p>
                        <span>
                            예<input className="radio" value="1" type="radio" name="jeon"onClick={()=>{set전공입력(true)}}></input>
                        
                            아니오<input value="0" type="radio" name="jeon" onClick={()=>{set전공입력(false)}}></input>
                        </span>
                        {전공입력 ? <input type="text" className="SignUpJeon" value={major} onChange={(e)=>{setMajor(e.target.value)}} placeholder="전공입력 ex) 컴퓨터공학과"></input> : null}
                        <button className="SignUpBtn">가입하기</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default SignUp;