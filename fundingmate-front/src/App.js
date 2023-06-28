import { Route, Routes } from "react-router";
import { Route, Routes } from "react-router";
import "./App.css";
import Reward from "./pages/Reward/Reward";
import Rewarddetail from "./pages/Rewarddetail/Rewarddetail";
import Home from "./pages/Home/Home";
import Reward from "./pages/Reward/Reward";
import Rewarddetail from "./pages/Rewarddetail/Rewarddetail";
import Home from "./pages/Home/Home";

function App() {
  return (
    <div className="App">
      <Home />
      <Routes>
        <Route exact path="/" element={<Main />} />
        <Route exact path="/reward" element={<Reward />} />
        <Route exact path="/reward-detail" element={<Rewarddetail />} />
      </Routes>
    </div>
  );
}

export default App;
