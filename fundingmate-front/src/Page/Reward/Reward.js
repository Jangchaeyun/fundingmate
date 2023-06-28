import "../../App.css";
import Banner from "../../Component/Reward/Banner/Banner";
import Finish from "../../Component/Reward/Finish/Finish";
import Preopen from "../../Component/Reward/Preopen/Preopen";
import Rewarding from "../../Component/Reward/Rewarding/Rewarding";

const Reward = () => {
  return (
    <div className="App">
      <Banner />
      <Rewarding />
      <Preopen />
      <Finish />
    </div>
  );
};

export default Reward;
