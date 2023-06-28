import "./App.css";
import Reward from "./pages/Reward/Reward";
import Rewarddetail from "./pages/Rewarddetail/Rewarddetail";
import InvestBasicInfo from "./Component/InvestMake/InvestBasicInfo";


function App() {
  return (
    <div className="App">
      <Routes>
        {/* <Route exact path="/" element={<Main />} /> */}
        <Route exact path="/reward" element={<Reward />} />
        <Route exact path="/reward-detail" element={<Rewarddetail />} />
      </Routes>
      <InvestBasicInfo />
    </div>
  );
}

export default App;
