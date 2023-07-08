import "./App.css";
import Home from "./pages/Home/Home";
import {Route, Routes} from "react-router-dom";
import Login from "./pages/Login/Login";
import * as React from 'react'
import { Reset } from 'styled-reset'
import FindIdPw from "./pages/Login/FindIdPw";
import Join from "./pages/Login/Join";
import FindId from "./pages/Login/FindId";
import FindPw from "./pages/Login/FindPw";
import FindIdResult from "./pages/Login/FindIdResult";
import FindPwNext from "./pages/Login/FindPwNext";
import FindPwResult from "./pages/Login/FindPwResult";
import Search from "./pages/Home/Search";
import Reward from "./pages/Reward/Reward";
import Story from "./Component/RewardDetail/Detail/Story";
import Contact from "./Component/RewardDetail/Detail/Contact";
import Guide from "./Component/RewardDetail/Detail/Guide";
import Fund from "./pages/Fund/Fund";
import FundStory from "./Component/FundDetail/Detail/FundStroy";
import FundContact from "./Component/FundDetail/Detail/FundContact";
import FundGuide from "./Component/FundDetail/Detail/FundGuide";
import CheckCategory from "./Component/Checkout/CheckCategory";
import Checkout from "./Component/Checkout/Checkout";
import CheckoutComplete from "./Component/Checkout/CheckoutComplete";
import FundPeople from "./Component/Checkout/FundPeople";
import Checkfund from "./Component/Checkout/Checkfund";
import FundCheckout from "./Component/Checkout/FundCheckout";
import MakeReward1 from "./Component/MakeReward/MakeReward1";
import MakeReward2 from "./Component/MakeReward/MakeReward2";
import MakeReward3 from "./Component/MakeReward/MakeReward3";
import MakeReward4 from "./Component/MakeReward/MakeReward4";
import MakeReward5 from "./Component/MakeReward/MakeReward5";

import MakeInvest1 from "./Component/MakeInvest/MakeInvest1";
import MakeInvest2 from "./Component/MakeInvest/MakeInvest2";
import MakeInvest3 from "./Component/MakeInvest/MakeInvest3";
import MakeInvest4 from "./Component/MakeInvest/MakeInvest4";
import MakeInvest5 from "./Component/MakeInvest/MakeInvest5";
import Founder from "./pages/MyPage/Founder";
import RewardFundMain from "./Component/RewardFund/RewardFundMain";
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
            <Route exact path="/my-page?type=founder" element={<Founder />} />
          </Routes>
          <Routes>
            <Route exact path="/reward-detail/story" element={<Story />} />
            <Route exact path="/reward-detail/contact" element={<Contact />} />
            <Route exact path="/reward-detail/guide" element={<Guide />} />
          </Routes>
          <Routes>
            <Route exact path="/fund" element={<Fund />} />
          </Routes>
          <Routes>
            <Route exact path="/fund-detail/story" element={<FundStory />} />
            <Route exact path="/fund-detail/contact" element={<FundContact />} />
            <Route exact path="/fund-detail/guide" element={<FundGuide />} />
          </Routes>
          <Routes>
            <Route exact path="/checkout/check" element={<CheckCategory />} />
            <Route exact path="/reward-checkout" element={<Checkout />} />
            <Route
              exact
              path="/reward-checkout/complete"
              element={<CheckoutComplete />}
            />
          </Routes>
          <Routes>
            <Route
              exact
              path="/fund-checkout/fundpeople"
              element={<FundPeople />}
            />
            <Route exact path="/fund-checkout/checkfund" element={<Checkfund />} />
            <Route
              exact
              path="/fund-checkout/checkout"
              element={<FundCheckout />}
            />
            <Route
              exact
              path="/fund-checkout/complete"
              element={<CheckoutComplete />}
            />
          </Routes>
          <Routes>
            <Route exact path="/rewardfund" element={<RewardFundMain />} />
          </Routes>
            <Routes>
                <Route exact path="/make-reward/basicinfo" element={<MakeReward1 />} />
                <Route exact path="/make-reward/story" element={<MakeReward2/>} />
                <Route exact path="/make-reward/typelist" element={<MakeReward3/>} />
                <Route exact path="/make-reward/goodsinfo" element={<MakeReward4/>} />
                <Route exact path="/make-reward/hostinfo" element={<MakeReward5/>} />
                <Route exact path="/make-invest/basicinfo" element={<MakeInvest1 />} />
                <Route exact path="/make-invest/moneyinfo" element={<MakeInvest2/>} />
                <Route exact path="/make-invest/story" element={<MakeInvest3/>} />
                <Route exact path="/make-invest/typelist" element={<MakeInvest4/>} />
                <Route exact path="/make-invest/hostinfo" element={<MakeInvest5/>} />
            </Routes>
        </div>
      </React.Fragment>

  );
}

export default App;
