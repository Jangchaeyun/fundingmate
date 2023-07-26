import "../../App.css";
import "../../pages/Fund/Fund.css";
import Finish from "../../Component/Fund/Finish";
import Funding from "../../Component/Fund/Funding";
import Preopen from "../../Component/Fund/Preopen";
import Realtime from "../../Component/Fund/realtime";
import Category from "../../Component/Category/Category";
import Header from "../../Component/Header/Header";
import CorFooter from "../../Component/Footer/CorFooter";

const Fund = () => {
  return (
    <div className="App">
      <Header/>
      <Category />
      <Funding />
      <Preopen />
      <Realtime />
      <Finish />
      <CorFooter />
    </div>
  );
};

export default Fund;
