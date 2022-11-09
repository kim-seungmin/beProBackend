import {useState, useEffect} from 'react';
import axiosGetHashTag from '../axios/axiosGetHashTag';

function SearchDropDown(props){
    const [supportData, setSupportData]= useState([]);
    useEffect(() => {
      if(props.major.length > 1){
        //const value = hashtag;
        const value = axiosGetHashTag(setSupportData);
        setSupportData(value.filter((data)=>{
            return (data.name).includes(props.major);
        }))
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
        <div className="dropDownBox">
            {supportData.map((data, index)=>{
                return(
                  <>
                    <div value={data.name} onClick={onClickHandler} style={{borderBottom:"1px solid black", cursor:"pointer"}} key={index}>{data.name}</div>
                  </>
                )
            })}
        </div>
    )
}
export default SearchDropDown;