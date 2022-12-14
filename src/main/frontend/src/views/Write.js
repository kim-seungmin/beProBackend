import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import Header from "../components/Header";
import { useState } from "react";
import HashTag from "../components/HashTag";
import axiosPost from "../axios/axiosPost";
import axiosPostUpdate from "../axios/axiosPostUpdate";

const Write = (props)=>{
    const [contentTitle, setContentTitle] = useState("");
    const [tag, setTag] = useState([]);
    const [context, setContext] = useState("");
    const location = useLocation();
    const obj = location.state.data
    useEffect(()=>{
        if(obj){
            setContentTitle(obj.title);
            setContext(obj.detail);
            setTag(obj.tags);    
        }
    },[])
    
    
    const onEnterKeyUpHandler = (e)=>{
        if(e.keyCode ===13 && e.target.value.length>1){
            console.log(e.target.value);
            const cp = [...tag];
            if(cp.includes(e.target.value)){
                alert("중복된 태그가 이미 존재합니다.");
                return;
            }
            cp.push(e.target.value);
            setTag(cp);
            e.target.value="";
        }
    }

    const onClickHandler = (e)=>{
        e.preventDefault();
        const body = {
            "title" : contentTitle,
            "tag" : tag,
            "context" : context,
            "date" : new Date("2022-12-03").toLocaleDateString(),
        }
        if(location.state.type==="edit"){
            body['index'] = obj.index;
            body['views'] = obj.views;
            const resEdit = axiosPostUpdate(body,location.state.board);
            resEdit.then(data => {
                alert(data.msg);
            })
        }else if(location.state.type==="new"){
            body['index'] = localStorage.length; // 실제 통신할때에는 지울 것
            const resNew = axiosPost(body,location.state.board);
            resNew.then( data => {
                alert(data.msg);
            })  
        }
    }

    const onDeleteHandler = ()=>{
        for( let i=1;i<=localStorage.length;i++){
            localStorage.removeItem("post_"+i);
        }
    }

    return(
        <div className="App">
        <Header/>
        <div className="mainContentFrame">
          <div className="innerArea border">
            <div className="contentFrame writeFrame">
                <div>QnA 게시글 작성</div>
                    <div>
                        <div>
                            <div>제목</div><input type="text" value={contentTitle} onChange={(e)=>{setContentTitle(e.target.value)}} name="contentTitle" required/>
                        </div>
                        <div className="tagFrame">
                            <div className="tagArea" id="tags">
                                { tag.length > 0 ? tag.map(name =>(<HashTag forDelTag={tag} setHashTag={setTag} tagName={name} key={name}>{name}</HashTag>) ) : null }
                            </div>
                            <input className="tagInput" type="text" placeholder="태그를 입력해주세요" onKeyUp={onEnterKeyUpHandler} name="contentTitle"/>
                        </div>
                        <div>
                            <div>내용</div><textarea cols="24" rows="8" value={context} onChange={(e)=>{setContext(e.target.value)}} type="text" name="contentTitle" required/>
                        </div>
                    </div>
                    <div>
                        <input onClick={onClickHandler} value="submit" type="submit"/>
                    </div>
                    <input style={{width:"fit-content"}} value="글 전체삭제" onClick={onDeleteHandler} type="button"></input>
            </div>
          </div>
        </div>
      </div>
    )    
}
export default Write;