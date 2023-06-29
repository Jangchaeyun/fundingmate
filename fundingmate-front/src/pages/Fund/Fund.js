import "../../App.css";
import "../../pages/Fund/Fund.css";
import Finish from "../../Component/Fund/Finish";
import Funding from "../../Component/Fund/Funding";
import Preopen from "../../Component/Fund/Preopen";
import Realtime from "../../Component/Fund/realtime";
import Banner from "../../Component/Reward/Banner";

const Fund = () => {
  return (
    <div className="App">
      <Banner />
      <Funding />
      <Preopen />
      <Realtime />
      <Finish />
    </div>
  );
};

export default Fund;
