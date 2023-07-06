import "./App.css";
import Home from "./pages/Home/Home";
import {Route, Routes} from "react-router-dom";
import Login from "./pages/Login/Login";
import * as React from 'react'
import { Reset } from 'styled-reset'
import Reward from "./Component/Reward/Reward";
import FindIdPw from "./pages/Login/FindIdPw";
import Join from "./pages/Login/Join";
import FindId from "./pages/Login/FindId";
import FindPw from "./pages/Login/FindPw";
import FindIdResult from "./pages/Login/FindIdResult";
import FindPwNext from "./pages/Login/FindPwNext";
import FindPwResult from "./pages/Login/FindPwResult";
import Search from "./pages/Home/Search";
// import Reward from "./Component/Reward/Reward";
// import Rewarddetail from "./Component/RewardDetail/Rewarddetail";
import Reward from "./pages/Reward/Reward";
import Rewarddetail from "./pages/Rewarddetail/Rewarddetail";
import InvestBasicInfo from "./Component/InvestMake/InvestBasicInfo";
import { Route, Routes } from "react-router-dom";

function App() {
  return (
      <React.Fragment>
          <Reset />
        <div className="App">
           <Routes>
              <Route exact path="/" element={<Home />} />
              <Route exact path="/login" element={<Login />} />
              <Route exact path="/findIdPw" element={<FindIdPw />} />
              <Route exact path="/findId" element={<FindId />} />
              <Route exact path="/findIdResult" element={<FindIdResult />} />
              <Route exact path="/findPw" element={<FindPw />} />
              <Route exact path="/findPwNext" element={<FindPwNext />} />
              <Route exact path="/findPwResult" element={<FindPwResult />} />
              <Route exact path="/join" element={<Join />} />
              <Route exact path="/search" element={<Search />} />
             {/*<Route exact path="/reward" element={<Reward />} />*/}
             {/*<Route exact path="/reward-detail" element={<Rewarddetail />} />*/}
           </Routes>
          <Routes>
            {/* <Route exact path="/" element={<Main />} /> */}
            <Route exact path="/reward" element={<Reward />} />
          </Routes>
          <Routes>
            <Route exact path="/reward-detail/story" element={<Rewarddetail />} />
          </Routes>
          {/* <InvestBasicInfo /> */}
        </div>
      </React.Fragment>
  );
}

export default App;
