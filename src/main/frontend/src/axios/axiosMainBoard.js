import axios from "axios";

const axiosMainBoard = (boardType) =>{
    const req = {
        board : boardType
    }
    const res = axios.post('/board/main',req)
    .then(response => response.data)
    .catch( err =>{
            return [
                {
                    title : `${boardType}글제목1`,
                    id : 1,
                    uploaderId : "test",
                    view : 1,
                    uploadtime : "2022-12-30",
                    like : 1
                },
                {
                    title : `${boardType}qna글제목2`,
                    id : 2,
                    uploaderId : "test2",
                    view : 2,
                    uploadtime : "2022-12-30",
                    like : 2
                }
            ]
    })
    return res;
}

export default axiosMainBoard;