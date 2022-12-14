function HashTag(props){
    const onClickHandler = ()=>{
        const copy = [...props.forDelTag];
        const index = copy.findIndex((el)=>el === props.tagName);
        copy.splice(index,1);
        props.setHashTag(copy);
    }
    return(
        <div className="tagBox">
            <div>{props.tagName}</div>
            {props.forView ? null : <div style={{cursor:"pointer"}} onClick={onClickHandler}>X</div>}
        </div>
        
    )
}
export default HashTag;