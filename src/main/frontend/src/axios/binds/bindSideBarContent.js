import axiosGetHashTagRanking from "../axiosGetHashTagRanking";

const bindSideBarContent = (title) =>{
    const method = {
        "# 실시간 태그순위" : axiosGetHashTagRanking
    }
    return method[title];
}
export default bindSideBarContent;