function HashTag(props){
    const onClickHandler = ()=>{
        const copy = [...props.forDelTag];
        const index = copy.findIndex((el)=>el === props.tagName);
        copy.splice(index,1);
        props.setHashTag(copy);
    }
    return(
        <div style={{display:"flex", flexDirection:"row"}}>
            <div>{props.tagName}</div>
            <div style={{cursor:"pointer"}} onClick={onClickHandler}>X</div>
        </div>
        
    )
}
export default HashTag;