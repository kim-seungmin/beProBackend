import {useState, useEffect} from 'react';
import axiosGetHashTag from '../axios/axiosGetHashTag';

function SearchDropDown(props){
    const [supportData, setSupportData]= useState([]);
    useEffect(() => {
      if(props.major.length > 1){
        const value = axiosGetHashTag();
        value.then(data =>{
          setSupportData( data.filter((
            (bit)=>{
              return (bit.name).includes(props.major);
            }
          )))
        })
      }else{
        setSupportData([]);
      }
    }, [props])
    const onClickHandler = (e) =>{
      const tags = [...props.forAppendHashTag];
      if(tags.includes(e.target.innerText)){
        return alert("중복된 태그가 존재합니다.");
      }
      props.setHashTag([...props.forAppendHashTag, e.target.innerText]);
      props.forDelText("");
    }
    return(
        <div key={"dropdown"} className="dropDownBox">
            {supportData.map((data, index)=>{
                return(
                    <div key={data.name} onClick={onClickHandler} style={{borderBottom:"1px solid black", cursor:"pointer"}} >{data.name}</div>
                )
            })}
        </div>
    )
}
export default SearchDropDown;