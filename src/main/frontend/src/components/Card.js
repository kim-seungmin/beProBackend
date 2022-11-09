import {useEffect, useState} from 'react'
function Card(props){
    const [글제목, set글제목] = useState("");
    useEffect(() => {
      set글제목(props.name)
      /* 백엔드 통신 */ 
      // eslint-disable-next-line default-case
      switch(props.name){
        case "최신 QnA":
            break;
        case "최근 등록된 용어":
            break;
        case "공지사항":
            break;
        case "조회수 높은 순":
            break;    
      }
    })
    return (
        <div className="card">
          <div className="cardTitle">{글제목}</div>
          <div className="cardDetail">
            <div>최신 등록된 QnA입니다.</div>
            <div>최신 등록된 QnA입니다.</div>
            <div>최신 등록된 QnA입니다.</div>
            <div>최신 등록된 QnA입니다.</div>
            <div>최신 등록된 QnA입니다.</div>
            <div>최신 등록된 QnA입니다.</div>
            <div>최신 등록된 QnA입니다.</div>
            <div>최신 등록된 QnA입니다.</div>
            <div>최신 등록된 QnA입니다.</div>
            <div>최신 등록된 QnA입니다.</div>
          </div>
        </div>
  )
}
export default Card;