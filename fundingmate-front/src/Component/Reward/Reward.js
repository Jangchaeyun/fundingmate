import "../../App.css";
import Banner from "./Main/Banner/Banner";
import Finish from "./Main/Finish/Finish";
import Preopen from "./Main/Preopen/Preopen";
import Rewarding from "./Main/Rewarding/Rewarding";

function Reward() {
  return (
    <div className="App">
      <Banner />
      <Rewarding />
      <Preopen />
      <Finish />
    </div>
  );
}

export default Reward;
