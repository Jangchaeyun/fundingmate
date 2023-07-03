
import "./App.css";
import MakeReward1 from "./Component/MakeReward/MakeReward1";
import MakeReward2 from "./Component/MakeReward/MakeReward2";
import MakeReward3 from "./Component/MakeReward/MakeReward3";
import { Route, Routes } from "react-router-dom";


function App() {
  return (
    <div className="App">
        <Routes>
            <Route exact path="/MakeReward1" element={<MakeReward1 />} />
            <Route exact path="/MakeReward2" element={<MakeReward2/>} />
            <Route exact path="/MakeReward3" element={<MakeReward3/>} />
        </Routes>



    </div>
  );
}

export default App;
