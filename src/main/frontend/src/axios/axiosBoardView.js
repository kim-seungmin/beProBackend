import axios from "axios";

const axiosBoardView = (index,boardType) =>{
    const req = {
        id : index,
        board : boardType
    }
    const res = axios.post('/board/view',req)
    .then(response => response.data)
    .catch(err =>{
        let returnData ={
            title : `${boardType}글제목${index}`,
            id : index,
            uploaderId : "test",
            view : 1,
            uploadtime : "2022-12-30",
            like : 1,
            detail : `${index}번째 글입니다.`
        }
        if(boardType ==="qna"){
            returnData['tags'] = ["태그1", "태그2"];
        }
        return returnData;
    })
    return res;
}

export default axiosBoardView;