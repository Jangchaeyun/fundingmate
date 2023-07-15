import Banner from "../../Component/Banner/Banner";
import Category from "../../Component/Category/Category";
import Finish from "../../Component/Reward/Finish";
import Preopen from "../../Component/Reward/Preopen";
import Rewarding from "../../Component/Reward/Rewarding";

const Reward = () => {
  return (
    <div className="App">
      <Category />
      <Rewarding />
      <Preopen />
      <Finish />
    </div>
  );
};

export default Reward;
