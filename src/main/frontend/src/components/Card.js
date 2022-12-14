import {useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom';
import bindCardContent from '../axios/binds/bindCardContent';

const Card = (props) => {
    const [title, setTitle] = useState("");
    const [contentTitle, setContentTitle] = useState([]);
    const [boardType, setBoardType] = useState("");
    const navigate = useNavigate();
    

    const onLinkHandler = (e) =>{
      const titleObj = {
        "최신 QnA" : "qna",
        "공지사항" : "notice"
      }
      if(e.target.innerHTML !== "조회수 높은 순"){
        navigate(titleObj[e.target.innerHTML]);
      }
    }

    useEffect(() => {
      setTitle(props.name);
      const obj = bindCardContent(props.name);
      setBoardType(obj.boardType);
      const method =obj.method;
      console.log(method);
      method(obj.boardType)
      .then( data =>{
          setContentTitle(data);
        }
      )
    },[props.name])


    return (
        <div className="card">
          <div onClick={ onLinkHandler} className="cardTitle">{title}</div>
          <div className="cardDetail">
            {contentTitle.map((data, index)=>{
              return <div onClick={()=>{navigate(`/${boardType}/${data['id']}`,{state:{
                obj : data}})}} className="cardContentTitle" key={data.id}>제목 : {data.title}|조회수 : {data.view}|글쓴 날짜 : { data.uploadtime }</div>
            })}
          </div>
        </div>
  )
}
export default Card;