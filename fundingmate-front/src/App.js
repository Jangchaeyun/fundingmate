import { Route, Routes } from "react-router-dom";
import "./App.css";
import Reward from "./Component/Reward/Reward";

function App() {
  return (
    <div className="App">
      <Routes>
        {/* <Route exact path="/" element={<Main />} /> */}
        <Route exact path="/reward" element={<Reward />} />
      </Routes>
    </div>
  );
}

export default App;
