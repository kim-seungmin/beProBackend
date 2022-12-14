import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import SignIn from './SignIn';
import SignUp from './SignUp';
import axiosSignOut from '../axios/axiosSignOut';

function Header(){
    const navigate= useNavigate();
    const [signIn, setSignIn] = useState(false);
    const [signUp, setSignUp] = useState(false);
    const [nick, setNick] = useState("");
    const [token, setToken] = useState("");

    const signOutHandler = ()=>{
        const isLogOut = axiosSignOut();
    }


    useEffect(() => {
        if(localStorage.getItem("token")){
            const userData = JSON.parse(localStorage.getItem("token"));
            setNick(userData.nick);
            setToken(userData.value);
        }
    }, [token])

    const UI = {
        loginSuccess1 : <Link to="/mypage/*"><li>{nick}</li></Link> ,
        loginSuccess2 : <li onClick={()=>{signOutHandler()}}>Sign Out</li>,
        SignUp : <li onClick={()=>{setSignUp(true)}}>Sign up</li>,
        SignIn : <li onClick={()=>{setSignIn(true)}}>Sign in</li>
    }

    return(
        <div className="area">
            <div className="innerArea">
                <div className="contentArea">
                <Link to="/"><div className="headerTitle">be전공자</div></Link>
                    <div className="headerTools">
                        <ul className="headerToolTitle">
                            <div style={{cursor:"pointer"}} onClick={()=>{ navigate('/write',{state:{ type:"new", board:"qna" }}) }}>글쓰기</div>
                            <span>|</span>
                            <Link to="/qna"><div>글목록</div></Link>
                            <span>|</span>
                            {token ? UI['loginSuccess1'] : UI['SignIn']}
                            {signIn ? <SignIn forClose={setSignIn} forNick={setNick}/> : null}
                            <span>|</span>
                            {token ? UI['loginSuccess2'] : UI['SignUp']}
                            {signUp ? <SignUp forClose={setSignUp}/> : null }
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Header;