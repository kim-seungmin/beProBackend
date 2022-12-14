import { useEffect, useState } from 'react';
import {useLocation, useNavigate, useParams} from 'react-router-dom';
import axiosBoardView from '../../axios/axiosBoardView';
import HashTag from '../../components/HashTag';

const QnAView = () =>{
    const location = useLocation();
    const navigate = useNavigate();
    const params = useParams();
    const [content, setContent] = useState({});
    const [hashTag, setHashTag] = useState([]);
    
    useEffect(()=>{
        const res = axiosBoardView(params.id, "qna");
        res.then(data=>{
            setContent(data);
            setHashTag(data.tags);
        })
    },[])


    const onClickHandler = () =>{
        console.log(hashTag);
    }
    /*const onClickHandler = () =>{
        navigate('/write',{state:{ type:"edit", data:obj, board:"qna"}})
    }*/
    return (
        <div>
            <input value="글수정" type="button"/>
            <button onClick={onClickHandler}>테스트용</button>
            <div>제목 : {content.title}</div>
            <div className="tagArea" id="tags">
                태그 : { hashTag ? hashTag.map(name =>(<HashTag forDelTag={hashTag} forView={true} setHashTag={setHashTag} tagName={name} key={name}>{name}</HashTag>) ) : null }
            </div>
            <div>내용 : {content.detail}</div>
            <div>글쓴날짜 : {content.uploadtime}</div>
            <div>글쓴이 : {content.uploaderId}</div>
            <div>조회수 : {content.view}</div>
            <div>좋아요 : {content.like}</div>
        </div>
    )
}
export default QnAView;