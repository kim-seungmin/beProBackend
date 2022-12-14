import { Outlet } from "react-router-dom";
import Header from "../../components/Header";
const QnABoard = () =>{
    return(
        <>
        <Header/>
        <Outlet/>
        </>
    )
}

export default QnABoard;