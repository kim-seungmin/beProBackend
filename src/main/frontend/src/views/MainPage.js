import '../App.css';
import '../components/css/SignIn.css'
import '../components/css/Card.css';
import '../components/css/Header.css';
import '../components/css/SignUp.css';
import '../components/css/SideBar.css';
import '../components/css/SearchDropDown.css';
import '../components/css/HashTag.css';
import '../components/css/SearchBox.css';
import '../components/css/Write.css';
import Header from '../components/Header';
import Card from '../components/Card';
import SideBar from '../components/SideBar';
import SearchDropDown from '../components/SearchDropDown';
import HashTag from '../components/HashTag';
import SearchBox from '../components/SearchBox';
import axiosSearch from '../axios/axiosSearch';
import {useState} from 'react';

function MainPage(){
    const [value, setValue] = useState("");
    const [hashTag, setHashTag] = useState([]);

    return(
    <div className="App">
      <Header/>
      <div className="mainContentFrame">
        <div className="innerArea border">
          <div className="contentFrame">
            <SideBar name={"# 실시간 태그순위"}/>
            <div className="cardFrame">
            <div>
                <SearchBox forValue={value} hashTag={hashTag} forsetValue={setValue}/>
                <SearchDropDown major={value} forDelText={setValue} setHashTag={setHashTag} forAppendHashTag={hashTag} />
                <div className="tagArea" id="tags">
                  { hashTag.length > 0 ? hashTag.map(name =>(<HashTag forDelTag={hashTag} setHashTag={setHashTag} tagName={name} key={name}>{name}</HashTag>) ) : null }
                </div>
              </div>
              <div className="cardArea">
                <Card name={"최신 QnA"}/>
              </div>
              <div className="cardArea">
                <Card name={"공지사항"}/>
                <Card name={"조회수 높은 순"}/>
              </div>  
            </div>
          </div>
        </div>
      </div>
    </div>
    )
}

export default MainPage;