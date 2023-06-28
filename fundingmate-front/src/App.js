import { Route, Routes } from "react-router-dom";
import "./App.css";
import Reward from "./Page/Reward/Reward";
import Rewarddetail from "./Page/Rewarddetail/Rewarddetail";

function App() {
  return (
    <div className="App">
      <Routes>
        {/* <Route exact path="/" element={<Main />} /> */}
        <Route exact path="/reward" element={<Reward />} />
        <Route exact path="/reward-detail" element={<Rewarddetail />} />
      </Routes>
    </div>
  );
}

export default App;
