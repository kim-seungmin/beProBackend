import { useEffect, useState } from "react";
import bindSideBarContent from "../axios/binds/bindSideBarContent";

function SideBar(props){
    const [content, setContent] = useState([]);
    useEffect(()=>{
      const method = bindSideBarContent(props.name);
      const result = method();
      result.then(data=>{
        setContent(data);
      })
    },[props.name])
    return(
        <div className="sideBarFrame">
              <div className="sideBarTitle">{props.name}</div>
              <div className="sideBarContentFrame">
                {content.map((data, index)=>{
                  return <div key={data}>{index+1}. {data}</div>
                })}
              </div>
        </div>
    )
}
export default SideBar;