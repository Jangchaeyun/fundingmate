import { Route, Routes } from "react-router-dom";
import "./App.css";
import Reward from "./Component/Reward/Reward";
import Founder from "./pages/MyPage/Founder";

function App() {
  return (
    <div className="App">
      <Routes>
        {/* <Route exact path="/" element={<Main />} /> */}
        <Route exact path="/reward" element={<Reward />} />
        <Route exact path="/my-page?type=founder" element={<Founder />} />
      </Routes>
    </div>
  );
}

export default App;
