import Header from '../components/Header';
import { useLocation } from 'react-router-dom';
function Result(){
    const location = useLocation();
    const contentData = location.state.content;
    return(
        <div>
            <Header/>
            {location.state.content}
            검색결과 페이지입니다.
        </div>
    )

}

export default Result;