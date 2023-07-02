import "./App.css";
import Home from "./pages/Home/Home";
import {Route, Routes} from "react-router-dom";
import Login from "./pages/Login/Login";
import * as React from 'react'
import { Reset } from 'styled-reset'
import Reward from "./Component/Reward/Reward";
import FindIdPw from "./pages/Login/FindIdPw";
// import Reward from "./Component/Reward/Reward";
// import Rewarddetail from "./Component/RewardDetail/Rewarddetail";

function App() {
  return (
      <React.Fragment>
          <Reset />
        <div className="App">
           <Routes>
              <Route exact path="/" element={<Home />} />
              <Route exact path="/login" element={<Login />} />
              <Route exact path="/findIdPw" element={<FindIdPw />} />
             {/*<Route exact path="/reward" element={<Reward />} />*/}
             {/*<Route exact path="/reward-detail" element={<Rewarddetail />} />*/}
           </Routes>
        </div>
      </React.Fragment>
  );
}

export default App;
