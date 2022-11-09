import {
  BrowserRouter as Router,
  Routes,
  Route,
}from "react-router-dom";

import MainPage from './views/MainPage';
import MyPage from './views/MyPage';
import Result from './views/Result';
function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<MainPage/>}/>
          <Route path="/MyPage/*" element={<MyPage/>}/>
          <Route path="/Result/*" element={<Result/>}/>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
