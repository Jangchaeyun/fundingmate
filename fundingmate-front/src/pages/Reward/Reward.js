import Banner from "../../Component/Banner/Banner";
import Category from "../../Component/Category/Category";
import CorFooter from "../../Component/Footer/CorFooter";
import Header from "../../Component/Header/Header";
import Finish from "../../Component/Reward/Finish";
import Preopen from "../../Component/Reward/Preopen";
import Rewarding from "../../Component/Reward/Rewarding";

const Reward = () => {
  return (
    <div className="App">
      <Header />
      <Category />
      <Rewarding />
      <Preopen />
      <Finish />
      <CorFooter />
    </div>
  );
};

export default Reward;
