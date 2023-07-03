import "./App.css";
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
import { Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <Routes>
        {/* <Route exact path="/" element={<Main />} /> */}
        <Route exact path="/reward" element={<Reward />} />
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
        <Route exact path="/MakeReward1" element={<MakeReward1 />} />
        <Route exact path="/MakeReward2" element={<MakeReward2/>} />
        <Route exact path="/MakeReward3" element={<MakeReward3/>} />
    </Routes>
    </div>
  );
};

export default App;
