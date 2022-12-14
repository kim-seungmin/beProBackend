import {
  BrowserRouter as Router,
  Routes,
  Route,
}from "react-router-dom";

import MainPage from './views/MainPage';
import MyPage from './views/MyPage';
import Write from './views/Write';
import QnABoard from "./views/QnA/QnABoard";
import QnABoardList from "./views/QnA/QnABoardList";
import QnAView from "./views/QnA/QnAView";
import QnAResult from "./views/QnA/QnAResult";
//import QnA404 from "./views/QnA/QnA404";
import NoticeBoard from "./views/Notice/NoticeBoard";
import NoticeBoardList from "./views/Notice/NoticeBoardList";
import NoticeResult from "./views/Notice/NoticeResult";
import NoticeView from "./views/Notice/NoticeView";
//import Notice404 from "./views/Notice/Notice404";

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<MainPage/>}/>
          <Route path="/mypage/*" element={<MyPage/>}/>
          <Route path="/qna" element={<QnABoard/>}>
            <Route index element={<QnABoardList/>}/>
            <Route path=":id" element={<QnAView/>}/>
            <Route path="search" element={<QnAResult/>}/>
          </Route>
          <Route path="/write" element={<Write/>}/>
          <Route path="/notice" element={<NoticeBoard/>}>
            <Route index element={<NoticeBoardList/>}/>
            <Route path=":id" element={<NoticeView/>}/>
            <Route path="search" element={<NoticeResult/>}/>
          </Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;

