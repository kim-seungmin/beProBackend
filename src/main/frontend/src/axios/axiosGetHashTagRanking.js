import axios from "axios";

const axiosGetHashTagRanking = () =>{
    const exp = ["# 컴퓨터공학과","# C언어","# JAVA","# 음악과","# python", "# react","# typescript","# git","# php","# jQuery"]

    const request = axios.get('/get/hashtagrank')
    .then( response => response.data)
    .catch(err =>{
        return exp
    })
    return request;
}
export default axiosGetHashTagRanking;