import Category from "../../Component/Category/Category";
import Banner from "../../Component/Reward/Banner";
import Finish from "../../Component/Reward/Finish";
import Preopen from "../../Component/Reward/Preopen";
import Rewarding from "../../Component/Reward/Rewarding";

const Reward = () => {
  return (
    <div className="App">
      <Banner />
      <Category />
      <Rewarding />
      <Preopen />
      <Finish />
    </div>
  );
};

export default Reward;
